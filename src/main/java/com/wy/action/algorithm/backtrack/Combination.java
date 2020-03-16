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
        for(int i=1; i<=n;i++) {
            List<Integer> one = new ArrayList<>();
            one.add(i);
            dfs(result, n,k, i, one);
        }
        return result;
    }

    private void dfs(List<List<Integer>> result, int n, int k, int i,List<Integer> one) {
        if (one.size() == k) {
            result.add(one);
            return;
        }

        for(int j=i+1; j<=n; j++) {
            List<Integer> newOne = new ArrayList<>(one);
            newOne.add(j);
            dfs(result,n, k,j, newOne);
        }
    }

    @Test
    public void test() {

//        System.out.println(combine(4,2));
        System.out.println(combine(4,3));
    }

}
