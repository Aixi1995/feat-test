package com.example.strategy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 13:41
 * @Desc 银联支付策略
 */
public class UnionPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.out.println("use unionPay...");
    }
}
