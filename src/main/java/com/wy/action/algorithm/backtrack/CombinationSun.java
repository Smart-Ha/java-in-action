package com.wy.action.algorithm.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2019-12-27
 */
public class CombinationSun {

    /**
     * 给出一组数，和目标，从数组中找吹一个组合，组合内的数相加等于目标，每个数可以出现多次
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    List<List<Integer>> get(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        getR(result, arr,new ArrayList<>(),0, 0, target);
        return result;
    }

    private void getR(List<List<Integer>> result,int[] arr, List<Integer> one, int currentV, int addV, int target) {
        if(one.size()>0 && one.get(one.size()-1) > addV) {//去重，按从小到大
            return;
        }

        if (currentV + addV == target) {

            one.add(addV);
            result.add(one);
            return;
        } else if (currentV + addV > target) {
            return;
        }
        if (addV >0 ) {
            one.add(addV);
            currentV += addV;
        }
        if (currentV <= target) {
            for (int i=0; i< arr.length; i++) {
                List<Integer> two =  new ArrayList<Integer>(one);
                getR(result, arr, two, currentV, arr[i], target);
            }
        }
    }

    @Test
    public void test() {
        int[] arr = {2,3,5};
        System.out.println(get(arr, 8));
    }
}
