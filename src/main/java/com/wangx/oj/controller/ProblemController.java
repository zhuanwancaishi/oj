package com.wangx.oj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Problem;
import com.wangx.oj.entity.User;
import com.wangx.oj.service.ProblemService;
import com.wangx.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @Autowired
    UserService userService;
    @RequestMapping("/findAll")
    Result findAll() {
        List<Problem> problemList = problemService.findAllProblems();
        return Result.success(problemList);
    }


    @RequestMapping("/deleteOne")
    Result delete(@RequestBody Problem problem) {
        problemService.deleteOneProblem(problem);
        return Result.success(null);
    }

    @RequestMapping("/findOneByPid")
    Result findOne(@RequestParam String pid) {
        Problem problem = problemService.findOneProblem(pid);
        User User = new User();
        User.setUid(problem.getUid());
        User user = userService.findUserById(User);
        Map res = new HashMap();
        res.put("problem", problem);
        res.put("user",user);
        return Result.success(res);
    }

    @RequestMapping(value = "{page}/{pageSize}", method = RequestMethod.GET)
    Result findProblemPagination(@PathVariable Integer page, @PathVariable Integer pageSize) {
        IPage<Problem> problemPagination = problemService.findProblemPagination(page, pageSize);
        return Result.success(problemPagination);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    Result updateProblem(@RequestBody Problem problem) {
        problemService.update(problem);
        return Result.success("修改成功");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return Result.success("添加成功");
    }

}
