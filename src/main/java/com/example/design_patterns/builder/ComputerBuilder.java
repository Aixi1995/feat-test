package com.example.design_patterns.builder;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 14:13
 */
public class ComputerBuilder {

    private Computer computer;

    public ComputerBuilder(String cpu, String ram, String usbCount) {
        computer = new Computer();
        computer.setCpu(cpu);
        computer.setRam(ram);
        computer.setUsbCount(usbCount);
    }

    public ComputerBuilder setMouse(String mouse) {
        computer.setMouse(mouse);
        return this;
    }

    public ComputerBuilder setScreen(String screen) {
        computer.setScreen(screen);
        return this;
    }

    public ComputerBuilder setKeyboard(String keyboard) {
        computer.setKeyboard(keyboard);
        return this;
    }

    public Computer build() {
        return computer;
    }
}
