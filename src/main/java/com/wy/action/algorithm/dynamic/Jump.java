package com.wy.action.algorithm.dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-04
 */
public class Jump {

    /**
     * 给定一个非负整数数组，您最初位于该数组的第一个索引处。
     * 数组中的每个元素代表该位置的最大跳转长度。
     * 您的目标是在最少的跳数中达到最后的索引。
     * Input: [2,3,1,1,4]
     * Output: 2
     */
    public int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position != 0) {
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    public int jump2(int[] nums) {
        int jump = 0;
        int cEnd = 0;
        int cFarthest = 0;
        for(int i=0; i<nums.length; i++) {
            // i=cBegin 到cEnd = i+num[i] 直接来寻找跳的最远的，调整cEnd
            cFarthest = Math.max(cFarthest, i+nums[i]);
            if (i == cEnd) {
                jump++;
                cEnd = cFarthest;
            }
        }
        return jump;
    }

    @Test
    public void  test2() {
        Assert.assertEquals(2,jump(new int[]{2,3,1,1,4}));
    }
}
