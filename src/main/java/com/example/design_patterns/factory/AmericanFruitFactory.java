package com.example.design_patterns.factory;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 11:12
 */
public class AmericanFruitFactory implements FruitFactory{
    @Override
    public Fruit getApple() {
        return new AmericanApple();
    }
}
