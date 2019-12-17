package com.wy.action.algorithm.dynamic;

import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2019-12-17
 */
public class MaxIncrease {

    /**
     * 我们有一个数字序列包含 n 个不同的数字，如何求出这个序列中的最长递增子序列长度？
     * 比如 2, 9, 3, 6, 5, 1, 7
     * 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
     */

    public int getMax(int[] arr) {
        int[] state = new  int[arr.length];
        state[0] = 1;
        for (int i=1;i<arr.length ;i++) {
            int max = 0;
            for(int j=i-1;j>=0;j--) {
                if (arr[j] < arr[i]) {//找出比arr[i]小的数中state[j]最大的
                     if (state[j] > max) {
                         max = state[j];
                     }
                }
            }
            state[i] = max+1;
        }

        int max = 0;
        for (int i=0;i<arr.length;i++) {
            if (max < state[i]) {
                max = state[i];
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] arr = {2, 9, 3, 6, 5, 1, 7,2,3,12,45,12,566};
        System.out.println(getMax(arr));
    }
}
