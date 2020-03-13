package com.wy.action.algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author wangyong
 * @Date 2020-03-13
 */
public class MinDistance {


    public int minDistance(String word1, String word2) {
       int m=word1.length(), n = word2.length();
       int[][] arr = new int[m+1][n+1];
       for(int i=0;i<=m ;i++) {
           arr[i][0] = i;
       }
       for(int i=0; i<=n;i++) {
           arr[0][i] = i;
       }
       for(int i=0;i<m; i++) {
           for(int j=0; j<n; j++) {

               if (word1.charAt(i) == word2.charAt(j)) {
                   arr[i+1][j+1] = arr[i][j];
               } else {
                   int insert = arr[i][j];
                   int remove = arr[i][j+1];
                   int replace = arr[i+1][j];
                   arr[i+1][j+1] = Math.min(insert, Math.min(remove, replace)) +1;
               }

           }

       }

       return arr[m][n];

    }

    @Test
    public void test() {
        Assert.assertEquals(3, minDistance("horse", "ros"));

    }
}
