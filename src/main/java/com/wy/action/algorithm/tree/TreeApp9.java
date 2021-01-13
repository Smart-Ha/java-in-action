package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
     * 先序遍历还原二叉树
     * https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
     * 输入："1-2--3--4-5--6--7"
     * 输出：[1,2,5,3,4,6,7]
     * @param S
     * @return
     */
    int idx = 0;
    public TreeNode recoverFromPreorder(String S) {
        return recoverFromPreorderTra(S,0);
    }

    private TreeNode recoverFromPreorderTra(String s, int dep) {
        if (idx >= s.length()) {
            return null;
        }

        int i=idx;
        int matchDep = 0;
        for(; i<s.length(); i++) {
            if (s.charAt(i) =='-') {
                matchDep++;
            } else {
                break;
            }
        }
        if (matchDep != dep) {
            return null;
        }
        idx = i;
        for(; i<s.length(); i++) {
            if (s.charAt(i) =='-') {
                break;
            }
        }

        TreeNode node = new TreeNode(Integer.parseInt(s.substring(idx, i)));
        idx = i;
        node.left = recoverFromPreorderTra(s,  dep+1);
        node.right = recoverFromPreorderTra(s, dep+1);
        return node;
    }
    @Test
    public void recoverFromPreorderTest() {

        TreeNode treeNode = recoverFromPreorder("1-2--3--4-5--6--7");

    }

}
