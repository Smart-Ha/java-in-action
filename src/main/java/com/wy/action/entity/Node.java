package com.wy.action.entity;

/**
 * 链表节点
 * @Author wangyong
 * @Date 2019-12-21
 */
public class Node<E> {
    public E value;
    public Node next;
    public Node pre;

    public Node(E value) {
        this.value = value;
    }

    public Node(E value, Node next) {
        this.value = value;
        this.next = next;
    }
}
