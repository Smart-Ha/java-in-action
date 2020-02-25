package com.wy.action.algorithm.backtrack;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author wangyong
 * @Date 2020-02-24
 */
public class SudokuApp {
    /**
     * 给定9*9 数组，填充完成数独
     * 其中 空的格子中是"."
     */
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][] box = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        for (int i=0; i<board.length; i++) {//行
            for (int j=0; j<board[0].length; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int p = c-49;
                    row[i][p] = true;// 第i行 哪些数字填了
                    col[j][p] = true;// 第j列 哪些数字填了
                    int b = i/3 * 3 + j/3;
                    box[b][p] = true;// 第n个方格 哪些数字填了

                }
            }
        }

        fillSodoKu(board,0,0);

    }

    /**
     *
     * @param board
     * @param i 行
     * @param j 列
     * @return
     */
    private boolean fillSodoKu(char[][] board, int i, int j) {
        if (i == board.length) return true;
        if (j == board.length) return fillSodoKu(board, i+1, 0);//下一行

        if (board[i][j] != '.') {// 把不空的放过
            return fillSodoKu(board, i, j+1);
        }

        int b = i/3 * 3 + j/3;
        for (int k=0; k<9; k++) {// 从1-9个数字中作为候选项，填这个位置
           if (!row[i][k] && !col[j][k] && !box[b][k]) {//候选项在行，列，九宫格都没有出现
               row[i][k] = true;
               col[j][k] = true;
               box[b][k] = true;
               board[i][j] = (char) (k+49);
               if (fillSodoKu(board, i, j+1)) {
                   return true;
               }
               row[i][k] = false;
               col[j][k] = false;
               box[b][k] = false;
               board[i][j] = '.';
           }
        }
        return false;
    }


    @Test
    public void test() {
        char[][] chars = {{'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(chars);
        System.out.println(chars);
    }
}
