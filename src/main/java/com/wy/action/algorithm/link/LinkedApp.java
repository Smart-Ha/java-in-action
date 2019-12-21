package com.wy.action.algorithm.link;

import com.wy.action.entity.Node;
import org.junit.Test;

import java.util.IdentityHashMap;

/**
 * 链表
 * @Author wangyong
 * @Date 2019-12-21
 */
public class LinkedApp {

    /**
     * 判断是否为回文
     * // 使用快慢两个指针 定位中点，并逆转前半部分
     */
    public boolean palindrome (Node node) {
        if(node == null) {
            return false;
        }
        Node one = node;
        Node pre = null;
        Node two = node;
        boolean isOdd = false;
        while (two != null) {
            two = two.next;
            if (two != null) {
                two = two.next;
                // 反转前半本部分链表
                Node temp = one.next;
                one.next = pre;
                pre = one;
                one = temp;
            } else {
                isOdd = true;
            }

        }
        if (isOdd) {//单数
            one = one.next;
        }
        while (pre!=null && one !=null) {
            if(!pre.value.equals(one.value)) {
                return false;
            }
            pre = pre.next;
            one = one.next;
        }
        return true;
    }

    @Test
    public void test() {
        String[] values = {"aa","bb","cc","ccc","bb","aa"};
        Node pre = new Node(values[0]);
        Node head = pre;
        for (int i=1; i<values.length; i++) {
            Node n = new Node(values[i]);
            pre.next =n;
            pre = n;
        }
        System.out.println(palindrome(head));
    }
}
