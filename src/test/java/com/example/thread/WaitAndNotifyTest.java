package com.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/18 9:35
 * @desc
 */
@Slf4j
public class WaitAndNotifyTest {

    /**
     * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
     * 用join()阻塞主线程，等待执行完加入到主线程
     */
    @Test
    public void testPrint() throws InterruptedException {
        Thread t1 = new Thread( () -> {
            Thread.currentThread().setName("Thread-t1");
            log.info("t1 start");
        });
        Thread t2 = new Thread( () -> {
            Thread.currentThread().setName("Thread-t2");
            log.info("t2 start");
        });
        Thread t3 = new Thread( () -> {
            Thread.currentThread().setName("Thread-t3");
            log.info("t3 start");
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }

    private boolean flag = true;
    private int index = 0;
    /**
     *  两个线程轮流打印数字，一直到10
     */
    @Test
    public void testPrint2() throws InterruptedException {
        Thread t1 = new Thread( () -> {
            Thread.currentThread().setName("Thread-1");
            while (index <= 10) {
                synchronized(this) {
                    if (flag) {
                        log.info(Thread.currentThread().getName() + ":" + index);
                        index++;
                        flag = false;
                        this.notify();
                    } else {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread( () -> {
            Thread.currentThread().setName("Thread-2");
            while (index <= 10) {
                synchronized(this) {
                    if (!flag) {
                        log.info(Thread.currentThread().getName() + ":" + index);
                        index++;
                        flag = true;
                        this.notify();
                    } else {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private int index4Print3Number = 0;
    private int index4Print3Word = 0;
    /**
     *  写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z
     */
    @Test
    public void testPrint3() throws InterruptedException {
        Thread t1 = new Thread( () -> {
            int i = 1;
            while (index4Print3Number < 26) {
                synchronized(this) {
                    if (flag) {
                        System.out.print(String.valueOf(i) + (++i));
                        i++;
                        index4Print3Number++;
                        flag = false;
                        this.notify();
                    } else {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread( () -> {
            while (index4Print3Word < 26) {
                synchronized (this) {
                    if (!flag) {
                        System.out.print((char)(65+(index4Print3Word++)));
                        flag = true;
                        this.notify();
                    } else {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
