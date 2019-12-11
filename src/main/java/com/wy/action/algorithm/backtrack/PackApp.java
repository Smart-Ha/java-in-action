package com.wy.action.algorithm.backtrack;

import org.junit.Test;

/**
 * 背包问题
 * @Author wangyong
 * @Date 2019-12-07
 */
public class PackApp {

    int max = Integer.MIN_VALUE;

    /**
     * 0-1背包问题，背包的总承重w，现在有m个物体，重量不等，且不可分割，选择n件物品，在不超过最大承重的情况下，重量达到最大
     *
     * item是= 【2,3,4,6,8,9,10】 w=32
     */
    public int getMax(int w, int[] items, int n) {
        //采用回溯法
        find(0,0, w, items,0, n);
        return max;
    }

    private void find(int currentW, int i, int w, int[] items, int currentN, int n) {
        if (i >= items.length || currentW >= w || currentN >= n) {
            if (currentW > max) {
                max = currentW;
            }
            return;
        }


        // 每次拿到一个物体的时候，有两种情况，1.不放背包，2.放进背包
        find(currentW, i+1, w, items,currentN, n);//1.不放背包
        if (currentW + items[i] <= w) {//2.放进背包 超过了重量,就不要再装了
            find(currentW+items[i], i+1, w,items,currentN+1, n);
        }
    }

    @Test
    public void test() {
        int[] items = {2,3,4,6,8,9,10};
        System.out.println(getMax(32,items, 4));
    }
}
