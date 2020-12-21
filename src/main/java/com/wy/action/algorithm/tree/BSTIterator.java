package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangyong
 * @Date 2020-12-21
 */
public class BSTIterator {

    List<Integer> data = new ArrayList<>();
    int size = 0;
    int pointer = 0;
    public BSTIterator(TreeNode root) {
       inOrder(root);
       size = data.size();
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        data.add(root.val);
        inOrder(root.right);
    }

    public int next() {
        return data.get(pointer++);
    }

    public boolean hasNext() {
        return pointer < size;
    }

}
