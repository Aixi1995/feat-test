package com.example.strategy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 13:34
 * @desc 支付策略的接口
 */
public interface PayStrategy {

    /**
     * 支付方法，不同策略的支付实现不同
     */
    void pay();

}
