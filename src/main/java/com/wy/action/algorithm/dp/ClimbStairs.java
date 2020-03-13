package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-12
 */
public class ClimbStairs {

    /**
     * 爬阶梯，一次盘1格或者2格，有多少种路径到达最后
     * https://leetcode.com/problems/climbing-stairs/
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] arr = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for( int i=2; i<=n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

    @Test
    public void climbStairsTest() {
        Assert.assertEquals(3,climbStairs(3));
    }
}
