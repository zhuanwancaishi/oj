package com.wangx.oj.service;

import com.wangx.oj.common.Result;
import com.wangx.oj.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    Result<Void> register(User user);
    Result login(User user);
}
