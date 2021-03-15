package com.wy.action.algorithm.math;

import com.wy.action.util.Print;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * 返回一个数组的子序列
     * https://leetcode.com/problems/subsets-ii/
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());// 空序列
        Arrays.sort(nums);
        for(int k=1; k<=nums.length;k++) {
            bfs(nums, result, new ArrayList<>(), k,0);
        }

        return result;
    }

    public void bfs(int[] nums, List<List<Integer>> result,List<Integer> one,  int k, int i) {
        if (one.size() == k) {
            List<Integer> r =  new ArrayList<>(one);
            result.add(r);
            return;
        }
        boolean flag = false;
        for (int j=i ;j<nums.length; j++) {
            if (flag && j-1>=0 && nums[j] == nums[j-1]) {
                continue;
            }
            one.add(nums[j]);
            bfs(nums, result, one, k, j+1);
            one.remove(one.size()-1);
            flag = true;
        }
    }

    @Test
    public void subsetsWithDupTest() {
        System.out.println(subsetsWithDup(new int[]{1,1}));
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
        System.out.println(subsetsWithDup(new int[]{1,2,2,3,4,4,5}));
    }

    /**
     * 盛最多水的容器
     * https://leetcode-cn.com/problems/container-with-most-water/
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for(int i=0;i<height.length-1; i++){
            for(int j=i+1;j<height.length;j++){
                int h = min(height[i], height[j]);
                int area = h * (j-i);
                if(area>max){
                    max = area;
                }
            }
        }
        return max;
    }
    public int min(int a, int b) {
        return a > b ? b : a;
    }

    /**
     * 先水平翻转再求逆
     * https://leetcode-cn.com/problems/flipping-an-image/
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i=0; i<A.length; i++) {
            flip(A[i]);
            for(int j=0; j<A[i].length; j++) {
                A[i][j] ^= 1;
            }
        }
        return A;
    }

    private void flip(int[] ints) {
        int i=0;
        int j = ints.length-1;
        int temp;
        while (i<j) {
            temp = ints[i];
            ints[i] = ints[j];
            ints[j] = temp;
            i++;
            j--;
        }

    }

    /**
     * 加油站 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * 输出: 3
     * https://leetcode-cn.com/problems/gas-station/
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int i=0;
        while (i<len) {
            int gasCount = 0;
            int j = i;
            while (j<len) {
                gasCount +=  gas[j];
                if (gasCount< cost[j]) {
                    break;
                } else {
                    gasCount -= cost[j];
                }
                j= ++j%len;
                if (j == i) {
                    return i;
                }
            }
            if (j>i) {
                i = j;
            } else {
                i++;
            }

        }
        return -1;
    }

    /**
     * 转置矩阵
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] res = new int[n][m];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    /**
     * 求峰值
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int i=0;
        for(; i< nums.length-1; i++) {
            if (nums[i]> pre && nums[i]> nums[i+1]) {
                return i;
            }
            pre = nums[i];
        }
        return nums[i]> pre ? i: 0;
    }

    /**
     * 旋转数组
     * https://leetcode-cn.com/problems/rotate-array/
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        if (k == 0) {
            return;
        }
        int count = 0, index = 0;
        int pre = nums[index];
        int next;
        while (count< n) {
            index = (index+k)%n;
            next = nums[index];
            nums[index] = pre;
            pre = next;
            count++;
        }
    }
}
