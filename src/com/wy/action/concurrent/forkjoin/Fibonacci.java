package com.wy.action.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author wangyong
 * @Date 2019-06-15
 */
public class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n<=1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n-1);
        f1.fork();// create a new child task
        Fibonacci f2 = new Fibonacci(n-2);
        //wait for result of child task, and compute result
        return f2.compute() + f1.join();
    }
}
