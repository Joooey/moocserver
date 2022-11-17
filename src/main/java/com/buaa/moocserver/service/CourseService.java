package com.buaa.moocserver.service;

import com.buaa.moocserver.entity.Course;
import com.buaa.moocserver.vo.response.PageResponseVO;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/9
 */
public interface CourseService {
    public Course findById(String id);

    public Course findByItemId(Integer itemId);

    public PageResponseVO getAllCourseList(int pageNum, int pageSize);

    public List<Course> getPopularCourseList(int k);

    public List<Course> getRecommendCourseList(int k);

}
