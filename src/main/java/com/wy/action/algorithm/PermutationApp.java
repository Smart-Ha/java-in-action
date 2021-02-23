package com.wy.action.algorithm;


/**
 * 排列算法
 */
public class PermutationApp {

    /**
     * 下一个更大一点的排列
     * https://leetcode-cn.com/problems/next-permutation/
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        // 1.倒序找出第一个比后一位数字小的数(这个地方就是需要交换的)
        while (i>=0 && nums[i]>= nums[i+1]) {
            i--;
        }
        // 2.从i-末尾，找出第一个比num[i]大的数，交换
        if (i>=0) {
            int j= nums.length-1;
            while (nums[i]>=nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 3.i+1之后都是降序的数字，因为第一步已经验证了，所以改为升序即可
        traverse(nums, i+1);
    }

    private void traverse(int[] nums, int i) {
        int j = nums.length;
        while (i<j) {
            swap(nums, i,j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
