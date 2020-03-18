package com.wy.action.algorithm;

import com.wy.action.util.Print;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-03-11
 */
public class MatrixApp {


    /**
     * 给一个二维数组，螺旋输出
     * https://leetcode.com/problems/spiral-matrix/
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rowS=0, rowE = matrix.length ;
        int colS=0, colE =  matrix[0].length;
        int t = rowE*colE;
        int i = 0,j=0;
        while (list.size() < t) {
            // left- right
            while ( j<colE) {
                list.add(matrix[i][j]);
                j++;
            }
            if (list.size() == t) {
                return list;
            }
            j--;
            i++;
            rowS++;//行+1
            // right - down
            while ( i<rowE) {
                list.add(matrix[i][j]);
                i++;
            }
            if (list.size() == t) {
                return list;
            }
            i--;
            j--;
            colE--;//列-1
            // right - left
            while (j>=colS) {
                list.add(matrix[i][j]);
                j--;
            }
            if (list.size() == t) {
                return list;
            }
            j++;
            i--;
            rowE--;//
            // left - up
            while (i>=rowS ) {
                list.add(matrix[i][j]);
                i--;
            }
            if (list.size() == t) {
                return list;
            }
            i++;
            j++;
            colS++;
        }
        return list;
    }

    @Test
    public void  spiralOrderTest() {
        int[][] arr = {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
//                , {13,14,15,16}
        };
        System.out.println(spiralOrder(arr));

    }


    /**
     * 顺时针螺旋生成数组
     * https://leetcode.com/problems/spiral-matrix-ii/
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int rowS=0, rowE = n ;
        int colS=0, colE =  n;
        int t = rowE*colE;
        int i = 0,j=0;
        int index = 1;
        while (index <= t) {
            // left- right
            while ( j<colE) {
               matrix[i][j]=index++;
                j++;
            }
            if (index>t) break;
            j--;
            i++;
            rowS++;//行+1
            // right - down
            while ( i<rowE) {
                matrix[i][j]=index++;
                i++;
            }
            if (index>t) break;
            i--;
            j--;
            colE--;//列-1
            // right - left
            while (j>=colS) {
                matrix[i][j]=index++;
                j--;
            }
            if (index>t) break;
            j++;
            i--;
            rowE--;//
            // left - up
            while (i>=rowS && index<=t) {
                matrix[i][j]=index++;
                i--;
            }
            i++;
            j++;
            colS++;
        }
        return matrix;
    }

    @Test
    public void generateMatrixTest() {
        Print.print(generateMatrix(4));
    }


    /**
     * https://leetcode.com/problems/set-matrix-zeroes/
     * 将有0 的行和列设置成0
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean status[][] = new boolean[matrix.length][matrix[0].length];

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length;j++){
                if(matrix[i][j] == 0 && !status[i][j]) {
                    for(int k=0;k<matrix.length;k++) {//列
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = 0;
                            status[k][j] = true;
                        }

                    }
                    for(int k=0;k<matrix[0].length;k++) {//行
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = 0;
                            status[i][k] = true;
                        }
                    }
                }
            }
        }
    }

    @Test
    public void setZeroesTest() {
        int[][] matrix = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        setZeroes(matrix);
        Print.print(matrix);
        int[][] matrix1 = {
                {0,1,1,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        setZeroes(matrix1);
        Print.print(matrix1);
    }

    /**
     * 给一个排序的数组，移除出现次数超过2的数字，返回最后的长度
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int k: nums) {
            if (i<2 || k>nums[i-2]) {
                nums[i++] = k;
            }
        }
        return i;
    }

    @Test
    public void removeDuplicatesTest() {
        int[] arr = {0,0,1,1,1,1,1,1,2,3,3};
        int[] one = {0,0,0,0,0};
        Assert.assertEquals(7, removeDuplicates(arr));
        Assert.assertEquals(2, removeDuplicates(one));
    }

    /**
     * 找出最大的矩形面积
     * https://leetcode.com/problems/largest-rectangle-in-histogram/
     * 已i为中心（最矮的），找出左右连续比他高的 max = h[i] * (right-left-1)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;
        int max = 0;
        int[] lessLestIdx = new int[heights.length];
        int[] lessRightIdx = new int[heights.length];
        lessLestIdx[0] = -1;
        lessRightIdx[heights.length-1] = heights.length;
        int p;
        for (int i=1; i<heights.length; i++) {
            p = i-1;
            while (p>=0 && heights[i]<= heights[p]) {
                p = lessLestIdx[p];
            }
            lessLestIdx[i] = p;
        }
        for (int i=heights.length-2;i>=0; i--) {
            p = i+1;
            while (p < heights.length && heights[i]<= heights[p]) {
                p = lessRightIdx[p];
            }
            lessRightIdx[i] = p;
        }

        for (int i=0; i<heights.length; i++) {
            max = Math.max(max, heights[i] *( lessRightIdx[i]-lessLestIdx[i]-1));
        }
        return max;

    }

    @Test
    public void largestRectangleAreaTest() {
        Assert.assertEquals(10, largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}
