package com.example.design_patterns;

import com.example.design_patterns.chain.CommonChain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2021/3/20 13:37
 * @description
 */
public class CommonChainTest {

    @Test
    public void testExecute() throws Exception {
        CommonChain chain = new CommonChain();
        Context context = new ContextBase();
        context.put("name", "wangzhiqiang");
        chain.execute(context);
    }
}
