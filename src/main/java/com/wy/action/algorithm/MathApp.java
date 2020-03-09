package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-02-21
 */
public class MathApp {

    /**
     * 不用乘除法，mod 求除法
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        return 1;
    }

    /**
     * 找出最小的正整数
     */
    public int firstMissingPositive(int[] nums) {

        int min = Integer.MAX_VALUE;
        boolean[] status = new boolean[nums.length+2];
        for(int i=0; i<nums.length; i++) {
            if (nums[i]>0) {
                if (min > nums[i]) {
                    min = nums[i];
                }
                if(nums[i]<=nums.length) {
                    status[nums[i]] = true;
                }
            }
        }
        if(min >1) {
            return 1;
        }

        for(int i=1; i<status.length; i++) {
            if (!status[i]) {
                return i;
            }
        }


        return 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1,2,0}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{1}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{3,4,-1,1}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{7,8,9,11,12}));
        Assert.assertEquals(3, firstMissingPositive(new int[]{1,7,8,9,11,12,2}));
    }

    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     * https://leetcode.com/problems/trapping-rain-water/
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <3) return 0;
        int result = 0;
        int left = 0;
        int right = height.length-1;
        while( left<right && height[left]<height[left+1]) {
            left++;
        }
        while (left< right && height[right]<height[right-1]) {
            right--;
        }

        while (left < right) {
            int leftValue = height[left];
            int rightValue = height[right];
            if (leftValue <=rightValue) {
                while (left< right && leftValue>= height[++left]) {
                    result += leftValue - height[left];
                }
            } else {
                while (left< right && rightValue>= height[--right]) {
                    result += rightValue - height[right];
                }
            }
        }
        return result;
    }

    @Test
    public void testTrap() {
        Assert.assertEquals(6, trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(3, trap(new int[]{2,1,0,2}));
        Assert.assertEquals(14, trap(new int[]{5,2,1,2,1,5}));
    }

    /**
     * 字符串数字的乘法
     * @param num1
     * @param num2
     *
     * "123456789"
     * "987654321"
     * @return
     */
    public String multiply(String num1, String num2) {
        // 使用现实中乘法的计算步骤，将每个数存在数组中以免溢出
        int[] pos = new int[num1.length() + num2.length()];

        for (int i=num1.length()-1; i>=0; i--) {
            int p1 = num1.charAt(i) - 48;
            for (int j=num2.length()-1; j>=0; j--) {
                int p2 = num2.charAt(j) - 48;
                int sum =  p1 * p2 + pos[i+j+1];// 当前计算值+ 原位置的值
                pos[i+j+1] = sum%10;//个位
                pos[i+j] += sum /10;// 十位
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<pos.length;i++) {
            if (sb.length() == 0 && pos[i] == 0) {
                continue;
            }
            sb.append(pos[i]);
        }
        return sb.length()==0 ? "0" :sb.toString();

    }

    @Test
    public void testMultiply(){
//        Assert.assertEquals("6", multiply("2", "3"));
        Assert.assertEquals("56088", multiply("123", "456"));
        Assert.assertEquals("121932631112635269", multiply("123456789", "987654321"));
        System.out.println(Long.MAX_VALUE);
    }

    /**
     * 实现x的n次方
     * https://leetcode.com/problems/powx-n/
     * @param x
     * @param n
     * @return
     */
    public double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if(n == Integer.MIN_VALUE) {//处理边界情况
            x = x * x;
            n = n/2;
        }

        if (n<0) {
            x = 1/x;
            n = -n;
        }
        return  n%2==0? power(x*x, n/2) : x*power(x*x, n/2);
    }

    @Test
    public void powerTest(){
        System.out.println(power(2.10000, 3));
    }

    /**
     * 找出一个数组，连续子数组的和最大值
     * https://leetcode.com/problems/maximum-subarray/
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /**
         * 计算i前面的累加值maxCurr和当前nums[i], 哪个大赋予maxCurr
         * 比较maxCurr和max，取较大的值
         */
        int max = nums[0],maxCurr = nums[0];
        for (int i=1; i<nums.length; i++) {
            maxCurr = Math.max(maxCurr+nums[i], nums[i]);
            max = Math.max(maxCurr, max);
        }
        return max;
    }

    @Test
    public void testMaxSubArray() {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

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
}
