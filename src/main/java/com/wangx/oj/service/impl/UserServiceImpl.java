package com.wangx.oj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangx.oj.entity.User;
import com.wangx.oj.mapper.UserMapper;
import com.wangx.oj.service.UserService;
import com.wangx.oj.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        List<User> UserList = userMapper.findAll();
        return UserList;
    }

    @Override
    public void register(User user) {

        // 设置用户id
        user.setUid(UUIDGenerator.getUUID());
        // md5 加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        // 初始为普通用户
        user.setAuthority(0);
        // 设置初始分数为1500分
        user.setLevel(1500);
        user.setPassword(md5Password);
        log.info(user.toString());
        userMapper.InsertOne(user);
        return;
    }





    @Override
    public void update(User user) {
        userMapper.updateById(user);
        return;
    }

    @Override
    public User findUserById(String uid) {
        User user = userMapper.selectById(uid);
        return user;
    }

    @Override
    public User findUserByUserName(String username) {
        User userByUserName = userMapper.findUserByUserName(username);
        return userByUserName;
    }

    @Override
    public IPage<User> findUserPagination(Integer page, Integer pageSize) {
        Page<User> userPage = new Page<>(page, pageSize);//参数一是当前页，参数二是每页个数
        IPage<User> userIPage = userMapper.selectPage(userPage, null);
        return userIPage;
    }

    @Override
    public Integer getUserCount() {
        Integer count = userMapper.selectCount(null);
        return count;
    }

    @Override
    public void deleteUser(String uid) {
        userMapper.deleteById(uid);
    }
}
