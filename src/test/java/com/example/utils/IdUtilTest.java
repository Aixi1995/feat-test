package com.example.utils;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/9/7 15:01
 * @desc
 */
public class IdUtilTest {

    public Set<Long> set = new HashSet<>();

    @Test
    public void testGenId() throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            set.add(IdUtil.nextId());
            Thread.sleep(2L);
        }
        System.out.println(set.size());
    }
}
