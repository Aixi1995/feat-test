package com.example.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Test
    public void testSecureUtil() {
        String md5 = SecureUtil.md5("1");
        log.info(md5);
        String sha256 = SecureUtil.sha256("1");
        log.info(sha256);
        /*String sm3 = SmUtil.sm3("1");
        log.info(sm3);*/
    }

    /**
     * CaptchaUtil使用：图形验证码
     */
    @Test
    public void testCaptchaUtil() {

    }

    public void captchaUtil(HttpServletRequest request, HttpServletResponse response) {
        //生成验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        try {
            request.getSession().setAttribute("CAPTCHA_KEY", lineCaptcha.getCode());
            response.setContentType("image/png");//告诉浏览器输出内容为图片
            response.setHeader("Pragma", "No-cache");//禁止浏览器缓存
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字段验证器，验证给定字符串是否满足指定条件，一般用在表单字段验证里。
     */
    @Test
    public void testValidator() {
        Assert.assertTrue(Validator.isEmail("123@qq.com"));
        Assert.assertTrue(Validator.isChinese("中国"));
        Assert.assertTrue(Validator.hasChinese("中国960万平方公里"));
        Assert.assertTrue(Validator.isMobile("17521019990"));
    }

    @Test
    public void testJSONUtil() {
        UserInfo userInfo = new UserInfo().setName("wang.zhiqiang").setAge(26)
                .setEmail("123@qq.com").setScore(100)
                .setAddr("shanghai").setItemId(1L);
        log.info(JSONUtil.parse(userInfo).toJSONString(2));
    }

    @Test
    public void testRandomUtil() {
        int result;
        String uuid;
        //获得指定范围内的随机数
        result = RandomUtil.randomInt(1, 100);
        log.info("randomInt:{}",StrUtil.toString(result));
    }

    @Test
    public void testIdUtil() {
        String id;
        id = IdUtil.fastUUID();
        log.info(id);
        id = IdUtil.randomUUID();
        log.info(id);
    }

    @Test
    public void testDigestUtil() {

    }

    @Test
    public void testHttpUtil() {
        String result1= HttpUtil.get("https://www.baidu.com");
        log.info(result1);
    }
}
