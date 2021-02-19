package com.wangx.oj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.entity.Contest;


public interface ContestService {
    IPage<Contest> findContestPagination(int page, int pageSize);
}
