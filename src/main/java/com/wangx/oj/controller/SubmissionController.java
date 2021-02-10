package com.wangx.oj.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Submission;
import com.wangx.oj.entity.TestCase;
import com.wangx.oj.entity.User;
import com.wangx.oj.service.SubmissionService;
import com.wangx.oj.service.TestCaseService;
import com.wangx.oj.service.UserService;
import com.wangx.oj.utils.RedisUtils;
import com.wangx.oj.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    UserService userService;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(value = "/{index}/{pageSize}", method = RequestMethod.GET)
    public Result findAllPagination(@PathVariable Integer index, @PathVariable Integer pageSize) {
        IPage<Submission> submissionPagination = submissionService.findSubmissionPagination(index, pageSize);
        List<Submission> submissionList = submissionPagination.getRecords();
        Iterator<Submission> iterator = submissionList.iterator();
        while (iterator.hasNext()){
            Submission submission = iterator.next();
            User user = userService.findUserById(submission.getUid());
            submission.setUser(user);
        }
        submissionPagination.setRecords(submissionList);
        return Result.success(submissionPagination);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result update(Submission submission) {
        submissionService.update(submission);
        return Result.success("成功");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result sendMessage(@RequestBody Submission submission, @RequestParam String tid) {
        // 设置为null更新题目详情的表格
        redisUtils.hmSet("problemChart", submission.getPid(), null);
        TestCase testCase = testCaseService.findTestCaseById(tid);
        submission.setSid(UUIDGenerator.getUUID());
        submission.setCreateTime(new Date());
        submission.setInput(testCase.getInput());
        submission.setOutput(testCase.getOutput());
        submission.setResult(-1); // pending
        log.info(submission.toString());
        submissionService.add(submission);
        amqpTemplate.convertAndSend("judge", JSON.toJSONString(submission));
        return Result.success("success");
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Result getCount(){
        Integer count = submissionService.getSubmissionCount();
        return Result.success(count);
    }

}
