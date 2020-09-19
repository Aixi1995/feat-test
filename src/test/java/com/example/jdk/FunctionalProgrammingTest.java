package com.example.jdk;

import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.security.auth.callback.Callback;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/22 17:54
 * @desc 测试函数式编程的相关接口api
 */
@Slf4j
public class FunctionalProgrammingTest {

    /**
     * 消费型函数式编程，传入一个参数，消费参数
     */
    @Test
    public void testConsumer() {
        Consumer<Integer> consumer = c -> log.info(c + "Consumer, 消费型有一个入参无返回");
        consumer.accept(1);
    }

    /**
     * 供给型函数式编程，没有入参，返回一个值
     */
    @Test
    public void testSupplier() {
        Supplier<String> supplier = () -> "Supplier, 供给型没有入参但有一个返回值";
        log.info(supplier.get());
    }

    /**
     * 功能型函数式编程，将输入数据转换成另一种形式的输出数据
     */
    @Test
    public void testFunction() {
        Function<Integer, String> f1 = String::valueOf;
        f1.apply(1);
        ToIntFunction<String> f2 = Integer::parseInt;//转换成int 相当于Function<T, Integer>
        f2.applyAsInt("1");
        BiFunction<String, String, Integer> f3 = (s1, s2) -> Integer.parseInt(s1) + Integer.parseInt(s2);
        int i = f3.apply("1", "2");
        log.info("f3: {}", i);
    }

    /**
     * 断言型的函数式编程，接受一个入参，返回true或者false
     */
    @Test
    public void testPredict() {
        Predicate<Integer> p1 = i -> i > 25;
        Assertions.assertTrue(p1.test(26));
        Predicate<UserInfo> p2 = userInfo -> userInfo.getAge() > 25;
        UserInfo userInfo = new UserInfo().setAge(26).setName("wang");
        Assertions.assertTrue(p2.test(userInfo));
        BiPredicate<UserInfo, Integer> p3 = (u, age) -> u.getAge() > age;
        Assertions.assertTrue(p3.test(userInfo, 24));
    }

    @Test
    public void testFunctional() {
        ThreadLocal<DateTimeFormatter> threadLocal = ThreadLocal.withInitial( () -> DateTimeFormatter.BASIC_ISO_DATE);
        log.info(threadLocal.get().format(LocalDateTime.now()));
        Runnable helloWorld=()-> System.out.println("hello world");
        helloWorld.run();
    }

    @Test
    public void testBuild() {
        UserInfo userInfo = new UserInfo.UserInfoBuider().setItemId(1L).setName("wang").build();
        log.info(userInfo.toString());
    }

    @Test
    public void testIterator() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1);list1.add(2);list1.add(3);list1.add(4);list1.add(5);
        list2.add(4);list2.add(6);list2.add(5);list2.add(7);list2.add(8);
        Iterator<Integer> i1 = list1.iterator();
        while (i1.hasNext()) {
            Integer a = i1.next();
            Iterator<Integer> i2 = list2.iterator();
            while (i2.hasNext()) {
                Integer b = i2.next();
                if (a.equals(b)) {
                    i1.remove();
                    i2.remove();
                }
            }
        }
        Iterator<Integer> i3 = list1.iterator();
        while (i3.hasNext()) {
            Integer a = i3.next();
            System.out.println(a);
        }
        Iterator<Integer> i4 = list2.iterator();
        while (i4.hasNext()) {
            Integer b = i4.next();
            System.out.println(b);
        }
    }

    public Executor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(3);
        //配置队列大小
        threadPoolTaskExecutor.setQueueCapacity(5);
        //配置线程池前缀
        threadPoolTaskExecutor.setThreadNamePrefix("task-");
        //拒绝策略
        // 抛出异常策略 AbortPolicy
        // 丢弃但不抛异常 DiscardPolicy
        // 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程） DiscardOldestPolicy
        // 由调用线程处理该任务  CallerRunsPolicy
        threadPoolTaskExecutor.setRejectedExecutionHandler((new ThreadPoolExecutor.CallerRunsPolicy()));
        threadPoolTaskExecutor.initialize();
        log.info("create the thread pool successfully, name-prefix : {}", threadPoolTaskExecutor.getThreadNamePrefix());
        return threadPoolTaskExecutor;
    }

    @Test
    public void testCompleteService() throws InterruptedException, ExecutionException {
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) taskExecutor();
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(taskExecutor);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            completionService.submit( () -> finalI);
        }
        for (int i = 0; i < 20; i++) {
            log.info(String.valueOf(completionService.take().get()));
        }
        //Thread.sleep(5000);
    }

    @Test
    public void testCompleteService1() throws InterruptedException, ExecutionException {
        //ExecutorService executorService1 = Executors.newFixedThreadPool(6);
        // newFixedThreadPool 没有指定的任务队列大小是Integer.MAX_VALUE 因此会无限堆积任务
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            completionService.submit( () -> finalI);
        }
        for (int i = 0; i < 20; i++) {
            log.info(String.valueOf(completionService.take().get()));
        }
        //Thread.sleep(5000);
    }

}
