package com.example.design_patterns.builder;

import java.util.Objects;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 14:09
 */
public class Computer {

    //必选属性
    /**
     * cpu
     */
    private String cpu;
    /**
     * 内存
     */
    private String ram;
    /**
     * usb数目
     */
    private String usbCount;

    //非必选属性

    /**
     * 显示器
     */
    private String screen;
    /**
     * 键盘
     */
    private String keyboard;
    /**
     * 鼠标
     */
    private String mouse;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getUsbCount() {
        return usbCount;
    }

    public void setUsbCount(String usbCount) {
        this.usbCount = usbCount;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Computer computer = (Computer) o;
        return Objects.equals(cpu, computer.cpu) &&
                Objects.equals(ram, computer.ram) &&
                Objects.equals(usbCount, computer.usbCount) &&
                Objects.equals(screen, computer.screen) &&
                Objects.equals(keyboard, computer.keyboard) &&
                Objects.equals(mouse, computer.mouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, ram, usbCount, screen, keyboard, mouse);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", usbCount='" + usbCount + '\'' +
                ", screen='" + screen + '\'' +
                ", keyboard='" + keyboard + '\'' +
                ", mouse='" + mouse + '\'' +
                '}';
    }
}
