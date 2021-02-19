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
import com.wangx.oj.utils.IPUtils;
import com.wangx.oj.utils.RedisUtils;
import com.wangx.oj.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

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
            // 未判题，重新判题
            if (submission.getResult().equals(-1)){
                reJudge(submission);
            }
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

    /**
     * 判题，通过mq发送消息给判题端
     * @param submission 提交信息
     * @param tid 测试样例 id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result sendMessage(@RequestBody Submission submission, @RequestParam String tid) {
        Date dateNow = new Date();
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        String sign = smf.format(dateNow).replace("-", "");
        log.info("sign: " + sign);
        redisUtils.setBit(sign, 0, true);
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

    /**
     * 获取提交总数
     * @return
     */
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Result getCount(){
        Integer count = submissionService.getSubmissionCount();
        return Result.success(count);
    }

    /**
     * 重新判题
     * @param submission
     */
    private void reJudge(Submission submission) {
        // 设置为null更新题目详情的表格
        redisUtils.hmSet("problemChart", submission.getPid(), null);
        TestCase testCase = testCaseService.findTestCaseById(submission.getPid());
        // 测试样例和提交信息不存在一个表中，不进行存储
        submission.setInput(testCase.getInput());
        submission.setOutput(testCase.getOutput());
        // 发送信息
        amqpTemplate.convertAndSend("judge", JSON.toJSONString(submission));
    }

    @RequestMapping("/today")
    public Result getTodaySubmission(HttpServletRequest request){
        Date dateNow = new Date();
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        String sign = smf.format(dateNow).replace("-", "");
        log.info(sign);
        Long count = redisUtils.bitCount(sign);
        return Result.success(count);
    }

}
