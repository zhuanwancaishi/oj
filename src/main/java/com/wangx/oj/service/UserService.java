package com.wangx.oj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void register(User User);
    void update(User User);
    User findUserById(User User);
    User findUserByUserName(String username);
    IPage<User> findUserPagination(Integer page, Integer pageSize);

}
