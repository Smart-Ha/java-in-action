package com.wy.action.guava.cache;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;
import com.wy.action.entity.People;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author wangyong
 * @Date 2019-08-03
 */
public class CacheLoaderApp {

    public static void main(String[] args) {
        System.out.println(simpleGet("zhangsan"));
    }

    /**
     * 1. 创建cache的时候只能添加1个监听器，这个监听器对象会被多个线程共享,所以要做好加锁
     */


    private static CacheLoader<String, People>  cacheLoader  = new CacheLoader<String, People>() {
        @Override
        public People load(String key) throws Exception {
            return createProple(key);
        }
    };
    private static  LoadingCache<String, People> defaultCache  = CacheBuilder.newBuilder()
            .maximumSize(10)
            .build(cacheLoader);

    /**
     * 带权重的缓存
     * @return
     */
    private static LoadingCache<String, People> getCacheByWeight () {
        return CacheBuilder.newBuilder()
                .maximumSize(10)
                .weigher(new Weigher<String, People>() {
                    // 权重，按权重回收
                    @Override
                    public int weigh(String key, People value) {
                        return value.getAge();
                    };
                })
                .maximumWeight(100)
                .build(cacheLoader);
    }

    private static LoadingCache<String,People> getCacheByListener() {
        RemovalListener<String, People> removalListener  = new RemovalListener<String, People>() {
            @Override
            public void onRemoval(RemovalNotification<String, People> notification) {
                People people = notification.getValue();
                System.out.println("remove " + people.getName());
            }
        };

        return CacheBuilder.newBuilder()
                .removalListener(removalListener)
                .build(cacheLoader);
    }

    /**
     * 创建一个异步移除监听器的缓存
     * @return
     */
    private static LoadingCache<String,People> getCacheByAsyncListener() {
        RemovalListener<String, People> removalListener  = new RemovalListener<String, People>() {
            @Override
            public void onRemoval(RemovalNotification<String, People> notification) {
                People people = notification.getValue();
                System.out.println("remove " + people.getName());
            }
        };

        RemovalListener<String, People> asyncRemoveListener = RemovalListeners.asynchronous(removalListener, Executors.newSingleThreadExecutor());
        return CacheBuilder.newBuilder()
                .removalListener(removalListener)
                .build(cacheLoader);
    }



    @Test
    public void testRemoveListener() throws ExecutionException {
        String key = "James";
        LoadingCache<String,People> peopleCache = getCacheByListener();
        People people =  peopleCache.get(key);
        if (people!= null) {
            peopleCache.invalidate(key);
        }
    }

    public static People simpleGet(String key) {
        try {
            return defaultCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量获取
     * @param keys
     * @return
     */
    public static ImmutableMap<String, People> simpleGetAll(Iterable<String> keys) {
        try {
            return defaultCache.getAll(keys);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static People getOrDo(String key) {
        try {
            return defaultCache.get(key, new Callable<People>() {
                @Override
                public People call() throws Exception {
                    return doThingsTheHardWay(key);
                    
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static People doThingsTheHardWay(String key) {
        return new People(key);
    }


    private static People createProple(String key) {
        return new People(key);
    }
}
