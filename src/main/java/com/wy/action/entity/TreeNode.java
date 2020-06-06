package com.wy.action.entity;

/**
 * @Author wangyong
 * @Date 2020-06-02
 */
public class TreeNode {
   public int val;
   public TreeNode left;
   public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
