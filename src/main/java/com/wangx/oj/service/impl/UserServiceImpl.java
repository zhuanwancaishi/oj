package com.wangx.oj.service.impl;

import com.wangx.oj.common.CodeMsg;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.User;
import com.wangx.oj.mapper.UserMapper;
import com.wangx.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Result<Void> register(User user) {
        userMapper.InsertOne(user);
        return Result.success(null);
    }

    @Override
    public Result login(User user) {
        User res = userMapper.findUserByUserName(user.getUsername());
        if (res.getPassword().equals(user.getPassword())) {
            res.setPassword(null);
            return Result.success(res);
        }
        return Result.fail(CodeMsg.PASSWORD_ERROR);
    }
}
