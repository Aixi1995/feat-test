package com.example.utils;

import com.alibaba.excel.EasyExcel;
import com.example.entity.UserInfo;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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

    @Test
    public void testWrite() throws FileNotFoundException {
        /*String filePath = "D:\\userInfo_write.xlsx";
        var user1 = new UserInfo(1L, "wang1", "wang1@163.com", 27);
        var user2 = new UserInfo(1L, "wang1", "wang1@163.com", 24);
        var user3 = new UserInfo(1L, "wang1", "wang1@163.com", 26);
        var user4 = new UserInfo(1L, "wang1", "wang1@163.com", 23);
        var list = new ArrayList<UserInfo>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        ExcelUtils.write(filePath, UserInfo.class, list);*/
    }
}
