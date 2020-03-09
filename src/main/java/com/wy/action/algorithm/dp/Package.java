package com.wy.action.algorithm.dp;

import com.wy.action.util.Print;
import org.junit.Test;

/**
 * 背包问题 动态规划解法
 * @Author wangyong
 * @Date 2019-12-12
 */
public class Package {
    private int max = Integer.MIN_VALUE;
    private int maxValue = Integer.MIN_VALUE;
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

    /**
     * 获取在不超过w的情况下，背包能装的最大价值
     * @param w
     * @param items
     * @param itemValues
     * @return
     */

    public int getMaxValue( int w, int[] items, int[] itemValues) {
        maxValue = Integer.MIN_VALUE;
        // 行表示 选到了第几个物品，列表示 当前层的重量 value表示 对应的总价值
        int[][] valueStates = new int[items.length][w+1];
        // 初始化
        for(int k=0; k< items.length; k++) {
            for (int j=0; j<w+1; j++) {
                valueStates[k][j] = -1;
            }
        }
        valueStates[0][0] = 0;
        if (items[0] <= w) {
            valueStates[0][items[0]] = itemValues[0];
        }
        for (int i=1;i<items.length;i++) {
            // 不选物品
            for (int j=0; j<w+1;j++) {
                if (valueStates[i-1][j] >=0 ) {
                    valueStates[i][j] = valueStates[i-1][j];
                }
            }
            Print.print(valueStates);
            //选物品
            for (int j=0;j<= w - items[i];j++) {
                if (valueStates[i-1][j] >= 0 ) {//排除掉无效的值
                    int value = valueStates[i-1][j] + itemValues[i];
                    if (value > valueStates[i][j+items[i]]) {//找出这一行的最大值
                        valueStates[i][j+items[i]] = value;
                    }
                }
                Print.print(valueStates);

            }
        }


        for (int i=0;i<w+1;i++) {
            //找最后一行的最大值
            if (valueStates[items.length-1][i] > maxValue) {
                maxValue = valueStates[items.length-1][i];
            }
        }
        return maxValue;
    }

   @Test
    public void test() {
       int[] items = {2,3,4,6,8,9,10};
       int[] itemValues = {3,3,4,6,4,2,3};
       System.out.println(getMaxValue(14, items, itemValues));
   }
}

