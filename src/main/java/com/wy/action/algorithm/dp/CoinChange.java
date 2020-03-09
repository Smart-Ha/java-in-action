package com.wy.action.algorithm.dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author wangyong
 * @Date 2019-12-16
 */
public class CoinChange {

    /**
     * 现有硬币1，3，5，等， 找出价值是value的硬币最小个数
     * 状态转移表法
     * @param coins
     * @param w
     * @return
     */
    public int getMinCount(int[] coins, int w) {
        int[] state = new int[w+1];
        for (int i=0; i<state.length;i++) {
            state[i] = -1;
        }
        state[0] = 0;
        for(int a = 1;a<=w; a++) {
            for(int i=0;i<coins.length; i++) {
                int value = coins[i];
                if(a-value>=0 && state[a-value]>=0) {
                    value = state[a-value]+1;
                    if (state[a] == -1 || state[a]> value) {
                        state[a] = value;
                    }
                }
            }
        }
        return state[w];
    }

    /**
     * 状态转移方程法
     * 状态转移方程： f(w) = min(f(w-1),f(w-1))
     * @param coins
     * @param w
     * @return
     */
    public int getMinCount2(Integer[] coins, int w) {
        count = 0;
        int[] state = new int[w+1];
        for (int i=0;i< coins.length; i++) {
            state[i+1] = coins[i];
        }
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2-i1;
            }
        };
        Arrays.sort(coins, cmp);//从大到小
        return getMinCountR(state,coins, w);
    }
    int count = 0;
    private int getMinCountR(int[] state, Integer[] coins, int w) {

        int min = Integer.MAX_VALUE;
        if (state[w] >0 ){
            return state[w];
        }

        for (int i=0;i< coins.length; i++) {
            if(w-coins[i] < 0) {
                continue;
            }
            if (state[w-coins[i]]>0) {//当前值的最小值
                min = state[w-coins[i]];
            }
            int value = getMinCountR(state, coins, w-coins[i]);
            if (state[w-coins[i]] == 0) {//赋值
                state[w-coins[i]] = value;
            } else if (state[w-coins[i]] > value) {//取更小值
                state[w-coins[i]] = value;
            }
            if (value == 1) {
                min = 1;
                break;
            }
            System.out.println(count++);
            if (value < min) {
                min = value;
            }
        }
        return min+1;
    }


    @Test
    public void test() {
        int[] arr = {1,3,5};
        Integer[] arr1 = {1,3,5};
        System.out.println(getMinCount(arr, 14));
        System.out.println(getMinCount2(arr1, 21));
    }
}
