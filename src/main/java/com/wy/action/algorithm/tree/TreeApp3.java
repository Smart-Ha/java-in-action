package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TreeApp3 {


    /**
     * 求最长的之字形树
     * https://leetcode-cn.com/problems/longest-zigzag-path-in-a-binary-tree/
     * @param root
     * @return
     */
    int longestZig = 0;
    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestZigZagDFS(root,true,0);
        longestZigZagDFS(root,false,0);
        return longestZig;
    }
    private void longestZigZagDFS(TreeNode root, boolean goLeft, int dep) {
        longestZig = Math.max(longestZig, dep);
        if (goLeft) {
            if (root.left != null) {
                longestZigZagDFS(root.left, false, dep+1);
            }
            if (root.right != null) {
                longestZigZagDFS(root.right, true, 1);
            }
        } else {
            if (root.right != null) {
                longestZigZagDFS(root.right, true, dep+1);
            }
            if (root.left != null) {
                longestZigZagDFS(root.left, false, 1);
            }
        }
    }

    @Test
    public void longestZigZagTest() {
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(1,1,1,null,1,null,null,1,1,null,1));
        Assert.assertEquals(4, longestZigZag(treeNode));
    }

    /**
     * 找出克隆二叉树中的相同节点
     * https://leetcode-cn.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }
}
