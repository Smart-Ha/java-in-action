package com.wy.action.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 贪心算法实例
 * @Author wangyong
 * @Date 2019-12-06
 */
public class GreedApp {

    /**
     * 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
     * @param number
     * @param k
     * @return
     */
    public List<Integer> getMaxByRemove(long number, int k) {
        /**
         * key: 让高位的数尽可能小，从高位开始，取一位数和下一位比较，如果高位比较大，则移除，负责右移比较下一位
         */
        String numStr = String.valueOf(number);
        List<Integer> result = new ArrayList<>();
        while (k>0) {
            for(int i=0; i<numStr.length()-1; i++) {
                if (numStr.charAt(i) > numStr.charAt(i+1)) {
                    result.add(numStr.charAt(i) - '0');
                    numStr = numStr.substring(0,i)+numStr.substring(i+1,  numStr.length());
                    k--;
                    break;
                }
            }
        }
        System.out.println(numStr);
        return result;
    }

    @Test
    public void getMaxByRemoveTest() {
        long number = 7084178234L;
        System.out.println(getMaxByRemove(number,2));
    }
}
