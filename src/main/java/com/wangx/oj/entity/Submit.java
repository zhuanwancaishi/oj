package com.wangx.oj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_submit")
public class Submit {
    @TableId
    private String sid;
    private String code;
    private Integer type;
    private Integer time;
    private Integer mem;
    private String err;
    private String input;
    private String output;
}
