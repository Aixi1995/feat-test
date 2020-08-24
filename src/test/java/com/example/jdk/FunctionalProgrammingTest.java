package com.example.jdk;

import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
