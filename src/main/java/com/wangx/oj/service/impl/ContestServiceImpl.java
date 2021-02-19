package com.wangx.oj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangx.oj.entity.Contest;
import com.wangx.oj.entity.Problem;
import com.wangx.oj.mapper.ContestMapper;
import com.wangx.oj.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContestServiceImpl implements ContestService {
    @Autowired
    private ContestMapper contestMapper;


    @Override
    public IPage<Contest> findContestPagination(int page, int pageSize) {
        Page<Contest> contestPage = new Page<>();
        IPage<Contest> contestIPage = contestMapper.selectPage(contestPage, null);

        return contestIPage;
    }
}
