package com.example.queue;

import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/17 14:48
 * @desc 优先队列的测试，是非阻塞的，可以用于对一批对象进行排序等。
 */
@Slf4j
public class PriorityQueueTest {

    @Test
    public void testPriorityQueueInteger() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        while (!priorityQueue.isEmpty()) {
            log.info("" + priorityQueue.poll());
        }
    }

    /**
     * 传入比较器倒序输出优先队列的内容
     */
    @Test
    public void testPriorityQueueIntegerDesc() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> (int)o2 - o1);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        /*while (true) {
            log.info("" + priorityQueue.poll());
        }*/
        while (!priorityQueue.isEmpty()) {
            log.info("" + priorityQueue.poll());
        }
    }

    /**
     * 测试传入对象。用对象的字段作为比较器
     */
    @Test
    public void testPriorityQueueUserDesc() {
        PriorityQueue<UserInfo> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(UserInfo::getAge));
        UserInfo u1 = new UserInfo().setAge(25).setName("wang1");
        UserInfo u2 = new UserInfo().setAge(27).setName("wang2");
        UserInfo u3 = new UserInfo().setAge(26).setName("wang3");
        priorityQueue.add(u1);
        priorityQueue.add(u2);
        priorityQueue.add(u3);
        while (!priorityQueue.isEmpty()) {
            log.info("" + priorityQueue.poll());
        }
    }
}
