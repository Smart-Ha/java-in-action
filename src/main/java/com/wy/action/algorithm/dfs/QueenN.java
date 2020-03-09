package com.wy.action.algorithm.dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-03-07
 */
public class QueenN {

    /**
     * https://leetcode.com/problems/n-queens/
     * 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法
     */
    public List<List<String>> solveNQueens(int n) {
        boolean[][] arr = new boolean[n][n];
        List<List<String>> result  = new ArrayList<>();
        rQueen(arr,0, result);
        return result;
    }

    private void rQueen(boolean[][] arr, int row, List<List<String>> result) {
        if ( row >= arr.length) {
            List<String> one = new ArrayList<>();
            for(int i=0; i<arr.length;i++) {
                StringBuilder sb = new StringBuilder();
                for (int j=0;j<arr[0].length; j++) {
                    if (arr[i][j]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                one.add(sb.toString());
            }
            result.add(one);
            return ;
        }
        for(int j=0;j<arr.length;j++) {// 列
            if (check(arr,row, j)) {
                arr[row][j] = true;
                rQueen(arr,row+1,result);
                arr[row][j] = false;
            }
        }
    }


    private boolean check(boolean[][] arr, int row, int j){
        for (int i=0;i<row;i++) {//该列已被占用
            if (arr[i][j]) {
                return false;
            }
        }
        // 45度角
        for(int m=row-1, n=j+1;m>=0 && n<arr.length; m--,n++) {
            if (arr[m][n]) {
                return false;
            }
        }
        // 135度
        for(int m=row-1, n=j-1; n>=0 && m>=0; m--,n--) {
            if (arr[m][n]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        System.out.println(solveNQueens(4));
    }


}
