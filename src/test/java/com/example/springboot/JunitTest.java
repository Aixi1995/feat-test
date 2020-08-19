package com.example.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/18 15:12
 * @desc
 */
@Slf4j
@DisplayName("测试Junit5")
public class JunitTest {

    @Test
    @DisplayName("测试junit5的test注解")
    public void test() {
        log.info("测试junit");
    }

    @Test
    @DisplayName("测试Junit5中的断言")
    public void testAssertions() {
        Assertions.assertEquals(1, 1);
        Assertions.assertNotEquals(1, 2);
        Assertions.assertTrue(true);
        Assertions.assertFalse(false);
        Assertions.assertSame("1", "1");
    }

    //todo 继续测试junit5的新特性
}
