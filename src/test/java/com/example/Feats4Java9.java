package com.example;

import org.junit.Test;

import java.util.List;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/30 8:47
 * @desc java9改动：模块系统，JShell，改进了javadoc，集合、Optional类， Stream API，进程API，CompletableFuture API等等
 */
public class Feats4Java9 {


    /**
     * Java 8 引入了 CompletableFuture<T> 类，可能是 java.util.concurrent.Future<T> 明确的完成版（设置了它的值和状态），<br>
     * 也可能被用作java.util.concurrent.CompleteStage。支持 future 完成时触发一些依赖的函数和动作。<br>
     * Java 9 对 CompletableFuture 做了改进：<br>
     * 1. 支持 delays 和 timeouts<br>
     * 2. 提升了对子类化的支持<br>
     * 3. 新的工厂方法<br>
     */
    @Test
    public void testCompletableFuture() {

    }

    /**
     * 测试java9引入的新的集合创建方式
     */
    @Test
    public void testCollections() {
        var list = List.of(1,2,3,4,5,6,7,123,125,13,12323);
        list.stream().distinct().sorted(Integer::compareTo).forEach(System.out::println);
    }

    /**
     * 进程相关的api测试
     */
    @Test
    public void testProcess() {

    }


}
