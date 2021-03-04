package com.wy.action.algorithm.dp;

public class MaxArrSum {

    /**
     * 最大子数组和
     * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/comments/
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // dp[i] 表示前I的最大连续子数组和的值
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        int res = dp[0];
        for(int i=1; i<nums.length ; i++) {
            if (dp[i-1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i-1] + nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
