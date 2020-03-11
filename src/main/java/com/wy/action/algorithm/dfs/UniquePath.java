package com.wy.action.algorithm.dfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-11
 */
public class UniquePath {

    /**
     * 从左上角走到右下角有多少种走法（只能向右或向下）
     * https://leetcode.com/problems/unique-paths/
     * @param m 列
     * @param n 行
     * @return
     */
    int count = 0;
    public int uniquePaths(int m, int n) {
        count = 0;
        dfs(m,n,0,0);
        return count;
    }

    /**
     *
     * @param m
     * @param n
     * @param i
     * @param j
     */
    private void dfs(int m, int n, int i, int j) {
        if(i==n-1 && j==m-1) {
            count++;
            return;
        }
        if (i<n) {
            dfs(m,n,i+1,j);
        }
        if (j<m) {
            dfs(m,n,i,j+1);
        }
    }

    /**
     * 动态规划算
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
       int[][] status = new int[n][m];
       for(int i=0;i<n;i++) {//左侧都是1
           status[i][0] = 1;
       }
        for(int i=0;i<m;i++) {//上侧都是1
            status[0][i] = 1;
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                status[i][j] = status[i-1][j] + status[i][j-1];
            }
        }
        return status[n-1][m-1];
    }


    @Test
    public void test() {
        Assert.assertEquals(3,uniquePaths2(3,2));
        Assert.assertEquals(126,uniquePaths2(5,6));
        for(int i=1;i<10;i++) {
            System.out.println(uniquePaths(5,i));
        }

    }


}
