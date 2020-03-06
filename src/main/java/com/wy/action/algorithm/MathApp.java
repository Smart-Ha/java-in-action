package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

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


}
