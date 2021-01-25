package com.wangx.oj.common;

import com.alibaba.fastjson.JSON;
import com.wangx.oj.entity.Submit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {
    @RabbitListener(queues = "result")
    public void receive(String message) {
        log.info("result 接受消息-->" + message);
    }
}
