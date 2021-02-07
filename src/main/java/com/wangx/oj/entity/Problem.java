package com.wangx.oj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_problem")
public class Problem implements Serializable {
    @TableId
    private String pid;
    private String tags;// ["tag1" , "tag2"]
    private String uid;
    private String title;
    private String description;
    private String samples;// {"input1":"1 1", "output1" : "2"}
    private String hint;
    private String languages; //["1","2"] 0 -> C 、1 -> c++、2 -> Java
    private Date createTime;
    private Date updateTime;
    private Integer timeLimit;//单位ms
    private Integer memoryLimit;//单位b
    private String testCaseId;
    private Integer level; // 0 -> easy 1-> mid 2 -> hard
    private Integer pass;
    private Integer totalSubmit;
    @TableField(exist=false)
    private String acRate;// 非表中字段，计算获得
    private String inputDescription;
    private String outputDescription;
}
