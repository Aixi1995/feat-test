package com.example.design_patterns.proxy.staticProxy;

import com.example.design_patterns.proxy.IDoSomething;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 9:49
 */
public class DoSomethingProxy implements IDoSomething {

    IDoSomething doSomething;

    public DoSomethingProxy(IDoSomething doSomething) {
        super();
        this.doSomething = doSomething;
    }

    @Override
    public void doSomething() {
        doBefore();
        doSomething.doSomething();
        doAfter();
    }

    private void doBefore() {
        System.out.println("do before...");
    }

    private void doAfter() {
        System.out.println("do after...");
    }
}
