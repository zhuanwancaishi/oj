package com.wangx.oj;

import com.alibaba.fastjson.JSON;
import com.wangx.oj.entity.Submit;
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

}
