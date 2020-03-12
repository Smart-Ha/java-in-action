package com.wy.action.algorithm.dfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-12
 */
public class MinPathSum {

    /**
     * 从左上角走到右下角有多少种走法（只能向右或向下）
     * https://leetcode.com/problems/unique-paths/
     * @param m 列
     * @param n 行
     * @return
     */
    int min = 0;
    public int minPathSum(int[][] grid) {
        min = Integer.MAX_VALUE;
        dfs(grid,0,0,0);
        return min;
    }

    /**
     * @param i
     * @param j
     */
    private void dfs(int[][] grid, int i, int j,int sum) {
        if(i< grid.length && j< grid[0].length) {
            sum += grid[i][j];
        }
        if(i==grid.length-1 && j==grid[0].length-1) {
            if (min>sum) {
                min = sum;
            }
            return;
        }
        if (i<grid.length) {
            dfs(grid,i+1,j,sum);
        }
        if (j<grid[0].length) {
            dfs(grid,i,j+1,sum);
        }
    }

    @Test
    public void test() {

        Assert.assertEquals(7, minPathSum(new int[][] {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        }));
    }
}
