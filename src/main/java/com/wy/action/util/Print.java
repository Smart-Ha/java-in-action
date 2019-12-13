package com.wy.action.util;

/**
 * @Author wangyong
 * @Date 2019-12-12
 */
public class Print {

    public static void print(int[][] arr) {
        for(int  i=0;i<arr.length;i++) {
            System.out.print("[");
            for (int j=0; j<arr[i].length;j++) {
                System.out.print(arr[i][j]);
                System.out.print(",");
            }

            System.out.println("]");
        }
        System.out.println();
    }
}
