package com.wangx.oj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void register(User User);
    void update(User User);
    User findUserById(String uid);
    User findUserByUserName(String username);
    IPage<User> findUserPagination(Integer page, Integer pageSize);
    Integer getUserCount();
    void deleteUser(String uid);
}
