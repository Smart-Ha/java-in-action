package com.wy.action.algorithm.dp;

public class Rob {


    /**
     * 打家劫舍 不能抢相邻的房子，求能抢到的最大值
     * https://leetcode-cn.com/problems/house-robber/
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /**
         * key: 使用动态规划 dp[i] 表示 能抢到的最大金额
         * 状态转移方程为： dp[i] = max(dp[i-2]+arr[i], d[i-1]);
         *
         */
        int n = nums.length;
        if (n==1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2; i< n;i++) {
            dp[i] = Math.max(dp[i-2]+ nums[i], dp[i-1]);
        }
        return dp[n-1];
    }
}
