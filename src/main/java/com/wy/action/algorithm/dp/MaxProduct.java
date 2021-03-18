package com.wy.action.algorithm.dp;

/**
 * @Author wangyong
 * @Date 2021-02-25
 */
public class MaxProduct {


    /**
     * 连续元素的最大乘积
     * @param nums [2,3,-2,4]
     * @return
     */
    public int maxProduct(int[] nums) {
        int maxPositive = nums[0];
        int minNegative = nums[0];
        int res = nums[0];
        for (int i=1; i< nums.length; i++) {
            int maxF = maxPositive, minF = minNegative;
            // 前一个的最大负数、正数和本身乘积， 本身 三个数的最大值
            maxPositive = Math.max(minF * nums[i] , Math.max(nums[i], maxF * nums[i]));
            minNegative = Math.min(minF * nums[i] , Math.min(nums[i], maxF * nums[i]));
            res = Math.max(res, maxPositive);
        }

        return  res;
    }
}
