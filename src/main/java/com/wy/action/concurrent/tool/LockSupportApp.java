package com.wy.action.concurrent.tool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author wangyong
 * @Date 2020-12-04
 */
public class LockSupportApp {


    public static void main(String[] args) {
        alternatePrint();
    }

    static Thread t1 = null;
    static  Thread t2 = null;
    private static void alternatePrint() {
        t1 = new Thread() {
            @Override
            public void run() {
                List<Integer> list = Arrays.asList(1,2,3,4,5);
                for (Integer i: list) {
                    System.out.print(i);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        };


        t2 = new Thread() {
            @Override
            public void run() {
                List<String> list = Arrays.asList("a,b,c,d,e".split(","));
                for (String i: list) {
                    LockSupport.park();
                    System.out.print(i);
                    LockSupport.unpark(t1);

                }
            }
        };

        t1.start();
        t2.start();

    }

}
