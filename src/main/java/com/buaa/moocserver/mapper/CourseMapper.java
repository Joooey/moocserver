package com.buaa.moocserver.mapper;

import com.buaa.moocserver.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/9
 */
@Mapper
@Repository
public interface CourseMapper {
    public Course findById(String id);

    public Course findByItemId(Integer itemId);

    public List<String> findPreListById(String id);

    public List<Course> getAllCourseList();

    public List<Course> getPopularCourseList(int k);

    public List<Course> getRecommendCourseList(int k);

    public boolean updateCoursePopular(String id);
}
