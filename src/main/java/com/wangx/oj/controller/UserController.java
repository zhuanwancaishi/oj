package com.wangx.oj.controller;

import com.wangx.oj.common.Result;
import com.wangx.oj.entity.User;
import com.wangx.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/register")
    public Result<Void> register(@RequestBody User user) {
        return userService.register(user);
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }



}
