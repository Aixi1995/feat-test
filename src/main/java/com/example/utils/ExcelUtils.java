package com.example.utils;

import com.alibaba.excel.EasyExcel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    /**
     * 将list写入excel
     *
     * @param filePath excel
     * @param clazz Java对象类型
     * @param tList 待写入list
     * @param <T> Java对象类型
     * @throws FileNotFoundException 文件不存在则抛出异常
     */
    public static <T> void write(String filePath, Class<T> clazz, List<T> tList) throws FileNotFoundException {
        OutputStream out = new FileOutputStream(filePath);
        EasyExcel.write(out, clazz).sheet(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)).doWrite(tList);
    }
}
