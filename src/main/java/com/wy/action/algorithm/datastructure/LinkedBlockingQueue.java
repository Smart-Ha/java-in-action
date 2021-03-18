package com.wy.action.algorithm.datastructure;


import com.wy.action.concurrent.queue.IQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingQueue<T> implements IQueue<T> {

    protected final Lock lock = new ReentrantLock();
    /**
     * 没有满的条件
     */
    protected final Condition notFull = lock.newCondition();
    /**
     * 队列不空的条件
     */
    protected final Condition notEmpty = lock.newCondition();


    protected final int capacity;

    protected final AtomicInteger count = new AtomicInteger(0);

    protected Node head;

    protected Node tail;

    public LinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        head = tail = new Node(null);
    }

    static class Node<T> {
        T val;
        Node<T> pre;
        Node<T> next;
        public Node(T val) {
            this.val = val;
        }
    }

    /**
     * 队
     * @param t
     */
    @Override
    public void enq(T t) {
            try {
                lock.lock();
                while (count.get() >= capacity) {
                    // 队列满了
                    notFull.await();
                }
                //
                enqueue(t);

                // 通知不为空了
                notEmpty.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

    }

    private void enqueue(T t) {
        Node<T> node = new Node<>(t);
        node.pre = tail;
        tail.next = node;
        tail = node;
        count.incrementAndGet();
    }

    @Override
    public T deq() {
        try {
            lock.lock();
            while (count.get() == 0) {
                // 队列为空
                notEmpty.await();
            }
            // 出对
            T val =  dequeue();
            // 通知没有满
            notFull.signalAll();
            return val;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    private T dequeue() {
        Node<T> node = tail;
        tail = tail.pre;
        tail.next = null;
        node.pre = null;
        count.decrementAndGet();
        return node.val;
    }


}
