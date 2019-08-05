package com.wy.action.concurrent.async2sync;

import com.sun.tools.javac.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * 同步转异步管理器
 * @Author wangyong
 * @Date 2019-06-22
 */
public class GuardedObject<T> {

    private T obj;//
    private ReentrantLock lock = new ReentrantLock();
    private Condition done = lock.newCondition();//表示请求是否完成
    private int timeout = 3000;//3s

    /**
     * 存储异步请求的map，需要考虑超时的问题
     */
    private static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    /**
     * 异步请求之前 拿到管理员
     * @param key
     * @param <K>
     * @return
     */
    public static <K> GuardedObject create(K key){
        if (key == null) {
            throw new NullPointerException("key is not allowed null.");
        }
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }

    /**
     * 返回异步执行返回结果后调用，（比如使用MQ返回请求结果）
     * @param key
     * @param message
     * @param <K>
     */
    public static <K, T> void onFire(K key,T message){
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChange(message);
        }
    }

    /**
     * 返回异步得到的结果
     * @param p
     * @return
     */
    public T get(Predicate<T> p){
        lock.lock();
        try {
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return obj;
    }

    /**
     * 请求返回响应
     * @param message
     */
    public void onChange(T message) {
        lock.lock();
        try {
            this.obj = message;
            done.signalAll();
        }finally {
            lock.unlock();
        }
    }


}
