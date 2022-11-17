package com.buaa.moocserver.service.impl;

import com.buaa.moocserver.entity.Course;
import com.buaa.moocserver.entity.User;
import com.buaa.moocserver.mapper.CourseMapper;
import com.buaa.moocserver.mapper.UserMapper;
import com.buaa.moocserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joey
 * @date 2022/11/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<Course> queryHistoryCourseListById(String id) {
        List<String> cidList = userMapper.queryHistoryById(id);
        List<Course> historyCourseList = new ArrayList<>();
        for (String cid : cidList) {
            historyCourseList.add(courseMapper.findById(cid));
        }
        return historyCourseList;
    }

    @Override
    public boolean addEnrollRecord(String uid, String cid) {
        boolean status1 = userMapper.addCourseSequence(uid, cid);
        boolean status2 = courseMapper.updateCoursePopular(cid);
        return status1 && status2;
    }
}
