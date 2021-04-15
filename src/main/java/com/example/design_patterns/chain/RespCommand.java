package com.example.design_patterns.chain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2021/3/20 13:34
 * @description
 */
@Slf4j
public class RespCommand implements Command {


    @Override
    public boolean execute(Context context) throws Exception {
        log.info("resp start...");
        return false;
    }
}
