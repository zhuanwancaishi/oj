package com.wangx.oj.service.impl;

import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Announce;
import com.wangx.oj.mapper.AnnounceMapper;
import com.wangx.oj.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnnounceServiceImpl implements AnnounceService {

    @Autowired
    AnnounceMapper announceMapper;
    @Override
    public Result findAll() {
        return Result.success(announceMapper.findAll());
    }

    @Override
    public Result publishAnnounce(Announce announce) {
        announce.setCreateTime(new Date());
        announce.setUpdateTime(new Date());
        announceMapper.insertOne(announce);
        return Result.success(null);
    }

    @Override
    public Result deleteAnnounce(Announce announce) {
        announceMapper.deleteOne(announce);
        return Result.success(null);
    }

    @Override
    public Result findByAid(Announce announce) {
        return Result.success(announceMapper.findByAid(announce));
    }


}
