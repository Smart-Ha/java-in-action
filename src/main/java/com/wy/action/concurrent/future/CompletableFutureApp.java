package com.wy.action.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangyong
 * @Date 2019-06-29
 */
public class CompletableFutureApp {

    public static void main(String[] args) {
        makeTea();
    }

    /**
     *
     * 场景： 泡茶
     * 分3个步骤：
     *  1.洗水壶，烧开水
     *  2.洗茶杯，拿茶叶
     *  3.泡茶
     */

    public static void makeTea(){
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {

            try {
                System.out.println("T1 洗茶壶 cost 1 min");
                Thread.sleep(1000);
                System.out.println("T1 烧开水 cos 15 min.");
                Thread.sleep(15000);
                System.out.println("T1 烧好了水。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {

            try {
                System.out.println("T2 洗茶杯 cost 1 min");
                Thread.sleep(1000);
                System.out.println("T2 买茶叶 cos 10 min.");
                Thread.sleep(10000);
                System.out.println("T2 买到茶叶。");
                return "龙井";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, (v,tf) -> {
            System.out.println("T1 拿到茶叶: " + tf);
            System.out.println("T1 泡茶");
            return "上茶：" + tf;
        });

        String result = f3.join();
        System.out.println(result);
    }

    /**
     * 描述串行任务关系
     */
    public static  void thenApply(){
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync( () -> {
            return "hello world";
        }).thenApply(s -> s + "qq")
          .thenApply(String::toUpperCase);
        System.out.println(f1.join());
    }
    
    public static void applyOr(){

        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = getRandom(5, 10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = getRandom(5, 10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);

        System.out.println(f3.join());
    }

    private static void sleep(int t, TimeUnit seconds) {
    }

    private static int getRandom(int i, int i1) {
        return 1;
    }

}
