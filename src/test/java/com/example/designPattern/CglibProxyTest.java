package com.example.designPattern;

import com.example.proxy.DoSomethingA;
import com.example.proxy.DoSomethingC;
import com.example.proxy.cglibProxy.ProxyDoSomething;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 10:35
 */
public class CglibProxyTest {

    /**
     * 利用cglib实现动态代理，相比于jdk的动态代理，cglib可以代理没有实现接口的类，实现也比jdk动态代理简单
     */
    @Test
    public void testCglibTest() {
        ProxyDoSomething proxyDoSomething = new ProxyDoSomething();
        DoSomethingC doSomethingC = (DoSomethingC) proxyDoSomething.getProxy(DoSomethingC.class);
        doSomethingC.doSomething();
    }
}
