package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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

    /**
     * 从根到叶的二进制数之和
     * https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/
     * @param root
     * @return
     */
    int sumRoot2Leaf = 0;
    public int sumRootToLeaf(TreeNode root) {
        sumRoot2Leaf = 0;
        sumRootToLeafTraverse(root,0);
        return sumRoot2Leaf;
    }

    private void sumRootToLeafTraverse(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        i *=2;
        if (root.left == null && root.right == null) {
            sumRoot2Leaf = sumRoot2Leaf+ i+root.val;
            return;
        }
        sumRootToLeafTraverse(root.left, i+root.val);
        sumRootToLeafTraverse(root.right, i+root.val);
    }

    @Test
    public void setSumRoot2LeafTest() {
        Assert.assertEquals(22,
                sumRootToLeaf(TreeNode.bfsBuild(Arrays.asList(1,0,1,0,1,0,1))));
    }
}
