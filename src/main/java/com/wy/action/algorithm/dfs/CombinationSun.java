package com.wy.action.algorithm.dfs;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author wangyong
 * @Date 2019-12-27
 */
public class CombinationSun {

    /**
     * 给出一组数，和目标，从数组中找吹一个组合，组合内的数相加等于目标，每个数可以出现多次，结果按从小到大的顺序，不能出现重复的
     * key1: 每次查找时累加时，只从大于等于本身的数开始
     * key2：如果是递归算法，那么可以只用一个数组来存储数据
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
        int min = Integer.MAX_VALUE;
        for (int i=0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        int[] store = new int[target/min +1];
        getR(result, arr, target, store, 0,0);
        return result;
    }

    /**
     * target 从大往小减，减到0为止
     * @param result
     * @param arr
     * @param target
     * @param store
     * @param fromIndex
     */
    public void getR(List<List<Integer>> result, int[] arr, int target, int[] store, int storeLen, int fromIndex) {
        if (target ==0 ) {// 添加结果
            addRes(result, store, storeLen);
        }
        for (int i=fromIndex; i<arr.length; i++) {
            if (target  >= arr[i] ) {
                store[storeLen] = arr[i];
                getR(result, arr, target- arr[i], store, storeLen+1, i);
            }
        }
    }

    private void addRes(List<List<Integer>> result, int[] store, int storeLen) {
        List<Integer> one = new ArrayList<>();
        for (int i=0; i< storeLen; i++) {
            one.add(store[i]);
        }
        result.add(one);
    }


    @Test
    public void test() {
        int[] arr = {2,3,5};
        System.out.println(get(arr, 8));
    }
}
