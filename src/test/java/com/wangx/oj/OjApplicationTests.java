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

    @Test
    public test() {
        System.out.println("hello world");
    }

}
