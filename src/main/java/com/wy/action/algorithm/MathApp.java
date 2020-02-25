package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-02-21
 */
public class MathApp {

    /**
     * 不用乘除法，mod 求除法
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        return 1;
    }

    /**
     * 找出最小的正整数
     */
    public int firstMissingPositive(int[] nums) {

        int min = Integer.MAX_VALUE;
        boolean[] status = new boolean[nums.length+2];
        for(int i=0; i<nums.length; i++) {
            if (nums[i]>0) {
                if (min > nums[i]) {
                    min = nums[i];
                }
                if(nums[i]<=nums.length) {
                    status[nums[i]] = true;
                }
            }
        }
        if(min >1) {
            return 1;
        }

        for(int i=1; i<status.length; i++) {
            if (!status[i]) {
                return i;
            }
        }


        return 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1,2,0}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{1}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{3,4,-1,1}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{7,8,9,11,12}));
        Assert.assertEquals(3, firstMissingPositive(new int[]{1,7,8,9,11,12,2}));
    }
}
