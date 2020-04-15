package com.wy.action.designpattern.backup;

/**
 * @Author wangyong
 * @Date 2020-04-15
 */
public class Snapshot {
    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}