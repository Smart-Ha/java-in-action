package com.wy.action.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author wangyong
 * @Date 2020-03-20
 */
public class BinaryApp {

    /**
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for(int i=0; i<n;i++) {
            for(int j=result.size()-1;j>=0; j--) {
                result.add(result.get(j) | 1<<i);
            }
        }
        return result;
    }

    @Test
    public void grayCode() {
        System.out.println(grayCode(0));
        System.out.println(grayCode(2));
        System.out.println(grayCode(1));
        System.out.println(grayCode(3));
    }
}
