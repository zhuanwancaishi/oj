package com.wangx.oj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.entity.Rank;

public interface RankService {
    public IPage<Rank> findRankListPagination(int page, int pageSize);
}
