package com.wangx.oj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Contest;
import com.wangx.oj.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @RequestMapping(value = "/{page}/{pageSize}", method = RequestMethod.GET)
    public Result findPagination(@PathVariable int page,@PathVariable int pageSize) {
        IPage<Contest> contestPagination = contestService.findContestPagination(page, pageSize);
        return Result.success(contestPagination);
    }

}
