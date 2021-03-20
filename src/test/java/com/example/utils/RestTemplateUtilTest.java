package com.example.utils;

import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 16:57
 * @desc
 */
public class RestTemplateUtilTest {

    @Test
    public void testGet() throws Exception {
        var response = RestTemplateUtil.get("http://127.0.0.1:9200", String.class, 1000, 3);
        System.out.println(response);
    }

    @Test
    public void testPost() throws Exception {
        var json = "";/*"""
                {
                    "addr":"Shanghai",
                    "age":26,
                    "email":"136@qq.com",
                    "itemId":6,
                    "score":100
                }
                """;*/
        var response = RestTemplateUtil.post("http://127.0.0.1:9200/my_index_20200805/_doc", json, String.class, 1000, 3);
        System.out.println(response);
    }
}
