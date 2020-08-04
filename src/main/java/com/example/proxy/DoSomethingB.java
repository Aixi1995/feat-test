package com.example.proxy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 9:56
 */
public class DoSomethingB implements IDoSomething{
    @Override
    public void doSomething() {
        System.out.println("do B...");
    }
}
