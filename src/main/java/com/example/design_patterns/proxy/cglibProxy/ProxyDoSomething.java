package com.example.design_patterns.proxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 10:29
 */
public class ProxyDoSomething implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        doBefore();
        Object object = methodProxy.invokeSuper(o, objects);
        doAfter();
        return null;
    }

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    private void doBefore() {
        System.out.println("do before...");
    }

    private void doAfter() {
        System.out.println("do after...");
    }
}
