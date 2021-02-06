package com.wangx.oj;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangx.oj.entity.Announce;
import com.wangx.oj.entity.Submit;
import com.wangx.oj.entity.User;
import com.wangx.oj.mapper.AnnounceMapper;
import com.wangx.oj.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class OjApplicationTests {



	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


	@Autowired
	AmqpTemplate amqpTemplate;
	@Test
	public void testSender2() throws Exception {
		Submit submit = new Submit();
		submit.setCode("#include<stdio.h>\nint main(){\nint a=0,b=0;\nscanf(\"%d %d\",&a, &b);\nprintf(\"%d\",a+b);\nreturn 0;\n}");
		submit.setInput("1 1");
		submit.setOutput("2");
		submit.setType(1);
		String data = JSON.toJSONString(submit);
		amqpTemplate.convertAndSend("judge", data);
	}

	@Test
	public void testReceiver() throws Exception {
		Object result = amqpTemplate.receiveAndConvert("result");

	}

	@Test
	public void testJsonArray() {
		List<String> tags = new ArrayList<>();
		tags.add("dp");
		tags.add("aa");
		System.out.println(JSON.toJSON(tags));
	}

	@Autowired
	private UserMapper userMapper;
	@Test
	public void queryUserForPage(){
		Page<User> userPage = new Page<>(1, 2);//参数一是当前页，参数二是每页个数
		IPage<User> iPage = userMapper.selectPage(userPage, null);
		System.out.println(iPage);
	}

}
