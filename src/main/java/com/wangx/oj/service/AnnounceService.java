package com.wangx.oj.service;

import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Announce;

public interface AnnounceService {
    Result findAll();
    Result publishAnnounce(Announce announce);
}
