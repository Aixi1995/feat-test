package com.example.elasticsearch.DO;

import java.util.Objects;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 13:10
 */
public class MyIndexDO {

    private String addr;
    private String name;
    private Integer age;
    private Integer itemId;
    private Integer score;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyIndexDO myIndexDO = (MyIndexDO) o;
        return Objects.equals(addr, myIndexDO.addr) &&
                Objects.equals(name, myIndexDO.name) &&
                Objects.equals(age, myIndexDO.age) &&
                Objects.equals(itemId, myIndexDO.itemId) &&
                Objects.equals(score, myIndexDO.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addr, name, age, itemId, score);
    }

    @Override
    public String toString() {
        return "myIndexDO{" +
                "addr='" + addr + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", itemId=" + itemId +
                ", score=" + score +
                '}';
    }
}
