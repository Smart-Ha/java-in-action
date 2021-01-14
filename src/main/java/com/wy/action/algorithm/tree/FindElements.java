package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class FindElements {

    private TreeNode transfer = null;
    private Set<Integer> set = null;
    public FindElements(TreeNode root) {
        transfer = null;
        set = new HashSet<>();
        if (root == null) {
            return;
        }
        root.val = 0;
        set.add(root.val);
        transfer = root;
        element(root);
    }

    private void element(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left!= null) {
            root.left.val = 2 * root.val+1;
            set.add(root.left.val);
            element(root.left);
        }

        if (root.right != null) {
            root.right.val = 2 * root.val+2;
            set.add(root.right.val);
            element(root.right);
        }
    }

    public boolean find(int target) {
        return set.contains(target);
    }
}
