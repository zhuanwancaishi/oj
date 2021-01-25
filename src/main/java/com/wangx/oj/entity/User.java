package com.wangx.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;
    private String username;
    private String password;
    private String email;
    private String tel;
    private Integer level;// 初始分数 1500分
    private Integer authority;//0 正常用户 1 管理员 -1 封禁用户
}
