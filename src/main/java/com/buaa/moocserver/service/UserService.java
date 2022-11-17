package com.buaa.moocserver.service;

import com.buaa.moocserver.entity.Course;
import com.buaa.moocserver.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/6
 */
public interface UserService {
    public User findById(String id);
    public boolean addUser(User user);

    public List<Course> queryHistoryCourseListById(String id);

    public boolean addEnrollRecord(String uid, String cid);
}
