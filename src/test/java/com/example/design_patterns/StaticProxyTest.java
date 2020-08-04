package com.example.design_patterns;

import com.example.design_patterns.proxy.DoSomethingA;
import com.example.design_patterns.proxy.DoSomethingB;
import com.example.design_patterns.proxy.staticProxy.DoSomethingProxy;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 9:54
 */
public class StaticProxyTest {

    @Test
    public void testStaticProxy() {
        DoSomethingProxy doSomethingA = new DoSomethingProxy(new DoSomethingA());
        doSomethingA.doSomething();
        DoSomethingProxy doSomethingB = new DoSomethingProxy(new DoSomethingB());
        doSomethingB.doSomething();
    }
}
