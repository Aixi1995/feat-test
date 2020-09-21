package com.example;

import com.example.algorithm.ListNodeUtils;
import com.example.entity.ListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/9/21 15:07
 * @desc
 */
@Slf4j
public class ListNodeTest {

    @Test
    public void testReserve() {
        ListNode<Integer> node4 = new ListNode<>(null, 4);
        ListNode<Integer> node3 = new ListNode<>(node4, 3);
        ListNode<Integer> node2 = new ListNode<>(node3, 2);
        ListNode<Integer> head = new ListNode<>(node2, 1);
        log.info(head.toString());
        ListNodeUtils<Integer> listNodeUtils = new ListNodeUtils<>();
        ListNode<Integer> ret = listNodeUtils.reverse(head);
        log.info(ret.toString());
    }

    @Test
    public void testReserveByLoop() {
        ListNode<Integer> node4 = new ListNode<>(null, 4);
        ListNode<Integer> node3 = new ListNode<>(node4, 3);
        ListNode<Integer> node2 = new ListNode<>(node3, 2);
        ListNode<Integer> head = new ListNode<>(node2, 1);
        log.info(head.toString());
        ListNodeUtils<Integer> listNodeUtils = new ListNodeUtils<>();
        ListNode<Integer> ret = listNodeUtils.reverseByLoop(head);
        log.info(ret.toString());
    }
}
