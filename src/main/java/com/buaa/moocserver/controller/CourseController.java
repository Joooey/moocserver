package com.buaa.moocserver.controller;

import com.buaa.moocserver.entity.Course;
import com.buaa.moocserver.service.CourseService;
import com.buaa.moocserver.vo.response.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/9
 */
@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course/list/{pageNum}/{pageSize}")
    @ResponseBody
    public PageResponseVO queryAllCourseList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return courseService.getAllCourseList(pageNum, pageSize);
    }

    @RequestMapping(value = "/course/detail/{id}")
    @ResponseBody
    public Course queryAllCourseList(@PathVariable("id") String id) {
        return courseService.findById(id);
    }

    @RequestMapping(value = "/course/popular/{k}")
    @ResponseBody
    public List<Course> queryPopularCourseList(@PathVariable("k") Integer k) {
        List<Course> popularCourseList = courseService.getPopularCourseList(k);
        return popularCourseList;
    }
}
