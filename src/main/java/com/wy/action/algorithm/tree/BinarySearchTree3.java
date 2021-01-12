package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;

/**
 * 1000 往上的题
 */
public class BinarySearchTree3 {

    /**
     * 前序遍历构造二叉搜索树
     * https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
     */
    int bstFromIdx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPre(preorder,0, 1000000000);
    }

    private TreeNode bstFromPre(int[] preorder,int start, int end) {
        if (bstFromIdx >= preorder.length ||
                preorder[bstFromIdx] < start || preorder[bstFromIdx] > end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[bstFromIdx++]);

        root.left = bstFromPre(preorder, start, root.val);
        root.right = bstFromPre(preorder, root.val, end);
        return root;

    }
}
