package com.example.algorithm;

import com.example.entity.ListNode;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/9/21 14:36
 * @desc
 */
public class ListNodeUtils<T> {

    /**
     * 使用递归的方法实现链表的反转
     *
     * @param head 原始链表
     * @preurn 反转后的链表
     */
    public ListNode<T> reverse(ListNode<T> head) {
        // 如果头元素head是null 或者链表只有head一个节点，则直接返回head
        if (head == null || head.getNext() == null) {
            return head;
        }
        // 获取下一个节点
        ListNode<T> tmp = head.getNext();
        ListNode<T> newHead = reverse(head.getNext());
        tmp.setNext(head);
        head.setNext(null);
        return newHead;
    }

    /**
     * 使用循环的方法实现链表的反转
     *
     * @param head 原始链表
     * @preurn 反转后的链表
     */
    public ListNode<T> reverseByLoop(ListNode<T> head) {
        ListNode<T> pre = null;
        ListNode<T> next = null;
        while (head != null) {
            // 找到下一个节点
            next = head.getNext();
            // 将 上一个节点设置为返回的节点
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }
}
