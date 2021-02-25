package com.wy.action.algorithm.dp;

import java.util.List;

/**
 * @Author wangyong
 * @Date 2021-02-25
 */
public class WordBreak {


    /**
     * 单词拆分
     * https://leetcode-cn.com/problems/word-break/
     * @param s  "applepenapple", wordDict = ["apple", "pen"]
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 动态规划，状态[i] 表示 0-i-1拆分的词都在wordDict中

        boolean[] dp = new boolean[s.length()+1];
        // 表示空串
        dp[0] = true;
        for (int i=1; i<=s.length(); i++) {
            for(int j=0; j<i; j++) {

                if (dp[j] && wordDict.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];

    }
}
