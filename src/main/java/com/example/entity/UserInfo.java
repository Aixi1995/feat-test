package com.example.entity;

import lombok.Data;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/29 13:28
 */
@Data
public class UserInfo {

    public UserInfo() {
    }

    public UserInfo(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    private Long id;

    private String name;

    private String email;

    private Integer age;

}
