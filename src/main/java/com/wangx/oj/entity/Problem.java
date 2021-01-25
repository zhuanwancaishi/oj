package com.wangx.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Problem {
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
}
