package com.wangx.oj.controller;

import com.wangx.oj.common.Result;
import com.wangx.oj.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SmsUtils smsUtils;

    @RequestMapping("/test")
    public Result testSendSms(@RequestParam String phone){
        smsUtils.send(phone);
        return Result.success(null);
    }
}
