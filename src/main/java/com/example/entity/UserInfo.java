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

    public UserInfo(Long itemId, String name, String email, Integer age) {
        this.itemId = itemId;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserInfo(Long itemId, String name, String email, Integer age, Integer score) {
        this.itemId = itemId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.score = score;
    }

    private Long itemId;

    private String name;

    private String email;

    private Integer age;

    private Integer score;

    private String addr;

    public static class UserInfoBuider {

        private Long itemId;

        private String name;

        private String email;

        private Integer age;

        private Integer score;

        private String addr;
        UserInfo userInfo = new UserInfo();
        public Long getItemId() {
            return itemId;
        }

        public UserInfoBuider setItemId(Long itemId) {
            userInfo.setItemId(itemId);
            return this;
        }

        public String getName() {
            return name;
        }

        public UserInfoBuider setName(String name) {
            userInfo.setName(name);
            return this;
        }

        public String getEmail() {
            return email;
        }

        public UserInfoBuider setEmail(String email) {
            userInfo.setEmail(email);
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public UserInfoBuider setAge(Integer age) {
            userInfo.setAge(age);
            return this;
        }

        public Integer getScore() {
            return score;
        }

        public UserInfoBuider setScore(Integer score) {
            userInfo.setScore(score);
            return this;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public UserInfo build() {
            return userInfo;
        }
    }

}
