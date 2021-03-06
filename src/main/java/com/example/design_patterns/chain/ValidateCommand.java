package com.example.design_patterns.chain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2021/3/20 13:27
 * @description
 */
@Slf4j
public class ValidateCommand implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        log.info("validate start...");
        return false;
    }
}
