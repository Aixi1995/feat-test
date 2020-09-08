package com.example.sort;

import com.example.algorithm.Sort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/9/3 17:51
 * @desc
 */
public class SortTest {

    public static int[] nums;

    @BeforeAll
    public static void init() {
        nums = new int[]{4,5,7,1,8,0,12};
    }

    @Test
    public void testSortByBubble() {
        System.out.println(Arrays.toString(Sort.sortByBubble(nums)));
    }

    @Test
    public void testSortBySelection() {
        System.out.println(Arrays.toString(Sort.sortBySelection(nums)));
    }
}
