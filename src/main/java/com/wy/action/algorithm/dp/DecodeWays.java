package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-23
 */
public class DecodeWays {

    /**
     * 给一组数字，有多少种解码方式 注意0不能单独存在，否则直接返回0
     * https://leetcode.com/problems/decode-ways/
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if ( s.length()>0 && s.charAt(0) == '0') return 0;

        if (s.length() <2) return s.length();
        int[] status = new int[s.length()+1];
        status[0] = 1;
        status[1] = s.charAt(0) =='0'?0:1;
        for(int i=2; i <= s.length(); i++) {
            int first = Integer.parseInt(s.substring(i-1, i));
            int second = Integer.parseInt(s.substring(i-2, i));
            if (first>0 && first < 10) {
                status[i] += status[i-1];
            }
            if (second>9 && second<=26) {
                status[i] += status[i-2];
            }
        }
        return status[s.length()];
    }

    @Test
    public void test() {
        Assert.assertEquals(0, numDecodings("01"));
        Assert.assertEquals(0, numDecodings("100"));
        Assert.assertEquals(2, numDecodings("12"));
        Assert.assertEquals(3, numDecodings("122"));
        Assert.assertEquals(1, numDecodings("110"));
    }
}
