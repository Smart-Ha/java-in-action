package com.wy.action.algorithm.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-03-16
 */
public class Combination {

    /**
     * 从1-n的数中， 返回ke个数排列的所有组合
     * https://leetcode.com/problems/combinations/
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, n,k,  new ArrayList<>(), 1);
        return result;
    }

    private void dfs(List<List<Integer>> result, int n, int k,List<Integer> one, int i) {
        if (one.size() == k) {
            result.add(new ArrayList<>(one));
            return;
        }

        for(int j=i; j<=n; j++) {
            one.add(j);
            dfs(result,n, k, one, j+1);
            one.remove(one.size()-1);
        }
    }

    @Test
    public void test() {

        System.out.println(combine(4,2));
        System.out.println(combine(4,3));
    }

    /**
     * 找出所有子序列
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i=1; i<=nums.length; i++) {
            subsetsDfs(result, nums,i,  new ArrayList<>(), 0);
        }

        return result;
    }
    private void subsetsDfs(List<List<Integer>> result, int[] num, int k,List<Integer> one, int i) {
        if (one.size() == k) {
            result.add(new ArrayList<>(one));
            return;
        }

        for(int j=i; j<num.length; j++) {
            one.add(num[j]);
            subsetsDfs(result,num, k, one, j+1);
            one.remove(one.size()-1);
        }
    }

    @Test
    public void subsetsTest() {

        System.out.println(subsets(new int[] {1,2,3}));
        System.out.println(subsets(new int[] {1,2,3,4}));
    }

}
