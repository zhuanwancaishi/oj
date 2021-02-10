package com.wangx.oj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_user")
public class User implements Serializable {
    @TableId
    private String uid;
    private String username;
    private String password;
    private String email;
    private String tel;
    private Integer level;// 初始分数 1500分
    private Integer authority;//0 正常用户 1 管理员 -1 封禁用户
    private String avatar; // 头像地址
    private String realName; // 真实姓名
}
