package com.wy.action.algorithm.backtrack;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author wangyong
 * @Date 2020-02-25
 */
public class CombinationSum2 {
    /**
     * 给定一组数， 找出所有想加等于目标的组合，每个组合不能重复
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, result, target, new ArrayList<>(),0,0);
        return result;
    }

    private void combinationSum(int[] candidates, List<List<Integer>> result, int target,List<Integer> one, int sum, int i) {

        for (int k=i; k<candidates.length;k++) {
            if(sum+ candidates[k] > target) {
                break;
            }
            if (k-1>=i && candidates[k] == candidates[k-1]) {
                continue;
            }
           if(sum+ candidates[k] < target){
                List<Integer> newOne =  new ArrayList<>(one);
                newOne.add(candidates[k]);
                combinationSum(candidates,result, target,  newOne, sum+ candidates[k],k+1);
            } else  {
                one.add(candidates[k]);
                result.add(one);
                break;

            }
        }
    }

    @Test
    public void test() {
        int[] arr = {10,1,2,7,6,1,5};
        System.out.println(combinationSum2(arr, 8));
    }
}
