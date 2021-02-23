package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class TrapRain {

    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     * https://leetcode.com/problems/trapping-rain-water/
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right && height[left] < height[left + 1]) {
            left++;
        }
        while (left < right && height[right] < height[right - 1]) {
            right--;
        }

        while (left < right) {
            int leftValue = height[left];
            int rightValue = height[right];
            if (leftValue <= rightValue) {
                while (left < right && leftValue >= height[++left]) {
                    result += leftValue - height[left];
                }
            } else {
                while (left < right && rightValue >= height[--right]) {
                    result += rightValue - height[right];
                }
            }
        }
        return result;
    }



    @Test
    public void testTrap() {
        Assert.assertEquals(6, trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        Assert.assertEquals(3, trap(new int[]{2, 1, 0, 2}));
        Assert.assertEquals(14, trap(new int[]{5, 2, 1, 2, 1, 5}));
    }


    /**
     * 动态规划求放的雨水数
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height.length < 3) return 0;
        /**
         * 向左向右找到最大的柱子,取其中较小的，减去当前的柱子，当前柱子就能放这么多
         */
        int n = height.length;
        int[] maxLeft = new int[n];
        maxLeft[0] = height[0];
        for (int i=1; i< n; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i-1]);
        }
        int[] maxRight = new int[n];
        maxRight[n-1] = height[n-1];
        for (int i=n-2; i>=0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i+1]);
        }
        int result = 0;
        for(int i=0; i< n; i++) {
            result += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }
        return result;


    }
}
