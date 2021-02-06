package com.wangx.oj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_submission")
public class Submission {
    @TableId
    private String id;
    private String pid;
    private Date createTime;// 提交时间
    private String uid;
    private String language;
    private Integer timeCost;
    private Integer memoryCost;
    private String code;// 代码
    private Integer result; // 结果
}
