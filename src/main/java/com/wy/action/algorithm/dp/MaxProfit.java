package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class MaxProfit {

    /**
     *  买卖股票的最佳时机 III
     *  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
     *  给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //存放的是i之前最小的数
        int[] minDp = new int[n];
        int min = Integer.MAX_VALUE;
        // 求第一次的最小值
        for (int i=0; i<n; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            minDp[i] = min;
        }
        //存放的是i之后最大的数
        int[] maxDp = new int[n];
        int max = Integer.MIN_VALUE;
        // 求第二次的最大值,
        for (int i=n-1; i>0; i--) {
            if (prices[i] > max) {
                max = prices[i];
            }
            maxDp[i] = max;
        }
        int res = 0;
        for(int i=1; i<n; i++) {
            int one = prices[i]-minDp[i];
            res = Math.max(res, one);
            for (int j=i+1;j<n-1; j++) {
                res = Math.max(res, one+ maxDp[j]-prices[j]);
            }
        }
        return res;

    }

    @Test
    public void test() {
        int[] arr = {3,3,5,0,0,3,1,4};
        Assert.assertEquals(6,maxProfit(arr));
        int[] arr2 = {1,2,3,4,5};
        Assert.assertEquals(4,maxProfit(arr2));
    }

}
