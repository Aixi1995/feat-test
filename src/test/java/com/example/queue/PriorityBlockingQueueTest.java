package com.example.queue;

import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/17 15:16
 * @desc 优先阻塞队列
 */
@Slf4j
public class PriorityBlockingQueueTest {

    /**
     * 测试传入对象。用对象的字段作为比较器
     */
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<UserInfo> priorityQueue = new PriorityBlockingQueue<>(4, Comparator.comparingInt(UserInfo::getAge));
        UserInfo u1 = new UserInfo().setAge(25).setName("wang1");
        UserInfo u4 = new UserInfo().setAge(28).setName("wang4");
        UserInfo u2 = new UserInfo().setAge(24).setName("wang2");
        UserInfo u3 = new UserInfo().setAge(26).setName("wang3");
        priorityQueue.add(u1);
        priorityQueue.add(u2);
        priorityQueue.add(u3);
        priorityQueue.add(u4);
        new Thread( () -> {
            while (true) {
                try {
                    log.info("" + priorityQueue.take());//take时，如果列表为空则会阻塞，而poll会一直返回null
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    @Test
    public void testPriorityBlockingQueueDesc() throws InterruptedException {

    }
}
