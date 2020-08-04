package com.example.design_patterns;

import com.example.design_patterns.singleton.Singleton;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/3 16:13
 */
public class SingletonTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void testSingletonBySync() {
        executorService.execute(() -> System.out.println(Singleton.getInstanceBySync().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getInstanceBySync().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getInstanceBySync().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getInstanceBySync().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getInstanceBySync().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getInstanceBySync().hashCode()));
    }

    @Test
    public void testSingletonByInnerClass() {
        executorService.execute(() -> System.out.println(Singleton.getSingletonByInnerClass().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getSingletonByInnerClass().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getSingletonByInnerClass().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getSingletonByInnerClass().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getSingletonByInnerClass().hashCode()));
        executorService.execute(() -> System.out.println(Singleton.getSingletonByInnerClass().hashCode()));
    }
}
