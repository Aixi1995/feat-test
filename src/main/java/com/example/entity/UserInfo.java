package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/29 13:28
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    public UserInfo() {
    }

    public UserInfo(String itemId, String name, String email, Integer age) {
        this.itemId = itemId;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserInfo(String itemId, String name, String email, Integer age, Integer score) {
        this.itemId = itemId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.score = score;
    }

    private String itemId;

    private String name;

    private String email;

    private Integer age;

    private Integer score;

    private String addr;


}
