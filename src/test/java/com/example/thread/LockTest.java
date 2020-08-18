package com.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/18 13:10
 * @desc
 *  Synchronized和Lock比较
 *     Synchronized是关键字，内置语言实现，Lock是接口。
 *     Synchronized在线程发生异常时会自动释放锁，因此不会发生异常死锁。Lock异常时不会自动释放锁，所以需要在finally中实现释放锁。
 *     Lock是可以中断锁，Synchronized是非中断锁，必须等待线程执行完成释放锁。
 *     Lock可以使用读锁提高多线程读效率。
 */
@Slf4j
public class LockTest {

    @Test
    public void testLock() throws InterruptedException {
        Thread t1 = new Thread(this::useLock);
        Thread t2 = new Thread(this::useLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    Lock lock = new ReentrantLock();
    public void useLock() {
        lock.lock();
        try {
            log.info(Thread.currentThread().getName() + " get the lock!");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info(Thread.currentThread().getName() + " release the lock!");
            lock.unlock();
        }
    }

    private int index = 0;
    private boolean flag = true;

    @Test
    public void testPrint() throws InterruptedException {
        var string = "我是个大帅逼";
        var strings = string.split("");
        Thread t1 = new Thread( () -> {
            while (index < strings.length) {
                lock.lock();
                try {
                    if (index < strings.length) {
                        if (flag) {
                            log.info(Thread.currentThread().getName() + ": " + strings[index++]);
                            flag = false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread( () -> {
            while (index < strings.length) {
                lock.lock();
                try {
                    if (index < strings.length) {
                        if (!flag) {
                            log.info(Thread.currentThread().getName() + ": " + strings[index++]);
                            flag = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
