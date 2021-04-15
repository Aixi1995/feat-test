package com.example.design_patterns.chain;

import org.apache.commons.chain.impl.ChainBase;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2021/3/20 13:35
 * @description
 */
public class CommonChain extends ChainBase {

    public CommonChain() {
        super();
        addCommand(new ValidateCommand());
        addCommand(new CleanCommand());
        addCommand(new MatchCommand());
        addCommand(new RespCommand());
    }
}
