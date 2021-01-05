package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author wangyong
 * @Date 2021-01-05
 */
public class StackApp {


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Map<Integer, Integer> value2Index = new HashMap<>();

        for(int i=0; i<nums2.length; i++) {
            value2Index.put(nums2[i], i);
        }

        for (int i=0; i<nums1.length; i++) {
            if (i>= nums2.length) {
                result[i] = -1;
                continue;
            }
            result[i] = -1;

            for (int j=value2Index.get(nums1[i]); j<nums2.length; j++) {
                if (nums2[j]> nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;

    }


    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] result = new int[n];
        for (int i=2*n-1; i>=0;i--) {
            while (!stack.empty() && stack.peek() < nums[i%n]) {
                stack.pop();
            }
            result[i%n] = stack.empty() ? -1 : stack.peek();
            stack.push(nums[i%n]);
        }
        return result;
    }

    public int[] nextGreaterElementsII(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i=0; i<n ;i++) {
            result[i] = -1;
           for(int j=i; j< 2*n; j++) {
               int k = j%n;
               if (nums[k]> nums[i]) {
                   result[i] = nums[k];
                   break;
               }

           }
        }
        return result;
    }
    public int nextGreaterElementIII(int n) {
       //1.找出后一位比前一位大的数
        char[]  chars = String.valueOf(n).toCharArray();
        int i= chars.length-1;
        while (i>0) {
            if (chars[i]>chars[i-1]) {
                break;
            }
            i--;
        }
        // 表示从大到小，没有更大的数
        if (i==0) {
            return -1;
        }
        //2.找出key之后的，比key大的最小数
        int smallest = i;
        for (int j=i; j<chars.length; j++) {
            if (chars[i-1] < chars[j] && chars[smallest] >= chars[j]) {
                smallest = j;
            }
        }
        // 3. 确定变换第一位置
        char temp = chars[i-1];
        chars[i-1] = chars[smallest];
        chars[smallest] = temp;
        // 4.将key后面的数排序
        Arrays.sort(chars, i, chars.length);
        long val = Long.parseLong(new String(chars));
        return val > Integer.MAX_VALUE ? -1: (int) val;
    }

    @Test
    public void nextGreaterElementIIITest() {
//        Assert.assertEquals(31, nextGreaterElementIII(13));
//        Assert.assertEquals(321, nextGreaterElementIII(312));
        Assert.assertEquals(-1, nextGreaterElementIII(332));
        Assert.assertEquals(230412, nextGreaterElementIII(230241));

    }
}
