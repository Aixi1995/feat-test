package com.example.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/6 16:38
 * @desc excel工具类
 */
public class ExcelUtils {

    /**
     * 从第二行开始读取excel，按照T类型来序列化
     *
     * @param fileName excel
     * @param clazz Java对象类型
     * @param <T> Java对象类型
     * @return 读取的结果集list
     */
    public static <T> List<T> read(String fileName, Class<T> clazz) {
        CommonListener<T> commonListener = new CommonListener<>();
        EasyExcel.read(fileName, clazz, commonListener).sheet().doRead();
        return commonListener.tList;
    }
}
