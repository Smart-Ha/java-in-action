package com.wy.action.concurrent.forkjoin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @Author wangyong
 * @Date 2019-06-15
 */
public class WordCount extends RecursiveTask<Map<String, Long>> {

    private String[] fr;
    private int start,end;

    public WordCount(String[] fr, int start, int end) {
        this.fr = fr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start<=1) {
            return calc(fr[start]);
        } else {
            int mid = (start + end) / 2;
            WordCount w1 = new WordCount(fr,start,mid);
            w1.fork();
            WordCount w2 = new WordCount(fr, mid, end);
            return merge(w2.compute(), w1.join());
        }
    }

    private Map<String, Long> merge(Map<String, Long> m1, Map<String, Long> m2) {
        Map<String, Long> result = new HashMap<>();
        result.putAll(m1);
        m2.forEach( (k, v) -> {
            long c = result.getOrDefault(k, 0L);
            result.put(k, c+v);
        });
        return result;
    }

    private Map<String, Long> calc(String s) {
        HashMap<String,Long > map = new HashMap<>();
        String[] words= s.split("\\s+");
        for (String word : words) {
            long count = map.getOrDefault(word, 0L);
            map.put(word,count+1);
        }
        return map;
    }
}
