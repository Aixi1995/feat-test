package com.example.design_patterns;

import com.example.strategy.AliPayStrategy;
import com.example.strategy.PayStrategyContainer;
import com.example.strategy.UnionPayStrategy;
import com.example.strategy.WeChatPayStrategy;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 13:49
 */
public class StrategyTest {

    @Test
    public void testStrategy() {
        PayStrategyContainer payStrategyContainer = new PayStrategyContainer(new WeChatPayStrategy());
        payStrategyContainer.pay();
        payStrategyContainer = new PayStrategyContainer(new AliPayStrategy());
        payStrategyContainer.pay();
        payStrategyContainer = new PayStrategyContainer(new UnionPayStrategy());
        payStrategyContainer.pay();
    }
}
