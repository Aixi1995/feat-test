package com.example.design_patterns;

import com.example.design_patterns.proxy.IDoSomething;
import com.example.design_patterns.proxy.jdkProxy.ProxyDoSomething;
import com.example.design_patterns.proxy.DoSomethingA;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 10:13
 */
public class JDKProxyTest {

    /**
     * 利用java反射实现的jdk动态代理
     */
    @Test
    public void testJDKProxy() {
        IDoSomething doSomethingA = new DoSomethingA();
        ClassLoader loader = doSomethingA.getClass().getClassLoader();
        Class[] interfaces = doSomethingA.getClass().getInterfaces();
        InvocationHandler handler = new ProxyDoSomething(doSomethingA);
        IDoSomething doSomethingProxy = (IDoSomething) Proxy.newProxyInstance(loader, interfaces, handler);
        doSomethingProxy.doSomething();
    }
}
