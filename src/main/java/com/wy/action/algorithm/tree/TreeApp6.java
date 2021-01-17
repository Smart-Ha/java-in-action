package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeApp6 {

    /**
     * 判断一课二叉树是否为奇偶树
     * https://leetcode-cn.com/problems/even-odd-tree/
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null || root.val %2 ==0) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int dep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer pre = null;
            boolean flag = dep%2==0;
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (flag && node.val%2==0) {
                    return false;
                }
                if (!flag && node.val%2 ==1) {
                    return false;
                }
                if (pre != null) {
                    if (flag) {
                        if (node.val<=pre) {
                            return false;
                        }
                    } else if(node.val>=pre) {
                        return false;
                    }
                }
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                pre = node.val;

            }
            dep++;
        }
        return true;
    }
}
