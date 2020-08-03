package com.example.model;

import java.util.Optional;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/3 15:54
 * @desc 单例模式的测试类
 */
public class Singleton {

    public static Singleton singleton = null;

    /**
     * 私有构造器
     */
    private Singleton() {

    }

    /**
     * 双层检查锁的形式创建单例
     * @return 单例
     */
    public static Singleton getInstanceBySync() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = Optional.ofNullable(singleton).orElseGet(Singleton::new);
            }
        }
        return singleton;
    }

    public static Singleton getSingletonByInnerClass() {
        return InnerSingleton.SINGLETON;
    }

    private static class InnerSingleton {
        private static final Singleton SINGLETON = new Singleton();
    }

}
