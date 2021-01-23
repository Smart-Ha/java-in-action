package com.wy.action.algorithm.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DPApp1 {

    public int minimumTotal(List<List<Integer>> triangle) {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i< triangle.size() ; i++) {
            List<Integer> list = triangle.get(i);
            for(int j=0; j< list.size(); j++) {
                Integer val1 = map.get((i-1)+"-"+j);
                Integer val2 =  map.get((i-1)+"-"+(j-1));
                int val;
                if (val1 != null && val2!= null) {
                    val = Math.min(val1,val2);
                } else if(val1 != null) {
                    val = val1;
                } else if (val2 != null) {
                    val = val2;
                } else {
                    val = 0;
                }

                map.put(i+"-"+j, val+ list.get(j));
            }
        }
        int min = Integer.MAX_VALUE;
        int i=triangle.size()-1;
        List<Integer> list = triangle.get(i);
        for(int j=0; j< list.size(); j++) {
            min = Math.min(min, map.get(i+"-"+j));
        }
        return min;

    }
}
