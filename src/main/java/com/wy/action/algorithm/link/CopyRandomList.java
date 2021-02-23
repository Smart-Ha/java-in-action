package com.wy.action.algorithm.link;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyong
 * @Date 2021-02-23
 */
public class CopyRandomList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 复制带随机指针的链表
     * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 存储节点对应的索引
        Map<Node, Integer> originMap = new HashMap<>();
        // 存储复制链表索引对应的节点
        Map<Integer, Node> copyMap = new HashMap<>();
        Node copyHead = new Node(head.val);
        originMap.put(head, 0);
        copyMap.put(0, copyHead);

        Node current = head.next;
        Node pre = copyHead;
        int index = 1;
        // 先构造next
        while (current != null) {
            Node node = new Node(current.val);
            pre.next = node;
            pre = node;
            copyMap.put(index, node);
            originMap.put(current, index);
            index++;
            current = current.next;
        }

        // 再构造random
        current = head;
        pre = copyHead;
        while (current != null) {
            // 获取原始链表的random指向的索引
            Node random = current.random;
            if (random != null) {
                int randomIdx = originMap.get(random);
                // 根据索引去找复制链表中的random节点
                pre.random = copyMap.get(randomIdx);
            }

            current = current.next;
            pre = pre.next;
        }
        return copyHead;
    }

    Map<Node, Node> visited = new HashMap<>();

    /**
     * 将节点的next和random当做两个普通的指针，将node的链接看成图，我们要处理环的情况
     * * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        if (visited.containsKey(head)) {
            return visited.get(head);
        }

        Node node = new Node(head.val);
        visited.put(head, node);
        node.next = copyRandomList2(head.next);
        node.random = copyRandomList2(head.random);
        return node;
    }

}
