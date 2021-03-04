package com.wangx.oj.controller;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.wangx.oj.common.CodeMsg;
import com.wangx.oj.common.Result;
import com.wangx.oj.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private SmsUtils smsUtils;

    @RequestMapping(value = "/{phone}", method = RequestMethod.GET)
    public Result sendSms(@PathVariable String phone) {
        if (phone == null || "".equals(phone)) return Result.fail(CodeMsg.MOBILE_EMPTY);
        if (!validateMobilePhone(phone)) return Result.fail(CodeMsg.MOBILE_ERROR);
        smsUtils.send(phone);
        return Result.success("发送成功");
    }

    /**
     *  正则：手机号（简单）, 1字头＋10位数字即可.
     * @param in
     * @return
     */
    public  boolean validateMobilePhone(String in) {
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        return pattern.matcher(in).matches();
    }

}
