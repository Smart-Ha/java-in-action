package com.wy.action.algorithm;

import java.util.List;

/**
 * 排序算法
 * @Author wangyong
 * @Date 2019-09-07
 */
public class SortApp {

    /**
     * 归并排序
     */
    public static void mergeSort(List<Integer> arr, int start, int end){
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        mergeC(arr,start, mid,end);
    }

    /**
     *
     * @param arr
     * @param start
     * @param mid
     * @param end
     */
    private static void mergeC(List<Integer> arr, int start,int mid, int end) {

    }
}
