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
        int len = nums.length-1;
        int index = -1;
        int p = nums.length-1;
        int count = 0;
        while (len>0) {
            int max = 0;
            for (int i=p;i>=0 ;i--) {
                if (nums[i] >= p-i && max <= p-i) {
                    max = p-i;
                    index = i;
                }
            }
            len -= max;//减去最后的长度
            count++;
            p = index;
        }

        return count;
    }

    @Test
    public void  test2() {
        Assert.assertEquals(2,jump(new int[]{2,3,1,1,4}));
    }
}
