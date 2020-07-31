package com.example.utils;

import com.example.entity.UserInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/31 11:01
 */
@SpringBootTest
class RedisUtilTest {

    @Resource
    private RedisUtil redisUtil;

    private static final String K_USER_PREFIX = "user:";

    @Test
    public void testRedisUtil() {
        redisUtil.set("int", Integer.MAX_VALUE);
        redisUtil.set("string", String.valueOf("1"));
        redisUtil.set("double", Double.MAX_VALUE);
        redisUtil.set("float", Float.MAX_VALUE);
        redisUtil.set("long", Long.MAX_VALUE);
        redisUtil.hset(K_USER_PREFIX + "1", "name", "wangzhiqiang");
        redisUtil.hset(K_USER_PREFIX + "1", "email", "123@qq.com");
        redisUtil.hset(K_USER_PREFIX + "1", "id", 1);
        redisUtil.hset(K_USER_PREFIX + "1", "age", 25);
    }

    @Test
    public void testGet() {
        Assert.assertTrue(redisUtil.get("int") instanceof Integer);
        Assert.assertTrue(redisUtil.get("string") instanceof String);
        Assert.assertTrue(redisUtil.get("double") instanceof Double);
        Assert.assertTrue(redisUtil.get("float") instanceof Double);
        Assert.assertTrue(redisUtil.get("long") instanceof Long);
        Assert.assertEquals("wangzhiqiang", redisUtil.hget("user:1", "name"));
        Assert.assertEquals(25, redisUtil.hget("user:1", "age"));

        var user = new UserInfo(2L, "wang2", "2@email", 24);
        //redisUtil.set(K_USER_PREFIX + user.getId(), user);
        var _user = redisUtil.get(K_USER_PREFIX + user.getId());
        System.out.println(_user.toString());
    }
}