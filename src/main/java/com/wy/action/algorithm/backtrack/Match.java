package com.wy.action.algorithm.backtrack;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-02-27
 */
public class Match {

    private boolean match = false;
    /**
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * https://leetcode.com/problems/wildcard-matching/
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String str, String pattern) {
        int startIndex = -1;// 保存的是上一个* 的位置
        int s=0,p=0;
        int match = 0;
        while (s<str.length()) {
            if (p<pattern.length() && (pattern.charAt(p)==str.charAt(s) || pattern.charAt(p)=='?')) {
                p++;
                s++;
            } else if (p<pattern.length() && pattern.charAt(p)=='*') {//如果p是*，p向前移动
                startIndex = p;
                match = s;
                p++;
            } else if(startIndex != -1) {// 如果
                p = startIndex + 1;
                match++;
                s = match;
            } else {
                return false;
            }

        }

        while (p<pattern.length() && pattern.charAt(p)=='*') {
            p++;
        }
        return p==pattern.length();
    }


    @Test
    public void test() {
        Assert.assertEquals(true, isMatch("aa", "*"));
        Assert.assertEquals(true, isMatch("adceb", "*a*b"));

        Assert.assertEquals(false, isMatch("acdcb", "a*c?b"));
        Assert.assertEquals(false, isMatch("cb", "?a"));
        Assert.assertEquals(true, isMatch("ho", "ho**"));
        Assert.assertEquals(false, isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
    }

    @Test
    public void t1() {

       for(int i=0;i<10000;i++) {
           List<Integer> weeklyFreeRoleIds = new ArrayList<>();
           weeklyFreeRoleIds.add(1);
           weeklyFreeRoleIds.add(2);
           weeklyFreeRoleIds.add(3);
           weeklyFreeRoleIds.add(4);
           List<Integer> weeklyVipFreeRoleIds = new ArrayList<>();
           weeklyVipFreeRoleIds.add(11);
           weeklyVipFreeRoleIds.add(12);
           Collections.shuffle(weeklyFreeRoleIds);
           Collections.shuffle(weeklyVipFreeRoleIds);
           System.out.println("free: "+ weeklyFreeRoleIds.get(0) +"   vip: "+weeklyVipFreeRoleIds.get(0));

       }
    }
}
