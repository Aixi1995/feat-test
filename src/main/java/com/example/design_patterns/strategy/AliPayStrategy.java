package com.example.design_patterns.strategy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 13:35
 * @Desc 支付宝支付策略
 */
public class AliPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.out.println("use aliPay...");
    }
}
