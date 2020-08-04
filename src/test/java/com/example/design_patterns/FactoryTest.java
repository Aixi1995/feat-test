package com.example.design_patterns;

import com.example.design_patterns.factory.*;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 11:10
 */
public class FactoryTest {

    @Test
    public void testFactory() {
        FruitFactory factory = new ChinaFruitFactory();
        Fruit chinaApple = factory.getApple();
        chinaApple.get();
        factory = new AmericanFruitFactory();
        factory.getApple().get();
    }
}
