package com.wy.action.concurrent.forkjoin;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * 分治任务模型，两个阶段：任务分解，迭代的分解子任务； 结果合并，逐层合并，直至获得最后的结果
 * @Author wang
 * @Date 2019-06-01
 */
public class ForkAndJoinApp {
    /**
     * 涉及的类：
     * ForkJoinPool 分治任务的线程池，本质上是一个生产者-消费者实现，内部有任务队列，每一个工作线程对应一个任务队列
     * ForkJoinTask 分治任务 类似于TheadPoolExecutor和Runnable的关系
     * 核心方法：
     * fork： 异步的创建一个子任务
     * join： 阻塞当前线程来等待子任务的执行结果
     *例子：使用分治任务来计算斐波那契数列
     */
    public static void main(String[] args) {
        wordCount();
    }

    public static void fibo(){
        ForkJoinPool fjp = new ForkJoinPool(4);

        // create a task
        Fibonacci fib = new Fibonacci(5);
        // start task
        Integer result = fjp.invoke(fib);
        System.out.println(result);
    }

    public static void wordCount(){
        String[] fr = {"hello i am s student",
        "i love you.",
        "i love China",
        "in the end ,life become an act of letting go,"};
        ForkJoinPool fjp = new ForkJoinPool(3);
        WordCount wc = new WordCount(fr,0, fr.length);
        Map<String, Long> result = fjp.invoke(wc);
        result.forEach((k,v) -> {
            System.out.println(k+":"+v);
        });

    }
}
