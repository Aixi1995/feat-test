package com.example.jdk;

import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/30 16:05
 */
public class Feats4Java12 {

    /**
     * java12 switch可以有返回值了
     */
    @Test
    public void testSwitch() {
        var grade = "A";
        String _grade = switch (grade) {
            case "A" -> "优秀";
            case "B" -> "良好";
            case "C", "D" -> "继续努力";
            default -> "unknown";
        };
        System.out.println(_grade);
    }
}
