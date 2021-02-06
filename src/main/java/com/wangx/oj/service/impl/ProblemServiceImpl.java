package com.wangx.oj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangx.oj.common.CodeMsg;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Problem;
import com.wangx.oj.mapper.ProblemMapper;
import com.wangx.oj.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemMapper problemMapper;

    @Override
    public List<Problem> findAllProblems() {
        List<Problem> res = new ArrayList<>();
        List<Problem> problemList = problemMapper.findAll();
        for(Problem problem: problemList) {
            if (problem.getTotalSubmit() == 0) {
                problem.setAcRate("0%");
            } else {
                double rate = ((double)problem.getPass() / problem.getTotalSubmit())*100;
                problem.setAcRate(rate+"%");
            }
            res.add(problem);
        }
        return res;
    }

    @Override
    public void deleteOneProblem(Problem problem) {
        problemMapper.deleteOne(problem);
    }

    @Override
    public Problem findOneProblem(String pid) {
        log.info(pid);
        Problem one = problemMapper.findOne(pid);
        return one;
    }

    @Override
    public void addOneProblem(Problem problem) {
        problemMapper.insertOne(problem);
    }

    @Override
    public IPage<Problem> findProblemPagination(Integer page, Integer pageSize) {
        Page<Problem> problemPage = new Page<>(page, pageSize);
        IPage<Problem> iPage = problemMapper.selectPage(problemPage, null);
        return iPage;
    }

    @Override
    public void update(Problem problem) {
        problemMapper.updateById(problem);
    }

    @Override
    public void add(Problem problem) {
        problemMapper.insert(problem);
    }
}
