package com.wangx.oj.service;

import com.wangx.oj.entity.Submission;

import java.util.List;

public interface SubmissionService {
    List<Submission> findSubmissionPagination(Integer index, Integer pageSize);
}
