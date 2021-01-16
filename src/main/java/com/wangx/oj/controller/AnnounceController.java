package com.wangx.oj.controller;

import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Announce;
import com.wangx.oj.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announce")
public class AnnounceController {
    @Autowired
    AnnounceService announceService;

    @RequestMapping("/findAll")
    Result findAll() {
        return announceService.findAll();
    }

    @RequestMapping("/add")
    Result add(@RequestBody Announce announce) {
        return announceService.publishAnnounce(announce);
    }
}
