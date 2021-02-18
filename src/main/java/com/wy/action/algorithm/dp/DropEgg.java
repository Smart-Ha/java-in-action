package com.wy.action.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyong
 * @Date 2021-02-10
 */
public class DropEgg {

    /**
     * 高楼人鸡蛋
     * @param K 鸡蛋数
     * @param N 路层数
     * @return
     */
    Map<String, Integer> status = new HashMap<>();
    public int superEggDrop(int K, int N) {
        //只剩下一个鸡蛋，有几层楼就从下往上试
        if (K == 1) {
            return N;
        }
        // 第0层，不需要扔鸡蛋
        if (N == 0) {
            return 0;
        }

        String key = K+"-"+N;
        Integer value = status.get(key);
        if (value != null) {
            return value;
        }
        int res = Integer.MAX_VALUE;
        // 1-N 层，从哪层扔能获取到最少的次数
        for(int i=1; i< N+1; i++) {
            res = Math.min(res, Math.max(
                    // 鸡蛋碎了
                    superEggDrop(K-1, i-1),
                    // 鸡蛋没碎, 还剩N-i层楼
                    superEggDrop(K, N-i)

            )+1);
        }
        status.put(key, res);
        return res;
    }

}
