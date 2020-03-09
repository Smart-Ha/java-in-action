package com.wy.action.algorithm.dfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-09
 */
public class CanJump {

    /**
     * 给一组数，每个位置的数代表当前位置能跳的步数，判断是否能到达最后
     * https://leetcode.com/problems/jump-game/
     * @param arr [2,3,1,1,4] true [3,2,1,0,4] false
     * @return
     */
    public boolean canJump(int[] arr) {
        /**
         * 从倒数第二位开始往前找，找离上次位置（last） 最近一小步的索引， 更新last, 看是否能够到达队首
         */

        int last = arr.length-1;
        for (int i=last-1; i>=0; i--) {
            if (arr[i]+i >= last) {
                last = i;
            }
        }
        return last == 0;

    }


    @Test
    public void test() {
        Assert.assertEquals(true, canJump(new int[]{2,3,1,1,4}));
        Assert.assertEquals(false, canJump(new int[]{3,2,1,0,4}));
    }
}
