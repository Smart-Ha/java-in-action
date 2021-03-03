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

    /**
     * 判断是否是单调性
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length <= 2) {
            return true;
        }
        Boolean greater = null;
        for(int i=1; i< A.length; i++) {
            if (A[i] ==A[i-1]) {
                continue;
            }
            boolean f = A[i] > A[i-1];
            if (greater == null) {
                greater = f;
            }
            if (greater != f) {
                return false;
            }
        }
        return true;

    }

    /**
     * 寻找最多的元素
     * https://leetcode-cn.com/problems/majority-element/
     * 如果我们把众数记为 +1+1，把其他数记为 -1−1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
     **/
    public int majorityElement(int[] nums) {
        Integer candidate = nums[0];
        int count = 1;

        for (int i=1; i< nums.length; i++) {

            if (count == 0) {
                candidate = nums[i];
            }
            count += candidate== nums[i]?1:-1;
        }
        return candidate;

    }

    /**
     * 阶乘后的0的个数
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        // key 只跟2和5的对数有关，因为2总是比5多，所以只考虑5就行，有几个5就有几个零
        int count = 0;
        while (n>0) {
            n /= 5;
            count += n;
        }
        return count;
    }
}
