package com.buaa.moocserver.mapper;

import com.buaa.moocserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/6
 */
@Mapper
@Repository
public interface UserMapper {
    public User findById(String id);
    public boolean addUser(User user);

    public List<String> queryHistoryById(String id);

    // 新增用户学习课程记录
    public boolean addCourseSequence(String uid, String cid);

}
