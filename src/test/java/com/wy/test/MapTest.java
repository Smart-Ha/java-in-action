package com.wy.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyong
 * @Date 2020-05-18
 */
public class MapTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(300, "300");
        map.put(1000, "1000");
        map.put(10000, "10000");
        System.out.println(map.get(1));
        System.out.println(map.get(300));
        System.out.println(map.get(new Integer(300)));
        System.out.println(map.get(1000));
        System.out.println(map.get(new Integer(1000)));
        System.out.println(map.get(10000));
        System.out.println(map.get(new Integer(10000)));
    }
}
