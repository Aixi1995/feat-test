package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/11/25 14:35
 * @description
 */
@Slf4j
public class ListUtilsTest {

    @Test
    public void testSubList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1010; i++) {
            list.add(i);
        }
        List<List<Integer>> lists = ListUtils.subList(500, list);
        log.info("list subList: {}", lists.size());

    }
}
