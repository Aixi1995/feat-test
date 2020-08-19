package com.example.reflect;

import com.example.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/19 11:03
 * @desc
 */
@Slf4j
public class ReflectTest {

    /**
     * 使用 Class.foName 创建对象
     */
    @Test
    public void testClassForName() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName("com.example.entity.UserInfo");
        log.info(c.getName());
        var user = (UserInfo) c.getConstructor().newInstance();
        user.setAge(1).setName("wang");
        log.info(user.toString());
    }

    /**
     * 使用类的class来创建对象
     */
    @Test
    public void testClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = UserInfo.class;
        var user = (UserInfo) c.getConstructor().newInstance();
        user.setName("wang1").setAge(25);
        log.info(user.toString());
    }

    /**
     * 通过反射获取对象属性，并且可以获取私有属性
     */
    @Test
    public void testGetAllProperties() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class c = UserInfo.class;
        var user = (UserInfo) c.getConstructor().newInstance();
        user.setName("wang1").setAge(25);
        var fields = c.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);//设置私有属性可达
            log.info(field.getName());
            if (field.getName().contains("name")) {
                try {
                    log.info((String)field.get(user));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 使用java反射来获取对象，以及获取对象的方法来执行方法
     */
    @Test
    public void testMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = UserInfo.class;
        var user = (UserInfo) c.getConstructor().newInstance();
        user.setName("wang1").setAge(25);
        var getAge = c.getMethod("getAge");
        var getName = c.getMethod("getName");
        var age = getAge.invoke(user);
        var name = getName.invoke(user);
        log.info(age + "," + name);
    }

}
