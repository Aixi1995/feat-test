package com.example.designPattern;

import com.example.proxy.DoSomethingA;
import com.example.proxy.DoSomethingB;
import com.example.proxy.staticProxy.ProxyDoSomething;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 9:54
 */
public class StaticProxyTest {

    @Test
    public void testStaticProxy() {
        ProxyDoSomething doSomethingA = new ProxyDoSomething(new DoSomethingA());
        doSomethingA.doSomething();
        ProxyDoSomething doSomethingB = new ProxyDoSomething(new DoSomethingB());
        doSomethingB.doSomething();
    }
}
