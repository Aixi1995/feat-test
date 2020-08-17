package com.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/17 10:51
 * @desc
 */
@Slf4j
public class WaitAndNotifyTest {

    /**
     * 打印字符串的锁
     */
    private static final Object LOCK = new Object();
    /**
     * 控制打印位置的指针
     */
    private static volatile int index = 0;
    /**
     * 打印的线程池
     */
    static ExecutorService executor = new ThreadPoolExecutor(2, 5, 1L,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        var str = "我是个大帅逼";
        var strs = str.split("");
        executor.execute(() -> {
            try {
                printStringA(strs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                printStringB(strs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }

    public static void printStringA(String[] strs) throws InterruptedException {
        while (strs.length > index) {
            synchronized (LOCK) {
                if (index % 2 == 0) {
                    log.info(Thread.currentThread().getName() + ": " + strs[index]);
                    index++;
                    // notify 随机唤醒一个持有该锁的其他线程
                    LOCK.notify();
                } else {
                    // 阻塞当前线程
                    LOCK.wait();
                }
            }
        }
    }

    public static void printStringB(String[] strs) throws InterruptedException {
        while (strs.length > index) {
            synchronized (LOCK) {
                if (index % 2 == 1) {
                    log.info(Thread.currentThread().getName() + ": " + strs[index]);
                    index++;
                    LOCK.notify();
                } else {
                    LOCK.wait();
                }
            }
        }
    }
}
