package com.wy.action.algorithm.math;

public class ByteApp {


    public int reverseBits(int n) {
        int res = 0;
        int pow = 31;
        while (n!= 0) {
            int m = n&1;//最后一位
            res += m<<pow;
            n = n>>1;
            pow--;
        }
        return res;
    }
}
