package com.example.kafka;

import com.example.FeatTestApplication;
import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/12/18 15:19
 * @description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FeatTestApplication.class})
public class KafkaSendTest {

    @Resource
    private KafkaProducer kafkaProducer;

    @Resource
    private KafkaConsumer kafkaConsumer;

    @Test
    public void testSend() {
        for (int i = 0; i < 10; i++) {
            /*UserInfo u = new UserInfo().setItemId((long) i).setName("wang" + i).setAge(20+i).setScore(100);
            kafkaProducer.send(u);*/
        }
    }

}
