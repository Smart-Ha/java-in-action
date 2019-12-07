package com.wy.action.algorithm.mapreduce;

import org.junit.Test;

/**
 * 获取逆序对个数
 * @Author wangyong
 * @Date 2019-12-07
 */
public class GetReverseNum {

    int count;
    /**
     * 0.求一组数据中逆序对的个数 2，4，3，1，5，6 -> （2，1）,(4,3), (4,1), (3,1)
     * 使用归并排序，排序过程中，分两块,两块的逆序对为k1,k2，然后再比较两块之间的逆序对k3，再比较过程中，由于每块都是有序的，所以可以通过临界值直接算出个数
     */

    public int getReverseNum(int[] arr) {
        mergeSort(arr, 0 ,arr.length-1);
        return count;
    }

    private void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high)/2;
        mergeSort(arr,  low, mid);
        mergeSort(arr, mid+1, high);
        merge(arr, low, mid, high);
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int i=low,j=mid+1;
        int[] tmp = new int[high-low+1];
        int k = 0;//排序
        while (i<=mid && j<=high) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {//前者比后者大，是逆序对
                count += (mid - i+1);// 因为i到mid之间是从小到大有序的，所以 i到mid都比a[j]大
                tmp[k++] = arr[j++];
            }
        }

        //拷贝剩下的
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= high) {
            tmp[k++] = arr[j++];
        }
        for(i=0; i <= high-low; ++i) {
            arr[low+i] = tmp[i];
        }
    }

    @Test
    public void test() {
        int[] arr = {2,4,3,1,5,6};
        System.out.println(getReverseNum(arr));
    }

}