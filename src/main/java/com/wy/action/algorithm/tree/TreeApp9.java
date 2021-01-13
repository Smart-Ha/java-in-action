package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TreeApp9 {

    Integer dep1 = null;
    TreeNode parent1 = null;
    Boolean isCousin = false;
    public boolean isCousins(TreeNode root, int x, int y) {
        cousin(root, x, y,0, null);
        return isCousin;
    }

    private boolean cousin(TreeNode root, int x, int y, int dep, TreeNode parent) {
        if (root == null ) {
            return false;
        }
        if (root.val == x || root.val == y) {
            if (dep1 == null) {
                dep1 = dep;
                parent1 = parent;
            } else  {
                isCousin = dep1 == dep && parent != parent1;
                return true;
            }
        }
        return cousin(root.left, x, y,dep+1, root) ||
                cousin(root.right, x, y, dep+1, root);
    }

    @Test
    public void isCousionsTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(1,2,3,null,4,null,5));
        Assert.assertEquals(true, isCousins(node, 4,5));
    }

    /**
     * 最大二叉树
     * https://leetcode-cn.com/problems/maximum-binary-tree-ii/
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if( root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }

    /**
     * 1026. 节点与其祖先之间的最大差值
     * https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/
     * @param root
     * @return
     */
    int maxDiff = 0;
    public int maxAncestorDiff(TreeNode root) {
        maxDiff = 0;
        maxAncestorDiffTraverse(root, root.val,root.val);
        return maxDiff;
    }

    private void maxAncestorDiffTraverse(TreeNode root, int min, int max) {
        if (root == null) {
            maxDiff = Math.max(maxDiff, max-min);
            return;
        }
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);
        maxAncestorDiffTraverse(root.left, min, max);
        maxAncestorDiffTraverse(root.right, min, max);
    }
}
