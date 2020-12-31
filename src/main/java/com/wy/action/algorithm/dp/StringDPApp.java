package com.wy.action.algorithm.dp;

/**
 * 字符串dp
 * @Author wangyong
 * @Date 2020-12-29
 */
public class StringDPApp {


    /**
     * 求两个字符串的最小编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        /**
         * db table 定义是table[m][n] 表示word(0, m) 和 word2(0, n)的最小编辑距离
         */
        int[][] table = new int[m+1][n+1];

        for(int i=1;i<=m; i++) {
            table[i][0] = i;
        }

        for(int i=1;i<=n; i++) {
            table[0][i] = i;
        }

        for( int i=1; i<=m ;i++) {

            for (int j=1; j<=n; j++) {
                // 如果相等
                if (word1.charAt(i) == word2.charAt(j)) {
                    table[i][j] = table[i-1][j-1];
                } else {

                    // 添加, 删除,替换
                    table[i][j] = Math.min(Math.min(table[i][j-1], table[i-1][j]),table[i-1][j-1]) + 1;
                }

            }
        }
        return table[m][n];

    }
}
