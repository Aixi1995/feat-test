package com.example.strategy;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 13:46
 * @Desc 支付策略容器
 */
public class PayStrategyContainer {

    private final PayStrategy payStrategy;

    public PayStrategyContainer(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    /**
     * 支付
     */
    public void pay() {
        payStrategy.pay();
    }
}
