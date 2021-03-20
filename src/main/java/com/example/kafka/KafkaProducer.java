package com.example.kafka;

import com.example.entity.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/12/18 15:35
 * @description
 */
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 向kafka发送用户实体
     *
     * @param u 用户信息
     */
    public void send(UserInfo u) {
        Gson gson = new GsonBuilder().create();
        kafkaTemplate.send("test", gson.toJson(u));
        log.info("producer send a msg: {}", u.toString());
    }
}
