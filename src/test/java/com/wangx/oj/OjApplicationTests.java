package com.wangx.oj;

import com.alibaba.fastjson.JSON;
import com.wangx.oj.common.Result;
import com.wangx.oj.common.Sender;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class OjApplicationTests {

	@Autowired
	private Sender sender;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Test
	public void testSender1() throws Exception {
		Map<String, Object> properties = new HashMap<>();
		properties.put("number", "12345");
		properties.put("send_time", simpleDateFormat.format(new Date()));
		sender.send("Hello RabbitMQ For Spring Boot!", properties);
	}

	@Autowired
	AmqpTemplate amqpTemplate;
	@Test
	public void testSender2() throws Exception {
		String data = JSON.toJSONString(Result.success("success"));
		amqpTemplate.convertAndSend("judge", data);
	}

}
