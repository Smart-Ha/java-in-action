package com.wy.action.algorithm.dp;

/**
 * @Author wangyong
 * @Date 2021-02-18
 */
public class NumDistinct {

    /**
     * 求不同的子序列的个数
     * https://leetcode-cn.com/problems/distinct-subsequences/
     */

    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()+1][s.length()+1];

        /**
         * i=0表示t为空串，那么子串的个数是1
         */
        for (int i=0; i<s.length()+1; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i< t.length()+1; i++) {
            for (int j=1; j<s.length()+1; j++) {
                if (t.charAt(i-1) == s.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]// s[0,j]的最后一位和t[0,i]的最后一位对应
                            + dp[i][j-1];// s[0,j]的最后一位和t[0,i]的最后一位不对应，即可忽略掉s的最后一位
                } else {
                    dp[i][j] = dp[i][j-1];
                }

            }
        }
        return dp[t.length()][s.length()];
    }
}
