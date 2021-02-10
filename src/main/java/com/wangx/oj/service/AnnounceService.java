package com.wangx.oj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Announce;

public interface AnnounceService {
    Result findAll();
    Result publishAnnounce(Announce announce);
    Result deleteAnnounce(Announce announce);
    Result findByAid(Announce announce);
    IPage<Announce> findAnnouncePagination(Integer page, Integer pageSize);
}
