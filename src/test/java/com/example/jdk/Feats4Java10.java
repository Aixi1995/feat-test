package com.example.jdk;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/30 15:43
 * @Desc java10 一些新特性的测试
 */
public class Feats4Java10 {


    /**
     * java10 引入的var关键字 <br>
     * 1. 只能用在局部变量或者加强for循环 <br>
     * 2. 声明时必须初始化 <br>
     * 3. 不能用做方法签名的参数类型
     */
    @Test
    public void testVar() {
        var a = 1;
        var b = 1L;
        var c = "var";
        var feats4JDK14 = new Feats4Java14();
        var list = new ArrayList<Integer>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        var bool = false;
        Assert.assertEquals(1, a);
        Assert.assertEquals(1L, b);
        Assert.assertEquals("var", c);
        Assert.assertTrue((feats4JDK14 instanceof Feats4Java14));
        for (var i: list) {
            Assert.assertTrue((i instanceof Integer));
        }
        Assert.assertFalse(bool);
    }

}
