package com.wy.action.algorithm.link;

import com.wy.action.entity.ListNode;

/**
 * @Author wangyong
 * @Date 2021-02-24
 */
public class SortListApp {


    /**
     * https://leetcode-cn.com/problems/sort-list/
     * 链表排序
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 使用归并排序
        return sortSub(head, null);

    }

    private ListNode sortSub(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }

        if (head.next == tail) {
            // 分段的时候前后都用了mid，所以这里把前面的mid去掉
            head.next = null;
            return head;
        }

        ListNode fast = head, slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast !=tail) {
                fast = fast.next;
            }
        }
        
        ListNode head1 = sortSub(head, slow);
        ListNode head2 = sortSub(slow, tail);
        return mergeSub(head1, head2);

    }

    private ListNode mergeSub(ListNode head1, ListNode head2) {
        ListNode flag = new ListNode(0);
        ListNode temp = flag, temp1 = head1, temp2 = head2;

        while (temp1!= null && temp2!= null) {

            if (temp1.val >= temp2.val) {
                temp.next = temp2;
                temp2 = temp2.next;
            } else {
                temp.next = temp1;
                temp1 = temp1.next;
            }
            temp = temp.next;
        }

        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return flag.next;

    }
}
