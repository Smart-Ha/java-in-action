package com.wy.action.concurrent.queue;

public interface IQueue<T> {
    /**
     * 入队
     * @param t
     */
    void enq(T t);

    /**
     * 出队
     * @return
     */
    T deq();
}
