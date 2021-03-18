package com.wy.action.algorithm.backtrack;

import java.util.ArrayList;
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
        dfs(0, s, 0, arr, result);
        return result;
    }

    /**
     *
     * @param step {0,1,2,3}
     * @param s
     * @param index 字符串的索引
     * @param arr 存放分解结果的
     * @param result
     */
    private void dfs(int step, String s, int index, String[] arr, List<String> result) {
        // 判断字符有剩余的情况
        if ((4-step)*3 < s.length() - index) {
            return;
        }
        // 结束的条件
        if (index == s.length()) {
            if (step < 4) {
                return;
            }
            StringBuilder sb  = new StringBuilder();
            sb.append(arr[0]).append(".");
            sb.append(arr[1]).append(".");
            sb.append(arr[2]).append(".");
            sb.append(arr[3]);
            result.add(sb.toString());
            return;
        }

        // 向后找3个判断是否合法，然后回溯
        for(int i=1; i<=3; i++) {
            if (index+i> s.length()) break;
            String temp = s.substring(index,index+i);
            // 排除首字符为0的情况
            if (temp.length() >1 && temp.charAt(0) =='0') {
                break;
            }
            if (Integer.parseInt(temp)>255) {
                continue;
            }
            arr[step] = temp;
            dfs(step+1, s, index+i, arr, result);
        }
    }
}
