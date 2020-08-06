package com.example.utils;

import com.example.entity.UserInfo;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/6 17:13
 * @desc
 */
public class ExcelUtilTest {

    @Test
    public void testRead() {
        String filePath = "D:\\userInfo.xlsx";
        ExcelUtils.read(filePath, UserInfo.class).forEach(System.out::println);
    }
}
