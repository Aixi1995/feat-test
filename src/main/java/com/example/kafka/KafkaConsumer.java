package com.example.kafka;

import com.example.entity.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/12/18 16:57
 * @description
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("kafka consumer listen start...");
        Optional.ofNullable(record.value()).ifPresent(m -> {
            Gson gson = new GsonBuilder().create();
            log.info("consumer get a msg: {}", gson.fromJson(m.toString(), UserInfo.class));
        });
    }
}
