package com.wy.action.algorithm;

import com.wy.action.util.Print;
import org.junit.Test;

import java.util.List;

/**
 * 排序算法
 * @Author wangyong
 * @Date 2019-09-07
 */
public class SortApp {


    /**
     * 冒泡排序
     * @param arr
     */
    public void bubble(int[] arr) {
        for (int i=0;i<arr.length;i++) {
            boolean flag = true;
            for(int j=0; j<arr.length-i-1; j++) {
                if (arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = false;
                }

            }
            if (flag == true) {//没有交换，说明排序好了
                return;
            }
        }
    }

    /**
     * 插入排序 遍历元素，插到比他大的前面
     * @param arr
     */
    public void insertSort(int[] arr) {

        for (int i=1; i<arr.length; i++) {
            int j=i-1;
            int value = arr[i];
            for (; j>=0 ; j--) {
                if (arr[j]>value) {
                    arr[j+1] = arr[j];// 元素往后移动
                } else {
                    break;
                }
            }
            arr[j+1] = value;
        }
    }

    /**
     * 选择排序，分为排序区间和未排序区间，从未排序区间中找到最小元素，插入到已排序区间的末尾
     * @param arr
     */
    public void selectInsert(int[] arr) {
        int temp;
        for (int i=0; i<arr.length-1 ;i++) {
            int min = i;
            for (int j=i;j<arr.length;j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }

    }

    @Test
    public void test() {
        int[] arr = {2,3,1,5,7,4,9};
        selectInsert(arr);
        Print.print(arr);
    }

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


    /**
     * 合并两个有序数组
     * https://leetcode.com/problems/merge-sorted-array/
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1, j=n-1, k = m+n-1;
        while (i>=0 && j>=0) {
            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums2[j--];
            }else {
                nums1[k--] = nums1[i--];
            }
        }
        while (j>=0) {
            nums1[k--] = nums2[j--];
        }
    }

    @Test
    public void mergeTest() {
        int[] arr = {1,2,3,7,12,0,0,0,0,0};
        merge(arr,5, new int[]{2,5,6}, 3);
        Print.print(arr);
    }
}
