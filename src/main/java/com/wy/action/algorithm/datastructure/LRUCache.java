package com.wy.action.algorithm.datastructure;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyong
 * @Date 2021-02-24
 */
public class LRUCache {

    public class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key,int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;

    private Map<Integer, Node> map;

    private int size ;
    /**
     * 设计一个最近最久未使用的缓存， 使用哈希表和双端队列
     * @param capacity
     */

    public LRUCache(int capacity) {
        size = capacity;
        map = new HashMap<>(size);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        putNode2Head(node);

        return node.val;
    }

    protected void putNode2Head(Node node) {
        // 获取的值等于head的情况
        if (node == head) {
            return;
        }
        node.pre.next = node.next;
        // 如果这个是链表最后一个值
        if (node.next == null) {
            tail = node.pre;
        } else {
            node.next.pre = node.pre;
        }
        node.pre = null;
        node.next = head;
        head.pre = node;
        head = node;
    }


    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            putNode2Head(node);
            return;
        }
        Node node = new Node(key, value);
        if (map.size() == 0) {
            head = node;
            tail = node;
            map.put(key, node);
            return;
        }

        if (map.size() >= size) {
            Node pre = tail.pre;
            if (pre != null) {
                pre.next = null;
            } else{
                head = null;
            }
            map.remove(tail.key);
            tail = pre;

        }
        map.put(key, node);
        node.next = head;
        if (head != null) {
            head.pre = node;
        }

        head = node;
    }

    public static void main(String args[]) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));


    }
}
