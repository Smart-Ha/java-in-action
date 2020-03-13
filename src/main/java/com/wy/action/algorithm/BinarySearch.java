package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-13
 */
public class BinarySearch {

    /**
     * 找出数组中是否有目标值 数组值从左到右有序，下一行比上一行的值大
     * https://leetcode.com/problems/search-a-2d-matrix/
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length==0) {
            return false;
        }
        int start = 0, end = matrix.length-1;
        if (matrix[0][0]>target || matrix[end][matrix[0].length-1]<target) {
            return false;
        }
        int row = 0;
        int mid;
        while (true) {
            mid = (start + end) /2;
            if (matrix[mid][0]> target) {
                end = mid-1;
            } else if( matrix[mid][0] < target) {// 第一个值比目标值小
                if (mid +1 < matrix.length) {
                    if (matrix[mid+1][0]> target) {//下一行比目标值大
                        row = mid;
                        break;
                    }
                    start = mid+1;
                } else {//最后一行
                    row = mid;
                    break;
                }
            } else {
                return true;
            }
        }
        start = 1;
        end = matrix[0].length-1;
        while (true) {

            mid = (start+ end)/2;
            if (matrix[row][mid] == target) {
                return true;
            } if (matrix[row][mid]> target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
            if (start > end) {
                return false;
            }
        }
    }

    @Test
    public void searchMatrixTest() {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30, 34, 50}
        };
        Assert.assertEquals(true, searchMatrix(matrix, 3));
        Assert.assertEquals(true, searchMatrix(matrix, 16));
        Assert.assertEquals(false, searchMatrix(matrix, 15));
        Assert.assertEquals(false, searchMatrix(matrix, 45));
    }

}
