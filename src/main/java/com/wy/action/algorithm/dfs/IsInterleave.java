package com.wy.action.algorithm.dfs;

import org.junit.Assert;
import org.junit.Test;

public class IsInterleave {


    /**
     * https://leetcode.com/problems/interleaving-string/
     * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
     *
     * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
     *
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
     *
     *  Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 + len2 != s3.length()) {
            return false;
        }

        if (s3.equals(s1+s2) || s3.equals(s2+s1)) {
            return true;
        }
        return  dps(s1,0,s2, 0, s3, 0, new boolean[len1+1][len2+1]);
    }

    private boolean dps(String s1, int l1, String s2, int l2, String s3, int l3, boolean[][] invalid) {
        if (invalid[l1][l2]) {
            return false;
        }
        if (l3 == s3.length()) {
            return true;
        }
        boolean result =  (l1< s1.length() && s3.charAt(l3) == s1.charAt(l1)) && dps(s1, l1+1, s2, l2, s3, l3+1, invalid) ||
                        (l2< s2.length() && s3.charAt(l3) == s2.charAt(l2)) && dps(s1, l1, s2, l2+1, s3, l3+1, invalid) ;
        if (!result) {
            invalid[l1][l2] = true;
        }
        return result;
    }

    @Test
    public void test1 () {
        Assert.assertEquals(true, isInterleave("aabcc","dbbca","aadbbcbcac" ));
    }

}
