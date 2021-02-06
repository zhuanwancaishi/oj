package com.wangx.oj.service.impl;

import com.wangx.oj.entity.Submission;
import com.wangx.oj.mapper.SubmissionMapper;
import com.wangx.oj.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    SubmissionMapper submissionMapper;
    @Override
    public List<Submission> findSubmissionPagination(Integer index, Integer pageSize) {
        Integer start = (index - 1) * pageSize;
        Integer end = index * pageSize;
        return submissionMapper.findSubmissionPagination(start, end);
    }
}
