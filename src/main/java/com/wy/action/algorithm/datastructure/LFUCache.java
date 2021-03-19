package com.wy.action.algorithm.datastructure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author wangyong
 * @Date 2021-03-16
 */
public class LFUCache {
    /**
     * 最近最少使用淘汰算法
     */
    /**
     * key-value
     */
    Map<Integer, Integer> key2Val;

    /**
     * key - freq 得到key的频率
     */
    Map<Integer, Integer> key2Freq;

    /**
     * freq- keys
     */

    Map<Integer, LinkedHashSet<Integer>> freq2Keys;

    private int capacity;
    /**
     * 最小频率
     */
    private  int minFreq;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        key2Val = new HashMap<>(capacity);
        key2Freq = new HashMap<>(capacity);
        freq2Keys = new HashMap<>(capacity);
    }

    public void put(int key, int val) {
        // 存在元素，增加频率
        if (key2Val.containsKey(key)) {
            key2Val.put(key, val);
            // 增加频率
            incrFreq(key);
            return;
        }
        // 超过当前容量，移除最小频率的key
        if (key2Val.size() >= capacity) {
            removeMinFreqKey();
        }

        key2Val.put(key, val);
        key2Freq.put(key, 1);
        freq2Keys.putIfAbsent(1, new LinkedHashSet<>());
        freq2Keys.get(1).add(key);
        minFreq = 1;

    }

    /**
     * 移除掉最小频率的
     */
    private void removeMinFreqKey() {
        LinkedHashSet<Integer> hashSet = freq2Keys.get(minFreq);
        int key = hashSet.iterator().next();

        hashSet.remove(key);
        if (hashSet.isEmpty()) {
            freq2Keys.remove(minFreq);
        }
        key2Val.remove(key);
        key2Freq.remove(key);
    }

    private void incrFreq(int key) {
        int freq = key2Freq.get(key);
        key2Freq.put(key, freq+1);
        freq2Keys.putIfAbsent(freq+1, new LinkedHashSet<>());
        freq2Keys.get(freq+1).add(key);
        freq2Keys.get(freq).remove(key);
        if (freq2Keys.get(freq).isEmpty()) {
            freq2Keys.remove(freq);
            if (minFreq == freq) {
                minFreq ++;
            }
        }
    }

    public int get(int key) {
        if (!key2Val.containsKey(key)) {
            return -1;
        }
        incrFreq(key);
        return key2Val.get(key);
    }
}
