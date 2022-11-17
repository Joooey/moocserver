package com.buaa.moocserver.controller;

import com.buaa.moocserver.entity.Course;
import com.buaa.moocserver.entity.User;
import com.buaa.moocserver.service.CourseService;
import com.buaa.moocserver.service.UserService;
import com.buaa.moocserver.utils.ResponseBuilder;
import com.buaa.moocserver.vo.request.EnrollCourseRequestVO;
import com.buaa.moocserver.vo.request.LoginRequestVO;
import com.buaa.moocserver.vo.request.ModelRequestVO;
import com.buaa.moocserver.vo.request.RegisterRequestVO;
import com.buaa.moocserver.vo.response.ModelResponseVO;
import com.buaa.moocserver.vo.response.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Joey
 * @date 2022/11/6
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO login(LoginRequestVO requestVO) {
        try {
            User user = userService.findById(requestVO.getUsername());
            if (user == null) {
                return ResponseBuilder.buildErrorResponse("用户不存在", ResponseVO.class);
            }
            if (StringUtils.equals(user.getUpassword(), requestVO.getPassword())) {
                return ResponseBuilder.buildSuccessResponse(user, ResponseVO.class);
            } else {
                return ResponseBuilder.buildErrorResponse("用户名或密码错误", ResponseVO.class);
            }
        } catch (Exception e) {
            return ResponseBuilder.buildErrorResponse(e, ResponseVO.class);
        }
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO register(RegisterRequestVO requestVO) {
        User user = new User();
        user.setUid(requestVO.getUsername());
        user.setUname(requestVO.getName());
        user.setUpassword(requestVO.getPassword());
        try {
            boolean status = userService.addUser(user);
            if (status) {
                return ResponseBuilder.buildSuccessResponse(null, ResponseVO.class);
            } else {
                return ResponseBuilder.buildErrorResponse("数据库写入失败", ResponseVO.class);
            }
        } catch (Exception e) {
            return ResponseBuilder.buildErrorResponse(e, ResponseVO.class);
        }
    }

    @RequestMapping(value = "/user/history/{id}")
    @ResponseBody
    public List<Course> queryHistoryCourseList(@PathVariable("id") String uid) {
        return userService.queryHistoryCourseListById(uid);
    }

    @RequestMapping(value = "/user/enroll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO enrollCourse(EnrollCourseRequestVO requestVO) {
        try {
            boolean status = userService.addEnrollRecord(requestVO.getUid(), requestVO.getCid());
            if (status) {
                return ResponseBuilder.buildSuccessResponse(null, ResponseVO.class);

            } else {
                return ResponseBuilder.buildErrorResponse("数据库写入失败", ResponseVO.class);
            }
        } catch (Exception e) {
            return ResponseBuilder.buildErrorResponse(e, ResponseVO.class);
        }
    }

    @RequestMapping(value = "/user/recommend/{id}")
    @ResponseBody
    public List<Course> queryRecommendCourseList(@PathVariable("id") String uid) {
        //获取用户交互历史
        List<Course> historyCourseList = userService.queryHistoryCourseListById(uid);
        if (historyCourseList.isEmpty()) {
            return new ArrayList<>();
        }
        //查询交互历史列表对应的itemId（模型中使用的int类型）
        List<Integer> itemIdList = new ArrayList<>();
        for (Course course : historyCourseList) {
            itemIdList.add(course.getItemId());
        }
        System.out.println(itemIdList);
        ModelRequestVO modelRequestVO = new ModelRequestVO();
        modelRequestVO.setSeq(itemIdList);
        //调用模型进行预测，获取top20推荐项目的itemID
        RestTemplate restTemplate = new RestTemplate();
        ModelResponseVO modelResponseVO = restTemplate.postForObject(
                "http://localhost:5000/recommend", modelRequestVO, ModelResponseVO.class);
        System.out.println(modelResponseVO);
        //根据itemID获取对应推荐的课程列表
        List<Course> results = new ArrayList<>();
        if (modelResponseVO != null) {
            List<Integer> recommendItemIdList = modelResponseVO.getResults();
            for (Integer itemId : recommendItemIdList) {
                Course recommendCourse = courseService.findByItemId(itemId);
                if (recommendCourse == null || recommendCourse.getCid().contains("thesis") || recommendCourse.getCid().contains("THESIS")) {
                    continue;
                } else {
                    results.add(recommendCourse);
                }
            }
        }
        return results;
    }

}
