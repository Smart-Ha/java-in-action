package com.wy.action.concurrent.tool;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangyong
 * @Date 2021-02-19
 */
public class AlternatePrint {
    /**
     * 多个线程轮流打印1-100
     */
    public static class Print1 implements Runnable {

        private static final Object lock = new Object();
        private static int count = 1;
        private int threadNo;
        private int theadCount;

        public Print1(int threadNo, int theadCount) {
            this.threadNo = threadNo;
            this.theadCount = theadCount;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // 满足条件，则跳出循环打印
                    while (count % theadCount != threadNo) {
                        if (count > 100) {
                            break;
                        }
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (count > 100) {
                        break;
                    }

                    System.out.println("thead-" + threadNo + ":" + count);
                    count++;
                    lock.notifyAll();
                }
            }

        }
    }

    @Test
    public void print1Test() {
        int threadCount = 3;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Print1(i, threadCount)).start();
        }
    }


    public static class Print2 implements Runnable {

        private static final ReentrantLock lock = new ReentrantLock();
        private static final Condition con = lock.newCondition();
        private static int count = 1;
        private int threadNo;
        private int theadCount;

        public Print2(int threadNo, int theadCount) {
            this.threadNo = threadNo;
            this.theadCount = theadCount;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    // 满足条件，则跳出循环打印
                    while (count % theadCount != threadNo) {
                        if (count > 100) {
                            break;
                        }
                        try {
                            con.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (count > 100) {
                        break;
                    }

                    System.out.println("thead-" + threadNo + ":" + count);
                    count++;
                    con.signalAll();
                } finally {
                    lock.unlock();

                }

            }

        }
    }

    @Test
    public void print2Test() {
        int threadCount = 3;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Print2(i, threadCount)).start();
        }


    }


}
