package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @Author wangyong
 * @Date 2020-03-12
 */
public class MinPathSum {

    /**
     * 从左上角走到右下角,求路径和最小的值
     * https://leetcode.com/problems/minimum-path-sum/
     * @return
     */
    public int minPathSum(int[][] grid) {
       // 使用贪心算法思路
        int[][] status = new int[grid.length][grid[0].length];
        status[0][0] = grid[0][0];
        for(int i=1;i<grid.length;i++) {//左侧都是1
            status[i][0] = status[i-1][0] + grid[i][0];
        }
        for(int i=1; i<grid[0].length; i++) {//上侧都是1
            status[0][i] = status[0][i-1] + grid[0][i];
        }
        for(int i=1;i<grid.length;i++) {
            for(int j=1;j<grid[0].length;j++) {
                status[i][j] =  grid[i][j] + Math.min(status[i-1][j],status[i][j-1]);
            }
        }
        return status[grid.length-1][grid[0].length-1];
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
