package com.wy.action.algorithm.link;

import com.wy.action.entity.ListNode;

/**
 * @Author wangyong
 * @Date 2021-03-18
 */
public class ReverseKGroup {

    /**
     * k个一组反转
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        /**
         * key: 通过head 和 tail k个一组反转，重要的是保存反转之后的最后一个节点
         */
        // 哨兵节点
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (head != null) {
            ListNode tail = pre;
            for(int i=0; i<k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return node.next;
                }
            }

            ListNode next = tail.next;
            ListNode[] reverse = reverseK(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 接回子链表
            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;

        }
        return node.next;

    }

    private ListNode[] reverseK(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode p = head;
        while (pre != tail) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }

        return new ListNode[]{tail, head};

    }
}
