package com.buaa.moocserver.service.impl;

import com.buaa.moocserver.entity.Course;
import com.buaa.moocserver.mapper.CourseMapper;
import com.buaa.moocserver.service.CourseService;
import com.buaa.moocserver.vo.response.PageResponseVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/9
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course findById(String id) {
        Course course = courseMapper.findById(id);
        course.setPreCidList(courseMapper.findPreListById(id));
        return course;
    }

    @Override
    public Course findByItemId(Integer itemId) {
        Course course = courseMapper.findByItemId(itemId);
        return course;
    }

    @Override
    public PageResponseVO getAllCourseList(int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<Course> courseList = courseMapper.getAllCourseList();
        return new PageResponseVO(page.getTotal(), courseList);
    }

    @Override
    public List<Course> getPopularCourseList(int k) {
        return courseMapper.getPopularCourseList(k);
    }
    @Override
    public List<Course> getRecommendCourseList(int k) {
        return null;
    }
}
