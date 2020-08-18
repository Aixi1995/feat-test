package com.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/18 10:46
 * @desc 每个Thread内部都有一个Map，我们每当定义一个ThreadLocal变量，就相当于往这个Map里放了一个key，并定义一个对应的value。每当使用ThreadLocal，就相当于get(key)，寻找其对应的value。
 */
@Slf4j
public class ThreadLocalTest {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    public void testThreadLocal() throws InterruptedException {
        threadLocal.set(Thread.currentThread().getName() + ": main");
        Thread t1 = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName() + ": t1");
            log.info(threadLocal.get());
            threadLocal.remove();
        });
        Thread t2 = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName() + ": t2");
            log.info(threadLocal.get());
            threadLocal.remove();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info(threadLocal.get());
        threadLocal.remove();
    }

}
