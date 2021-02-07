package com.wangx.oj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_testcase")
public class TestCase implements Serializable {
    @TableId
    String id;
    String input;
    String output;
}
