package com.example.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/10/14 14:36
 * @description
 */
@Slf4j
public class HutoolTest {

    /**
     * 类型转换工具类，用于各种类型数据的转换。
     */
    @Test
    public void testConvert() {
        Assert.assertEquals(1, (int) Convert.toInt("1"));
        Assert.assertEquals("1", Convert.toStr(1));
        String[] str = new String[]{"1", "2", "3"};
        List<String> stringList = Convert.toList(String.class, str);
        Assert.assertEquals(str.length, stringList.size());
        String[] strs1 = Convert.toStrArray(stringList);
        Arrays.stream(strs1).forEach(log::info);
    }

    @Test
    public void testStrUtil() {
        String str = "123@qq.com";
        Assert.assertFalse(StrUtil.isBlank(str));
        Assert.assertFalse(StrUtil.isEmpty(str));
        log.info(StrUtil.removePrefix(str, "123"));
        log.info(StrUtil.removeSuffix(str, "com"));
    }

    @Test
    public void testBeanUtil() {
        UserInfo userInfo = new UserInfo().setName("wang.zhiqiang").setAge(26)
                .setEmail("123@qq.com").setScore(100)
                .setAddr("shanghai").setItemId(1L);
        // bean to map
        Map<String, Object> map = BeanUtil.beanToMap(userInfo);
        log.info(map.toString());
        // map to bean
        UserInfo userInfo1 = BeanUtil.toBean(map, UserInfo.class);
        log.info(userInfo1.toString());
        // copy bean properties
        UserInfo userInfo2 = new UserInfo();
        BeanUtil.copyProperties(userInfo1, userInfo2);
        log.info(userInfo2.toString());
    }
}
