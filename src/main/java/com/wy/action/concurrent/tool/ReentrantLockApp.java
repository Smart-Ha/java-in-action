package com.wy.action.concurrent.tool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangyong
 * @Date 2020-05-26
 */
public class ReentrantLockApp {


    private final Lock rtl = new ReentrantLock();
    int value;

    public int get() {
        // 获取锁
        rtl.lock();
        try {
            return value;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }

    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value = 1 + get();// 可重入
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
}
