package com.wangx.oj.mapper;

import com.wangx.oj.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    void InsertOne(User user);
    User findUserByUserName(String username);
}
