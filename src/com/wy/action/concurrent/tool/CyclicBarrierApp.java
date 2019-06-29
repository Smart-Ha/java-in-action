package com.wy.action.concurrent.tool;

import com.wy.action.concurrent.ThreadEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @Author wangyong
 * @Date 2019-06-29
 */
public class CyclicBarrierApp {


    /**
     * 思路2使用CyclicBarrier,他也是一个计时器，只不过当计数值减到0时，会重置到初始值，可以继续使用
     *
     */

    BlockingQueue<Object> pQueue = new LinkedBlockingQueue<>(300);//未检订单的队列
    BlockingQueue<Object> dQueue = new LinkedBlockingQueue<>(300);//派送订单的队列
    ExecutorService executor = ThreadEngine.createThreadPool(5,10,100);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () ->{
       executor.execute( () -> {//每次计数到0，执行对比check
           check();
       });
    });

    public void start(String[] args) {
        selectInfo();
    }

    private void check() {
        try {
            Object p = pQueue.take();
            Object d = dQueue.take();
            Object diff = check(p ,d);
            save(diff);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object check(Object p, Object d){
        return new Object();
    }

    private void save(Object diff) {
    }

    /**
     * 查询出所有信息
     */
    private void selectInfo() {
        Thread pThread = new Thread( ()-> {
            long count = getPCount();
            while (count >0) { 
                List<Object> pOrders = getPOrders();
                pQueue.addAll(pOrders);
                try {
                   cyclicBarrier.await();//计数减一
                } catch (InterruptedException e) {
                   e.printStackTrace();
                } catch (BrokenBarrierException e) {
                   e.printStackTrace();
                }
            }
        });
        pThread.start();
        Thread dThread = new Thread( ()-> {
            long count = getDCount();
            while (count >0) {
                List<Object> dOrders = getDOrders();
                dQueue.addAll(dOrders);
                try {
                    cyclicBarrier.await();//计数减一
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        dThread.start();
        
    }

    private List<Object> getDOrders() {
        return new ArrayList<>();
    }

    private long getDCount() {
        return 1L;
    }

    private List<Object> getPOrders() {
        return new ArrayList<>();
    }

    private long getPCount() {
        return 1L;
    }

}
