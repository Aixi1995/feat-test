package com.example.design_patterns.factory;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 11:09
 */
public class ChinaFruitFactory implements FruitFactory{
    @Override
    public Fruit getApple() {
        return new ChinaApple();
    }
}
