package com.wy.action.concurrent.tool;


import com.wy.action.concurrent.ThreadEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author wangyong
 * @Date 2019-06-25
 */
public class CountDownLatchApp {

    /**
     * CountDownLatch 的作用是： 在并发环境中，假如有n个并行任务执行，等这n个并行任务都结束之后，才能继续另一个任务，那么可以使用CountDownLatch计数。
     * 场景：
     * 一个订单系统，每天要核对订单信息和派单信息，比较差异，然后写入系统，步骤：
     * 1.查询未对账订单
     * 2.查询派送单
     * 3.执行对账操作
     * 4.写入差异库
     * 思路1：
     * 1、2可以并行执行，1、2都完了之后，执行3，然后执行4
     * 思路2：
     * 1、2为一个整体并行执行，将结果输出到阻塞队列1，3从阻塞队列中消费执行，将结果输出到阻塞队列2，4进行消费。
     * 那么12、3、4就可以并行执行了。
     */

    public void fun1(){
        ExecutorService executor = ThreadEngine.createThreadPool(2,5,100);

        while (true) {
            final  AsyncObject ao1 = new AsyncObject() ;

            CountDownLatch countDownLatch = new CountDownLatch(2);
            executor.execute( ()->{
                ao1.returnData =  getPOrders();
                countDownLatch.countDown();
            });
            final  AsyncObject ao2 = new AsyncObject() ;

            executor.execute( () -> {
                ao2.returnData = getDOrders();
                countDownLatch.countDown();
            });
            try {
                countDownLatch.await();
                List<Object> pOrders = (List<Object>) ao1.returnData;
                List<Object> dOrders = (List<Object>) ao2.returnData;

                List<Object> diff = check(pOrders, dOrders);
                save(diff);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 差异入库
     * @param diff
     */
    private void save(List<Object> diff) {
    }

    /**
     * 对比购买订单和派送订单的差异
     * @param pOrders
     * @param dOrders
     * @return
     */
    private List<Object> check(List<Object> pOrders, List<Object> dOrders) {
        return new ArrayList<>();
    }

    /**
     * 查询派单信息
     * @return
     */
    private Object getDOrders() {
        return new ArrayList<>();
    }

    /**
     * 查询未对账的记录
     * @return
     */
    private List<Object> getPOrders() {
        return new ArrayList<Object>();
    }


}
