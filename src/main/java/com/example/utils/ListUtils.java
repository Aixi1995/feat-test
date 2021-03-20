package com.example.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/11/25 14:35
 * @description
 */
public class ListUtils {

    public static <T> List<List<T>> subList(int length, List<T> list) {
        int size = list.size();
        int temp = size / length + 1;
        boolean result = size % length == 0;
        List<List<T>> subList = new ArrayList<>();
        for (int i = 0; i < temp; i++) {
            if (i == temp - 1) {
                if (result) {
                    break;
                }
                subList.add(list.subList(length * i, size));
            } else {
                subList.add(list.subList(length * i, length * (i + 1)));
            }
        }
        return subList;
    }
}
