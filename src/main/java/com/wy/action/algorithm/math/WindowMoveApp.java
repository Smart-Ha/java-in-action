package com.wy.action.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2021-02-23
 */
public class WindowMoveApp {

    /**
     * 爱生气的书店老板
     * https://leetcode-cn.com/problems/grumpy-bookstore-owner/
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        if (X >= customers.length) {
            for(int i=0; i<customers.length; i++) {
                res += customers[i];
            }
            return res;
        }
        int n = customers.length;
        // 使用滑动窗口
        int xSum = 0;
        // 存储前m个时间的满意度
        int[] prefix = new int[n];
        for(int i=0; i< prefix.length; i++) {
            if (i<X) {
                xSum += customers[i];
            }
            if(grumpy[i]== 0) {
                res += customers[i];
            }
            prefix[i] = res;
        }

        // 存储后n个时间的满意度
        int[] suffix = new int[n];
        res = 0;
        for(int i=n-1; i>= X; i--) {
            if(grumpy[i]== 0) {
                res += customers[i];
            }
            suffix[i] = res;
        }

        res = xSum + suffix[X];
        for(int i=1; i<=customers.length-X; i++) {
            xSum -= customers[i-1];
            xSum += customers[i+X-1];
            int resOne = 0;
            resOne +=  prefix[i-1];
            resOne += xSum;
            if (i<customers.length-X) {
                resOne += suffix[i+X];
            }

            res = Math.max(resOne, res);

        }
        return res;
    }

    @Test
    public void maxSatisfiedTest() {
        int[] customers = {9,10,4,5};
        int[] grumpy = {1,0,1,1};
        int X = 1;
        Assert.assertEquals(19, maxSatisfied(customers, grumpy, X));
    }

    @Test
    public void maxSatisfiedTest2() {
        int[] customers = {4,10,10};
        int[] grumpy = {1,1,0};
        int X = 2;
        Assert.assertEquals(24, maxSatisfied(customers, grumpy, X));
    }

    @Test
    public void maxSatisfiedTest3() {
        int[] customers = {4,10,10};
        int[] grumpy = {1,1,0};
        int X = 1;
        Assert.assertEquals(20, maxSatisfied(customers, grumpy, X));
    }

    @Test
    public void maxSatisfiedTest4() {
        int[] customers = {7,8,8,6};
        int[] grumpy = {0,1,0,1};
        int X = 3;
        Assert.assertEquals(29, maxSatisfied(customers, grumpy, X));
    }


}
