package com.example.design_patterns.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 10:06
 */
public class DoSomethingProxy implements InvocationHandler {

    private Object object;

    public DoSomethingProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object returnValue = method.invoke(object, args);
        doAfter();
        return returnValue;
    }

    private void doBefore() {
        System.out.println("do before...");
    }

    private void doAfter() {
        System.out.println("do after...");
    }
}
