package com.wangx.oj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangx.oj.common.Result;
import com.wangx.oj.entity.Announce;
import com.wangx.oj.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/delete")
    Result delete(@RequestBody Announce announce) {
        return announceService.deleteAnnounce(announce);
    }

    @RequestMapping("/findByAid")
    Result findByAid(@RequestBody Announce announce) {
        return announceService.findByAid(announce);
    }

    @RequestMapping(value = "/{page}/{pageSize}", method = RequestMethod.GET)
    Result findAnnouncePagination(@PathVariable Integer page, @PathVariable Integer pageSize) {
        IPage<Announce> announcePagination = announceService.findAnnouncePagination(page, pageSize);
        return Result.success(announcePagination);
    }
}
