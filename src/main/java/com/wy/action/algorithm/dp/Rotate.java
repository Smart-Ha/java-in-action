package com.wy.action.algorithm.dp;

import com.wy.action.util.Print;
import org.junit.Test;

/**
 *
 * @Author wangyong
 * @Date 2020-03-06
 */
public class Rotate {

    /**
     * 矩阵顺时针旋转90度
     * https://leetcode.com/problems/rotate-image/
     * @param arr
     */
    public void rotate(int[][] arr) {
        int n = arr.length;
        int temp;
        for (int i=0; i< n/2;i++) {
            for(int j=i;j< n-i-1;j++) {
                temp = arr[i][j];
                arr[i][j] = arr[n-j-1][i];
                arr[n-j-1][i] = arr[n-i-1][n-j-1];
                arr[n-i-1][n-j-1] = arr[j][n-i-1];
                arr[j][n-i-1] = temp;
            }
        }
    }

    @Test
    public void test() {
        int[][] arr = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(arr);
        Print.print(arr);
    }

}
