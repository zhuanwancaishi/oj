package com.wangx.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangx.oj.entity.Submission;
import com.wangx.oj.mapper.SubmissionMapper;
import com.wangx.oj.service.SubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    SubmissionMapper submissionMapper;
    @Override
    public IPage findSubmissionPagination(Integer index, Integer pageSize) {
        Integer start = (index - 1) * pageSize;
        Integer end = index * pageSize;
        Page<Submission> page = new Page<>(index, pageSize);
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return submissionMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void update(Submission submission) {
        submissionMapper.updateById(submission);
        return;
    }

    @Override
    public void add(Submission submission) {
        log.info("add" + submission.toString());
        submissionMapper.insert(submission);
        return;
    }

    @Override
    public Submission findSubmissionById(String sid) {
        Submission submission = submissionMapper.selectById(sid);
        return submission;
    }

    @Override
    public Integer getSubmissionCount() {
        Integer count = submissionMapper.selectCount(null);
        return count;
    }
}
