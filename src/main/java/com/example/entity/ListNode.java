package com.example.entity;

import lombok.Data;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/9/21 14:35
 * @desc
 */
@Data
public class ListNode<T> {

    private ListNode<T> next;
    private T t;

    public ListNode(ListNode<T> next, T t) {
        this.setT(t);
        this.setNext(next);
    }

    @Override
    public String toString() {
        return (this.getT() + "-" + (this.getNext() != null ? this.getNext().toString() : "NULL"));
    }
}
