package com.example.jdk;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/29 10:54
 * @Desc jdk14的一些特性
 */
public class Feats4Java14 {

    /**
     * java14 对 instanceof 支持直接转型，不需要判断后再强转
     */
    @Test
    public void testInstanceOf() {
        Object str = "hello world";
        if (str instanceof String _str) {
            Assert.assertTrue(_str.contains("hello"));
        }
    }

    // recode...
}
