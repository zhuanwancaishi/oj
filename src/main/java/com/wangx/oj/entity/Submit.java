package com.wangx.oj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Submit {
    private String sid;
    private String code;
    private Integer type;
    private Integer time;
    private Integer mem;
    private String err;
    private String input;
    private String output;
}
