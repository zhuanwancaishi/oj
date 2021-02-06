package com.wangx.oj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findAllProblems();

    void deleteOneProblem(Problem problem);

    Problem findOneProblem(String pid);

    void addOneProblem(Problem problem);

    IPage<Problem> findProblemPagination(Integer page, Integer pageSize);

    void update(Problem problem);

    void add(Problem problem);
}
