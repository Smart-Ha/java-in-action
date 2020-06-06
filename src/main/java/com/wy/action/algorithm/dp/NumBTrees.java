package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-06-05
 */
public class NumBTrees {


    /**
     * 求包含1-n，n个数的二叉搜索树的个数
     * https://leetcode.com/problems/unique-binary-search-trees/
     * key: {1,2,3} 搜索二叉树的个数 和{1,2,4}, {2,3,4}一样，所以对于n个数，我们有n个根节点，根节点i的左子树有dp[i-1] 种搜索树，右子树有dp[n-i]中搜索树，根节点i的搜索树总数=dp[i-1] * dp[n-i]
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] count = new int[n+1];
        count[0] = 1;
        count[1] = 1;
        for (int i=2; i<=n; i++) {
            for (int root=1; root<=i; root++) {
                count[i] += count[i-root] * count[root-1];
            }
        }
        return count[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(5,numTrees(3));
        Assert.assertEquals(14,numTrees(4));
    }

}
