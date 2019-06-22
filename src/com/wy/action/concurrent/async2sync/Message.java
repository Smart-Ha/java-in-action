package com.wy.action.concurrent.async2sync;

/**
 * 消息
 * @Author wangyong
 * @Date 2019-06-22
 */
public class Message<T> {
    private T key;
    private Object payload;

    public Message(T key, Object payload) {
        this.key = key;
        this.payload = payload;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
