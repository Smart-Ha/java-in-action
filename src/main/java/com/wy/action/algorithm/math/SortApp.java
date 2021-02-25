package com.wy.action.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2021-02-25
 */
public class SortApp {

    /**
     *  寻找旋转排序数组中的最小值
     *  https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
     * @param nums [4,5,6,7,0,1,2]
     * @return
     */
    public int findMin(int[] nums) {
        int res = nums[0];
        int i =0, j = nums.length-1;
        // 已排序好的
        if (res <= nums[j]) {
            return res;
        }
        while (i<j) {
            int mid = (i+j)/2;
            if (nums[mid]>=res) {
                i = mid+1;
            } else {
                res = nums[mid];
                j= mid-1;
            }
        }
        return Math.min(nums[i], res);

    }

    @Test
    public void findMin() {

        int nums[] = {4,5,6,7,0,1,2};
        Assert.assertEquals(0, findMin(nums));
    }

    /**
     * 最大间距 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题
     * https://leetcode-cn.com/problems/maximum-gap/
     * @param nums [3,6,9,1]
     * @return
     */
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        return 0;
    }
}
