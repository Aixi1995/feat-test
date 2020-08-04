package com.example.design_patterns.strategy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 13:36
 * @Desc 微信支付策略
 */
public class WeChatPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.out.println("use wechatPay...");
    }
}
