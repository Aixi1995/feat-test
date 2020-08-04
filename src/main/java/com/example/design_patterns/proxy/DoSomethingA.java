package com.example.design_patterns.proxy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 9:53
 */
public class DoSomethingA implements IDoSomething{
    @Override
    public void doSomething() {
        System.out.println("do A...");
    }
}
