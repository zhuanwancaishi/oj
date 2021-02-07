package com.wangx.oj.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.CodeMsg;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.User;
import com.wangx.oj.service.UserService;
import com.wangx.oj.utils.RedisUtils;
import com.wangx.oj.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @Value("refreshToken-expireTime")
    String refreshTokenExpireTime;

    @RequestMapping("/findAll")
    public Result findAll(){
        return Result.success(userService.findAll());
    }



    @RequestMapping("/register")
    public Result register(@RequestBody User User) {
        userService.register(User);
        return Result.success(null);
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User User, @RequestParam String code) {
        log.info(User.toString());
        if(!verityCode(code)) return Result.fail(CodeMsg.VERITY_CODE_ERROR);
        if (User.getUsername() == null) return Result.fail(CodeMsg.NO_USER);
        if (User.getPassword() == null || "".equals(User.getPassword())) return Result.fail(CodeMsg.PASSWORD_EMPTY);

        User res = userService.findUserByUserName(User.getUsername());


        String userToken = (String) redisUtils.hmGet("user_token", User.getUsername());

        if (userToken!=null) {
            res.setPassword("");
            Map result = new HashMap();
            result.put("user",res);
            log.info("Get user token");
            result.put("token", userToken);
            return Result.success(result);
        }
        String passwordMd5 = DigestUtils.md5DigestAsHex(User.getPassword().getBytes());
        log.info(passwordMd5+"\t"+res.getPassword());
        if (passwordMd5.equals(res.getPassword())){
            res.setPassword("");
            Map result = new HashMap();
            result.put("user",res);
            String token = UUIDGenerator.getUUID();
            result.put("token", token);
            redisUtils.hmSet("user_token", User.getUsername(), token);
            return Result.success(result);
        }
        return Result.fail(CodeMsg.PASSWORD_ERROR);
    }

    @RequestMapping("/logout")
    public Result Logout(@RequestBody User User) {
        String userToken = (String) redisUtils.hmGet("user_token", User.getUsername());
        if (userToken!=null){
            redisUtils.remove("user_token", User.getUsername());
        }
        return Result.success(null);
    }

    @RequestMapping("/findUserById")
    public Result findUserById(@RequestBody User User) {
        return Result.success(userService.findUserById(User.getUid()));
    }

    public Boolean verityCode(String code){
        String verificationCodeIn = (String) redisUtils.get("verificationCode");
        redisUtils.remove("verificationCode");
        log.info(verificationCodeIn+"==="+code);
        if ( verificationCodeIn==null || StringUtils.isEmpty(verificationCodeIn)) return Boolean.FALSE;
        if (verificationCodeIn.equals(code)) {
            log.info("验证成功");
            return Boolean.TRUE;
        }
        log.info("验证失败");
        return Boolean.FALSE;
    }

    @RequestMapping(value = "/{page}/{pageSize}", method = RequestMethod.GET)
    public Result findUserPagination(@PathVariable Integer page, @PathVariable Integer pageSize) {
        IPage<User> userPagination = userService.findUserPagination(page, pageSize);
        return Result.success(userPagination);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result updateUser(@RequestBody User user) {
        userService.update(user);
        return Result.success("更新成功");
    }



}
