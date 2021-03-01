package com.wangx.oj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_submission")
public class Submission implements Serializable {
    @TableId(value = "sid", type = IdType.INPUT)
    private String sid;
    private String pid;
    private Date createTime;// 提交时间
    private String uid;
    private String language;
    @TableField(exist = false)
    private Integer type;
    @TableField(exist = false)
    private String input;
    @TableField(exist = false)
    private String output;
    private Integer timeCost;
    private Integer memoryCost;
    private String code;// 代码
    /**
     * 结果
     *     {"pending": -1},
     *     {"Accepted": 1},
     *     {"Wrong Answer": 2},
     *     {"Runtime Error", 3},
     *     {"Output Limit Exceeded", 4},
     *     {"Memory Limit Exceeded", 5},
     *     {"Time Limit Exceeded", 6},
     *     {"Presentation Error", 7},
     *     {"System Error", 8},
     *     {"Compile Error", 9}
     */
    private Integer result;
    private String error; // 错误信息
    @TableField(exist = false)
    private User user;
}
