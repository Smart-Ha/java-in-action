package com.wy.action.concurrent.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 对象池限流
 * @Author wangyong
 * @Date 2019-06-15
 */

/**
 * 涉及类：Semaphore 信号量，与锁的区别是支持多个线程访问一个临界区
 * 核心方法：
 * acquire
 * release
 * 举例：  实现一个对象池，一次性创建n个对象斥候，所有的线程重复利用这n个对象，当然对象在释放之前不允许其他线程使用
 */

public class ObjectPool<T, R> {
    final List<T> pool;//对象池
    final Semaphore sem;//信号量实现限流器

    public ObjectPool(int size, Class<T> t) {
        this.pool = new Vector<T>();
        for(int i=0 ; i<size; i++) {
            try {
                pool.add(t.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.sem = new Semaphore(size);
    }

    public R execute(Function<T,R> function){
        T t = null;
        try {
            sem.acquire();
            t = pool.remove(0);
            return function.apply(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pool.add(t);
            sem.release();

        }
        return null;
    }

    public static void main(String[] args) {
        ObjectPool<CommonObject, String> objectPool = new ObjectPool<>(2,CommonObject.class);
        String result = objectPool.execute(t -> {
            System.out.println(t);
            return t.getClass().toString();
        });
        System.out.println(result);
    }

}
