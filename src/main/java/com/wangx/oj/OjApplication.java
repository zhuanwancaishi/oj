package com.wangx.oj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangx.oj.mapper")//使用MapperScan批量扫描所有的Mapper接口；
public class OjApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjApplication.class, args);
	}

}
