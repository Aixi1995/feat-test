package com.example.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/6 17:00
 * @desc
 */
public class CommonListener<T> extends AnalysisEventListener<T> {

    private static final Logger logger = LoggerFactory.getLogger(CommonListener.class);
    List<T> tList = null;

    @Override
    public void invoke(T data, AnalysisContext context) {
        tList = new ArrayList<>();
        tList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("all data has been read");
    }
}
