package com.wy.action.concurrent.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author wangyong
 * @Date 2019-06-29
 */
public class CompletionServiceApp {

    /**
     * 场景：同一种商品向不同的电商平台询价，将询价的价格入库
     * 实现： 使用CompletionService。 原理是将多个相同类型的任务异步处理，将处理结果保存在一个阻塞队列中，这样就能实现谁先来就处理谁的。
     */

    public static void main(String[] args) {
       all();
    }

    public static void all() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Double> completionService = new ExecutorCompletionService<>(executorService);
        String itemId = "";
        completionService.submit(() -> {
            return getPriceByS1();
        });

        completionService.submit(() -> {
            return getPriceByS2();
        });

        completionService.submit(() -> {
            return getPriceByS3();
        });
        int size = 3;
        for( int i=0; i< size; i++) {
            try {
                Future<Double> f = completionService.take();
                save(itemId, f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取任意一个
     */
    public static void one() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Double> completionService = new ExecutorCompletionService<>(executorService);
        List<Future> futureList =  new ArrayList<>();
        String itemId = "";
        futureList.add(completionService.submit(() -> {
            return getPriceByS1();
        }));
        futureList.add(completionService.submit(() -> {
            return getPriceByS2();
        }));

        futureList.add(completionService.submit(() -> {
            return getPriceByS3();
        }));
        int size = 3;
        for( int i=0; i< size; i++) {
            try {
                Future<Double> f = completionService.take();
                if (f.get() != null) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                futureList.forEach(f -> f.cancel(true));
            }
        }
    }

    private static void save(String itemId, Double aDouble) {
    }

    private static Double getPriceByS3() {
        return 1.1;
    }

    private static Double getPriceByS2() {
        return 1.1;
    }

    private static Double getPriceByS1() {
        return 1.1;
    }
}
