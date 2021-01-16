package com.wangx.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announce {
    private Integer aid;
    private String uid;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
}
