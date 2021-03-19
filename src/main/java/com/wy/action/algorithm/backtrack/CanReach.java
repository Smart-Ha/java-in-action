package com.wy.action.algorithm.backtrack;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2021-03-18
 */
public class CanReach {

    /**
     * 跳跃游戏 III
     * https://leetcode-cn.com/problems/jump-game-iii/
     * @param arr
     * @param start
     * @return
     */
    boolean res = false;
    public boolean canReach(int[] arr, int start) {
        backtrack(arr, start, 0);
        return res;
    }

    private void backtrack(int[] arr, int start, int count) {
        if (res) {
            return;
        }
        if (start < 0 || start >= arr.length) {
            return;
        }
        if (arr[start] == 0) {
            res = true;
            return;
        }
        if( count == arr.length) {
            return;
        }

        if (start + arr[start] < arr.length) {
            count++;
            backtrack(arr, start + arr[start], count);
            count--;

        }

        if (start - arr[start] > -1) {
            count++;
            backtrack(arr, start - arr[start], count);
            count--;
        }
    }

    @Test
    public void test() {
        int[] arr = {4,2,3,0,3,1,2};
        Assert.assertEquals(true,canReach(arr, 5) );

    }
}
