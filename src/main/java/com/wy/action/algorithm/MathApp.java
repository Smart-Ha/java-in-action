package com.wy.action.algorithm;

import com.wy.action.util.Print;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author wangyong
 * @Date 2020-02-21
 */
public class MathApp {

    /**
     * 不用乘除法，mod 求除法
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        return 1;
    }

    /**
     * 找出最小的正整数
     */
    public int firstMissingPositive(int[] nums) {

        int min = Integer.MAX_VALUE;
        boolean[] status = new boolean[nums.length+2];
        for(int i=0; i<nums.length; i++) {
            if (nums[i]>0) {
                if (min > nums[i]) {
                    min = nums[i];
                }
                if(nums[i]<=nums.length) {
                    status[nums[i]] = true;
                }
            }
        }
        if(min >1) {
            return 1;
        }

        for(int i=1; i<status.length; i++) {
            if (!status[i]) {
                return i;
            }
        }


        return 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1,2,0}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{1}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{3,4,-1,1}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{7,8,9,11,12}));
        Assert.assertEquals(3, firstMissingPositive(new int[]{1,7,8,9,11,12,2}));
    }

    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     * https://leetcode.com/problems/trapping-rain-water/
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <3) return 0;
        int result = 0;
        int left = 0;
        int right = height.length-1;
        while( left<right && height[left]<height[left+1]) {
            left++;
        }
        while (left< right && height[right]<height[right-1]) {
            right--;
        }

        while (left < right) {
            int leftValue = height[left];
            int rightValue = height[right];
            if (leftValue <=rightValue) {
                while (left< right && leftValue>= height[++left]) {
                    result += leftValue - height[left];
                }
            } else {
                while (left< right && rightValue>= height[--right]) {
                    result += rightValue - height[right];
                }
            }
        }
        return result;
    }

    @Test
    public void testTrap() {
        Assert.assertEquals(6, trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(3, trap(new int[]{2,1,0,2}));
        Assert.assertEquals(14, trap(new int[]{5,2,1,2,1,5}));
    }

    /**
     * 字符串数字的乘法
     * @param num1
     * @param num2
     *
     * "123456789"
     * "987654321"
     * @return
     */
    public String multiply(String num1, String num2) {
        // 使用现实中乘法的计算步骤，将每个数存在数组中以免溢出
        int[] pos = new int[num1.length() + num2.length()];

        for (int i=num1.length()-1; i>=0; i--) {
            int p1 = num1.charAt(i) - 48;
            for (int j=num2.length()-1; j>=0; j--) {
                int p2 = num2.charAt(j) - 48;
                int sum =  p1 * p2 + pos[i+j+1];// 当前计算值+ 原位置的值
                pos[i+j+1] = sum%10;//个位
                pos[i+j] += sum /10;// 十位
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<pos.length;i++) {
            if (sb.length() == 0 && pos[i] == 0) {
                continue;
            }
            sb.append(pos[i]);
        }
        return sb.length()==0 ? "0" :sb.toString();

    }

    @Test
    public void testMultiply(){
//        Assert.assertEquals("6", multiply("2", "3"));
        Assert.assertEquals("56088", multiply("123", "456"));
        Assert.assertEquals("121932631112635269", multiply("123456789", "987654321"));
        System.out.println(Long.MAX_VALUE);
    }

    /**
     * 实现x的n次方
     * https://leetcode.com/problems/powx-n/
     * @param x
     * @param n
     * @return
     */
    public double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if(n == Integer.MIN_VALUE) {//处理边界情况
            x = x * x;
            n = n/2;
        }

        if (n<0) {
            x = 1/x;
            n = -n;
        }
        return  n%2==0? power(x*x, n/2) : x*power(x*x, n/2);
    }

    @Test
    public void powerTest(){
        System.out.println(power(2.10000, 3));
    }

    /**
     * 找出一个数组，连续子数组的和最大值
     * https://leetcode.com/problems/maximum-subarray/
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /**
         * 计算i前面的累加值maxCurr和当前nums[i], 哪个大赋予maxCurr
         * 比较maxCurr和max，取较大的值
         */
        int max = nums[0],maxCurr = nums[0];
        for (int i=1; i<nums.length; i++) {
            maxCurr = Math.max(maxCurr+nums[i], nums[i]);
            max = Math.max(maxCurr, max);
        }
        return max;
    }

    @Test
    public void testMaxSubArray() {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }




    /**
     * https://leetcode.com/problems/merge-intervals/submissions/
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <=1 ) {
            return intervals;
        }
        //排序,
        Arrays.sort(intervals, (o1,o2)-> {
            return Integer.compare(o1[0], o2[0]);
        });
        List<int[]> result = new ArrayList<>();
        int[] one = intervals[0];
        result.add(one);
        for (int i=0; i<intervals.length; i++) {
            if (intervals[i][0]<= one[1] ) {// 有交叉
                one[1] = Math.max(one[1],intervals[i][1]);

            } else {
                one = intervals[i];
                result.add(one);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void mergeTest() {
        int[][] arr = {
                {1,4},
                {2,3}
        };
        System.out.println(merge(arr));
    }

    /**
     * 一组没有交叉覆盖的数组端，给一个数组段，插入到原来的数组序列中，并合并
     * https://leetcode.com/problems/insert-interval/
     * @param intervals [[1,2],[3,5],[6,7],[8,10],[12,16]]
     * @param newInterval [4,8]
     * @return [[1,2],[3,10],[12,16]]
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) {
            intervals =  new int[1][2];
            intervals[0] = newInterval;
            return intervals;
        }
        List<int[]> result = new ArrayList<>();
        int index = intervals.length;
        for (int i=0; i<intervals.length; i++) {
            if (newInterval[1] < intervals[i][0]) {// 插在最前面
                // 剩余的添加到list中
                index = i;
                break;
            } else if (newInterval[0] <= intervals[i][0] ){// 新的第一个数在前面
                if(newInterval[1] <= intervals[i][1]) {// 中间有交叉
                    newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                    newInterval[1] = intervals[i][1];
                    index = i+1;
                    break;
                } else {//新的包含原来的
                    index = i+1;
                    continue;
                }
            } else if (newInterval[0] <= intervals[i][1] ){// 新的第一个数在右边 有交叉
                if (newInterval[1] <= intervals[i][1]) {// 旧的包含新的
                    newInterval[0] = intervals[i][0];
                    newInterval[1] = intervals[i][1];
                    index = i+1;
                    break;
                } else {//
                    newInterval[0] = intervals[i][0];
                    index = i+1;
                }
            } else  {
                result.add(intervals[i]);
            }
        }

        result.add(newInterval);
        for(int i=index; i<intervals.length;i++) {
            result.add(intervals[i]);
        }
        return result.toArray(new int[result.size()][2]);
    }

    @Test
    public void insertTest() {
//        insert(new int[][]{{1,3},{6,9}}, new int[] {2,5});
//        Print.print(insert(new int[][]{{1,5}}, new int[] {2,3}));
//        Print.print(insert(new int[][]{{1,5}}, new int[] {2,7}));
        Print.print(insert(new int[][]{{1,5}}, new int[] {6,8}));
        Print.print(insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[] {4,8}));
    }

    /**
     * 求n的全排列中第k个值
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] arr = new int[n+1];
        List<Integer> list = new ArrayList<>();
        arr[0] = 1;
        for(int i=1; i<=n; i++) {
            arr[i] = i* arr[i-1];
            list.add(i);
        }

        int num = 0;
        int one ;
        StringBuilder sb = new StringBuilder();
        while (!list.isEmpty()) {
            if( n== 1){
                sb.append(list.remove(0));
                break;
            }
            num = arr[n-1];
            one = (k-1)/num;
            sb.append(list.remove(one));
            k = k- one*num;
            n--;
        }
        return sb.toString();
    }

    @Test
    public void getPermutationTest() {
        Assert.assertEquals("132", getPermutation(3,2));
        Assert.assertEquals("213", getPermutation(3,3));
        Assert.assertEquals("2314", getPermutation(4,9));
    }

    /**
     * 给数组中组合成的数+1
     * https://leetcode.com/problems/plus-one/
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1; i>=0;i--) {
            if (digits[i]+1<10) {
                digits[i]++;
                return digits;
            }
            digits[i] = (digits[i]+1)%10;
        }
        int[] newDig = new int[digits.length+1];
        System.arraycopy(digits, 0, newDig, 1,
                digits.length);
        newDig[0] = 1;
        return newDig;
    }
    @Test
    public void plusOneTest() {
        Assert.assertEquals(1000, plusOne(new int[] {9,9,9}));
    }

    /**
     * 对两个二进制数求和
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int i = a.length()-1, j = b.length()-1;
        int sum, f=0;
        StringBuilder sb = new StringBuilder();
        while (i>=0 || j>=0) {
            sum = f;
            if (i>=0) {
                sum+= a.charAt(i--)-'0';
            }
            if(j>=0) {
                sum += b.charAt(j--)-'0';
            }
            sb.append(sum%2);
            f = sum/2;
        }
        if(f==1) {
            sb.append(f);
        }
        return sb.reverse().toString();
    }
}
