package com.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/17 10:51
 * @desc
 */
@Slf4j
public class WaitAndNotifyTest {

    private static final Object LOCK = new Object();
    private static volatile int index = 0;
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        var str = "我是个大帅逼";
        var strs = str.split("");
        executor.execute( () -> {
            try {
                printStringA(strs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute( () -> {
            try {
                printStringB(strs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void printStringA(String[] strs) throws InterruptedException {
        while (strs.length > index) {
            synchronized (LOCK) {
                if (index % 2 == 0) {
                    log.info(Thread.currentThread().getName() + ": " +strs[index]);
                    index++;
                    LOCK.notify();
                } else {
                    LOCK.wait();
                }
            }
        }
    }

    public static void printStringB(String[] strs) throws InterruptedException {
        while (strs.length > index) {
            synchronized (LOCK) {
                if (index % 2 == 1) {
                    log.info(Thread.currentThread().getName() + ": " +strs[index]);
                    index++;
                    LOCK.notify();
                } else {
                    LOCK.wait();
                }
            }
        }
    }
}
