package com.wy.action.algorithm.dp;

public class Nim {

    /**
     * 你作为先手能不能赢
     * 桌子上有一堆石头。
     * 你们轮流进行自己的回合，你作为先手。
     * 每一回合，轮到的人拿掉 1 - 3 块石头。
     * 拿掉最后一块石头的人就是获胜者。
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        if (n<4) {
            return true;
        }
        if (n == 4) {
            return false;
        }
        boolean[] res = new boolean[n+1];
        res[1] = res[2] =res[3] = true;
        for(int i=4; i<=n;i++) {
            res[i] = !(res[i-1] && res[i-2] && res[i-3]);
        }
        return res[n];
    }


}
