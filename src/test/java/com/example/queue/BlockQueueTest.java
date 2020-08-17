package com.example.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/17 14:21
 * @desc 阻塞队列的测试，BlockQueue有很多实现类，例如 ArrayBlockingQueue和linkedBlockingQueue
 */
@Slf4j
public class BlockQueueTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        //BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<String>(3);
        BlockingQueue<String> blockingDeque = new LinkedBlockingDeque<>();
        executorService.execute( () -> {
            try {
                Thread.currentThread().setName("producer");
                producer(blockingDeque);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute( () -> {
            try {
                Thread.currentThread().setName("consumer");
                consumer(blockingDeque);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    public static void producer(BlockingQueue<String> blockingDeque) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            blockingDeque.offer(Thread.currentThread().getName() + ": 生产者" + i);
            Thread.sleep(1000);
        }
    }

    public static void consumer(BlockingQueue<String> blockingDeque) throws InterruptedException {
        while (true) {
            log.info(blockingDeque.take());
            // log.info(blockingDeque.poll(10, TimeUnit.SECONDS));
        }
    }
}
