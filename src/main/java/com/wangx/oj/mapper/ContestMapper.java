package com.wangx.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.entity.Contest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestMapper extends BaseMapper<Contest>  {

}
