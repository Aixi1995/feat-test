package com.example.design_patterns;

import com.example.design_patterns.builder.Computer;
import com.example.design_patterns.builder.ComputerBuilder;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 14:21
 */
public class BuilderTest {

    @Test
    public void testBuilder() {
        Computer computer = new ComputerBuilder("Core i7", "16G", "3个USB")
                .setKeyboard("ikcb")
                .setMouse("logitech")
                .setScreen("sunsang")
                .build();
        System.out.println(computer.toString());

        Computer computer1 = new ComputerBuilder("Core i7", "16G", "3个USB")
                .setKeyboard("ikcb")
                .setMouse("logitech")
                .build();
        System.out.println(computer1.toString());
    }
}
