package com.wy.action.algorithm.dynamic;

/**
 * 背包问题 动态规划解法
 * @Author wangyong
 * @Date 2019-12-12
 */
public class Package {
    private int max = Integer.MIN_VALUE;
    /**
     * 动态规划的思路：
     *  我们把问题分解为多个阶段，每个阶段对应一个决策，记录每个阶段的状态集合（去掉重复的），
     *  然后通过当前阶段的状态几个，来退到下一个阶段的额状态集合，动态的推进
     *  [2,3,9,11,4,4,5] w=15
     */
    public int find (int[] items, int w) {
        max = Integer.MIN_VALUE;
        boolean[] states = new boolean[w+1];//key为背包能装的数量，true表示能有
        states[0] = true;//第一个行需要特殊处理
        if (items[0] < w) {
            states[items[0]] = true;
        }
        for (int i=0; i< items.length;i++) {//动态规划遍历每个物品
            for(int j=w-items[i]; j>=0; j--) {
                if (states[j] == true) {//如果背包能装这么多。那么再装items[i]
                    states[j+items[i]] = true;
                }
            }
        }
        for(int i=w;i>=0;i--) {
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Package pack = new Package();
        int[] item = {2,3,9,11,4,4,5};
        int w = 15;
        System.out.println(pack.find(item, w));
    }
}

