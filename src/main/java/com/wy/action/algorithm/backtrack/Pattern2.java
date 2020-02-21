package com.wy.action.algorithm.backtrack;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2019-12-28
 */
public class Pattern2 {

    private boolean match = false;

    /**
     * https://leetcode.com/problems/regular-expression-matching/
     * DESC: Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     *
     * key: 点判断匹配， * 匹配的是前一个或n个
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        match = false;
        if (".*".equals(p)) {
            return true;
        }
        matchR(s,0 ,p, 0);
        return match;
    }

    private void matchR(String s, int i, String p, int j) {
        if (match) {
            return;
        }
        if (i==s.length() || j == p.length()) {

        }

    }


    @Test
    public void test () {
        Assert.assertEquals(true, isMatch("a","ab*"));
        Assert.assertEquals( true,isMatch("aa","a******"));
        Assert.assertEquals(true, isMatch("aa","a*"));
        Assert.assertEquals(isMatch("a.",".*"), true);
        Assert.assertEquals( true, isMatch("aab","c*a*b"));
        Assert.assertEquals(isMatch("mississippi","mis*is*p*"), false);
        Assert.assertEquals(isMatch("aaa","a*a"), true);
        Assert.assertEquals( true, isMatch("aaa","ab*a*c*a"));

    }

}
