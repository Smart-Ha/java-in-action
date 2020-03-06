package com.wy.action.algorithm.dfs;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author wangyong
 * @Date 2020-03-05
 */
public class PermuteUnique {

    /**
     * https://leetcode.com/problems/permutations-ii/
     * 求给出一组数的不重复排列组合
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permute(result,  new ArrayList<>(), Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return result;
    }

    private void permute(List<List<Integer>> result,
                         List<Integer> going, List<Integer> left) {
        if (left.size() ==0) {
            result.add(going);
            return;
        }

        for (int j=0; j<left.size(); j++) {//第index位,n种情况
            if (j>0 && left.get(j-1) ==left.get(j)) {// 相同的元素就不考虑了
                continue;
            }

            List<Integer> one = new ArrayList<>(going);
            List<Integer> leftOne = new ArrayList<>(left);
            leftOne.remove(j);
            one.add(left.get(j));
            permute(result,  one, leftOne);
        }
    }

    @Test
    public void testPermuteUnique () {
        int[] arr = {1,1,2};
//        System.out.println(permuteUnique(arr));
        System.out.println(permuteUnique2(arr));
    }


    /**
     * 试探法
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] status = new boolean[nums.length];//元素有没有被使用过
        dfs(nums, result,  new ArrayList<>(),status);
        return result;
    }

    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> one, boolean[] status) {
        if (one.size() == nums.length) {
            result.add(new ArrayList<>(one));
            return;
        }

        for (int i=0; i<nums.length;i++) {
            if (status[i]) {
                continue;
            }
            if (i>0 && nums[i-1]== nums[i] && !status[i-1]) {
                continue;
            }

            one.add(nums[i]);
            status[i] = true;
            dfs(nums,result, one,status);
            one.remove(one.size()-1);
            status[i] = false;
        }
    }

}
