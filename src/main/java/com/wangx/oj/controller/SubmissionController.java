package com.wangx.oj.controller;

import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Submission;
import com.wangx.oj.entity.User;
import com.wangx.oj.service.SubmissionService;
import com.wangx.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @Autowired
    UserService userService;
    @GetMapping("")
    public Result findAllPagination(@RequestParam Integer index, @RequestParam Integer pageSize) {
        List<Submission> submissionList = submissionService.findSubmissionPagination(index, pageSize);
        List<Map> resultList = new ArrayList<>();
        for (Submission submission:submissionList) {
            Map result = new HashMap();
            String uid = submission.getUid();
            User User = new User();
            User.setUid(uid);
            User user = userService.findUserById(User);
            result.put("submission", submission);
            result.put("user", user);
            resultList.add(result);
        }
        return Result.success(resultList);
    }

}
