package com.wy.action.designpattern.backup;

import java.util.Stack;

/**
 * @Author wangyong
 * @Date 2020-04-15
 */
public class SnapshotHolder {
    private Stack<Snapshot> snapshots = new Stack<>();

    public Snapshot popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(Snapshot snapshot) {
        snapshots.push(snapshot);
    }
}