package com.wangx.oj.mapper;

import com.wangx.oj.entity.Announce;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnounceMapper {
    List<Announce> findAll();

    void insertOne(Announce announce);
}
