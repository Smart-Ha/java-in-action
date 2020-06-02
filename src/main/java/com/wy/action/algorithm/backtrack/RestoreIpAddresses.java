package com.wy.action.algorithm.backtrack;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-06-02
 */
public class RestoreIpAddresses {

    /**
     * 返回合法ip地址
     * https://leetcode.com/problems/restore-ip-addresses/
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        if (n<4 || n>12) {
            return result;
        }
        String[] arr = new String[4];
        recursion(0, s, 0, arr, result);
        return result;
    }

    private void recursion(int step, String s, int count,String[] arr, List<String> result) {
        if (s.length()-count > (4-step)*3) {//有多余的
            return ;
        }

        if (count == s.length()) {
            if (step < 4) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<4;i++) {
                sb.append(arr[i]);
                if (i<3){
                    sb.append(".");
                }

            }
            result.add(sb.toString());
            return;
        }

        for(int i=1; i<=3; i++) {
            if (count+i> s.length()) break;
            String temp = s.substring(count, count+i);
            if (temp.length()>1 && temp.charAt(0) == '0') break;
            if (Integer.parseInt(temp)>255) {
                continue;
            }
            arr[step] = temp;
            recursion(step+1,s, count+i, arr, result);
        }
    }

    @Test
    public void  restoreIpAddressesTest() {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("010010"));

    }

}
