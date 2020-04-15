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
        int[] status = new int[s.length()];
        int i=0;
        status[0] = 1;
        for(i=1; i < s.length(); i++) {
            if (s.charAt(i-1) != '0' && Integer.parseInt(s.substring(i-1,i+1)) <=26) {
                if (s.charAt(i) == '0') {// 0不能单独存在
                    status[i] = status[i-1] ==1 ? 1 :status[i-1]-1;
                } else {
                    status[i] = 2*status[i-1]-1;
                }
            } else if (s.charAt(i) == '0' ) {
                return 0;
            } else {
                status[i] = status[i-1];
            }
        }
        return status[s.length()-1];
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
