package com.wy.action.concurrent;

import java.util.concurrent.*;

/**
 * @Author wangyong
 * @Date 2019-06-22
 */
public class ThreadEngine {

    /**
     * 创建线程池
     * @param coreSize
     * @param maxSize
     * @param blockQueueSize
     * @return
     */
    public static ExecutorService createThreadPool(int coreSize, int maxSize, int blockQueueSize){
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(blockQueueSize);
        ExecutorService es = new ThreadPoolExecutor(coreSize, maxSize, 5, TimeUnit.MINUTES,queue);
        return es;

    }
}
