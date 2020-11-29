package com.wy.action.algorithm.dp;

import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-06-06
 */
public class InterleaveString {

    /**
     * 判断s3是否由s1和s2 交叉组合而成
     * https://leetcode.com/problems/interleaving-string/
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int index=0;
        for(int i=0;i< s1.length(); i++) {
            for(int j=index; j<s3.length(); j++) {
                if (s1.charAt(i) == s3.charAt(j)) {
                    s3 = s3.substring(0,j) +s3.substring(j+1);
                     break;
                } else {
                    index++;
                }
            }
        }
        System.out.println(s3);
        return s2.equals(s3);
    }

    @Test
    public void  test (){
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
