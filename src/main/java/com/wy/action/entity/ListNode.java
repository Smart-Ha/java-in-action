package com.wy.action.entity;

/**
 * @Author wangyong
 * @Date 2020-03-11
 */

import java.util.List;

/**
 * Definition for singly-linked list.
 *
 */
public class ListNode {
    public int val;
    public ListNode next;
    ListNode(int x) { val = x; }

    /**
     * 构造节点
     * @param list
     * @return
     */
    public static ListNode construct(List<Integer> list) {
        ListNode head = null;
        ListNode pre = null;
        for (int i=0; i<list.size(); i++) {
            ListNode node = new ListNode(list.get(i));
            if (head == null) {
                head = node;
            }
            if(pre != null) {
                pre.next = node;
            }
            pre = node;
        }
        return head;
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            System.out.print("-->");
            head = head.next;
        }
        System.out.println();
    }
}

