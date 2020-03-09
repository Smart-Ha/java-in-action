package com.wy.action.algorithm.dp;

import org.junit.Test;

/**
 * 动态规划球n x m 格子的最短路径
 * @Author wangyong
 * @Date 2019-12-13
 */
public class MinDictPath {

    private int minValue = Integer.MIN_VALUE;


    /**
     * m * n的格子 每个格子有值 从左上角走，只能向右或向下走，走到右下角，求值加起来最小的路径
     * 每个格子当前的值 由上一个格子+当前格，或左边的格子+当前格 得到， 我们选最小的那个
     * 状态转移表法
     * @param arr
     * @return
     */
    public int getMinPath(int[][] arr, int m , int n) {
        int[][] state = new int[m][n];
        // 初始化第一列的值
        int sum = 0;
        for(int i=0;i<m; i++) {
            sum += arr[i][0];
            state[i][0] = sum;
        }
        sum = 0;
        for(int i=0;i<n; i++) {
            sum +=arr[0][i];
            state[0][i] = sum;
        }

        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                state[i][j] = Math.min(state[i-1][j], state[i][j-1]) + arr[i][j];
            }
//            Print.print(state);
        }
        return state[m-1][n-1];
    }

    /**
     * 状态转移方程法 从结果往前推
     * @return
     */
    public int getMinPath(int i, int j, int[][] arr, int[][] state) {
        if (i == 0 && j == 0) {
            return arr[0][0];
        }
        // 找左边的
        int left = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            left = getMinPath(i, j - 1, arr, state);
        }

        int up = Integer.MAX_VALUE;
        if (i-1 >= 0) {
            up = getMinPath(i-1,j,arr,state);
        }


        state[i][j] = Math.min(left, up) + arr[i][j];
        return state[i][j];
    }


    @Test
    public void test() {
        int[][] arr = {
                {2,3,4,5,5},
                {1,6,2,1,5},
                {2,3,4,3,5},
                {2,3,6,5,5}
        };
        System.out.println(getMinPath(arr,4,5));
        System.out.println(getMinPath(4-1,5-1,arr,new int[4][5]));


    }
}
