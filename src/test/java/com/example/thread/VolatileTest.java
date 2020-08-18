package com.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/18 10:22
 * @desc 使用volatile
 */
@Slf4j
public class VolatileTest {

    private volatile boolean flag4Volatile = true;
    private int index = 0;

    /**
     *  两个线程轮流打印数字，一直到10。使用volatile来实现线程间通信
     */
    @Test
    public void testPrint2ByVolatile() throws InterruptedException {
        Thread t1 = new Thread( () -> {
            while (index < 10) {
                if (flag4Volatile) {
                    log.info(Thread.currentThread().getName() + ": " + index++);
                    flag4Volatile = false;
                }
            }
        });

        Thread t2 = new Thread( () -> {
            while (index < 10) {
                if (!flag4Volatile) {
                    log.info(Thread.currentThread().getName() + index++);
                    flag4Volatile = true;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * 使用volatile来实现两个线程交替打印一个字符串
     * @throws InterruptedException
     */
    @Test
    public void testVolatile() throws InterruptedException {
        var str = "我是个大帅逼";
        var strs = str.split("");
        Thread t1 = new Thread( () -> {
            while (index < strs.length) {
                if (flag4Volatile) {
                    log.info(Thread.currentThread().getName() + ": " + strs[index++]);
                    flag4Volatile = false;
                }
            }
        });

        Thread t2 = new Thread( () -> {
            while (index < strs.length) {
                if (!flag4Volatile) {
                    log.info(Thread.currentThread().getName() + ": " + strs[index++]);
                    flag4Volatile = true;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
