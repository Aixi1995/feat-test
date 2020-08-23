package com.example.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
        Consumer<Integer> consumer = c -> log.info(c.toString());
        consumer.accept(1);
    }

    /**
     * 供给型函数式编程，没有入参，返回一个值
     */
    @Test
    public void testSupplier() {
        Supplier<String> supplier = () -> "supplier没有入参，有一个返回值";
        log.info(supplier.get());
    }

    /**
     * 功能型函数式编程，将输入数据转换成另一种形式的输出数据
     */
    @Test
    public void testFunction() {
        Function<Integer, String> function = x -> {
            String s = String.valueOf(x);
            log.info(s);
            return s;
        };
        function.apply(1);
    }
}
