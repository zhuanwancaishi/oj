package com.wangx.oj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangx.oj.entity.Rank;
import com.wangx.oj.entity.User;
import com.wangx.oj.mapper.RankMapper;
import com.wangx.oj.mapper.UserMapper;
import com.wangx.oj.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankMapper rankMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public IPage<Rank> findRankListPagination(int page, int pageSize) {
        Page<Rank> rankPage = new Page<>(page, pageSize);
        IPage<Rank> selectPage = rankMapper.selectPage(rankPage, null);
        List<Rank> rankList = selectPage.getRecords();
        Iterator<Rank> iterator = rankList.iterator();
        DecimalFormat df = new DecimalFormat("0.00");
        while (iterator.hasNext()){
            Rank rank = iterator.next();
            User user = userMapper.selectById(rank.getUid());
            rank.setUsername(user.getUsername());
            if (rank.getTotal() == 0) {
                rank.setAcRate("0");
            } else {
                 double rate = ((double)rank.getPass() / rank.getTotal());
                 rank.setAcRate(df.format(rate * 100));
            }
        }
        selectPage.setRecords(rankList);
        return selectPage;
    }
}
