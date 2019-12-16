package com.wy.action.algorithm.backtrack;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

/**
 * @Author wangyong
 * @Date 2019-12-13
 */
public class CoinChange {
    int min = Integer.MAX_VALUE;


    /**
     * 现有硬币1，3，5，等， 找出价值是value的硬币最小个数
     * @param arr
     * @param value
     * @return
     */


    public int getMinCount( int arr[], int value) {
        int[] state = new int[value+1];
        getMinR(0,0,arr,state, value);
        return state[value];

    }

    public void getMinR(int n,int currentV, int arr[],int[] state, int value) {
        if (currentV > value) {
            return ;
        } else if (currentV == value) {
            if (state[value] == 0) {
                state[value] = n;
            } else if (state[value] > n) {
                state[value] = n;
            }
            return;
        }
        if (state[currentV] == 0) {
            state[currentV] = n;
        } else if (state[currentV] > n) {//取最小值
            state[currentV] = n;
        }

        for(int i=0; i<arr.length; i++) {
            getMinR(n+1,currentV+arr[i], arr,state, value);
        }
    }

    @Test
    public void test() {
        int[] arr = {1,3,5};

        System.out.println(getMinCount(arr,21));
    }
}
