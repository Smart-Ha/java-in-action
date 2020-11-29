package com.wy.action.algorithm.dfs;

import com.wy.action.entity.TreeNode;

/**
 * @Author wangyong
 * @Date 2020-09-11
 */
public class isValidBST {


    /**
     * 判断是否为二叉搜索树
     *
     * [0,null,1]
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBSTR(root);
    }

    private boolean isValidBSTR(TreeNode root) {
        if (root == null) return true;
        if (root.left != null) {
            if (root.val > root.left.val) {
                if (root.right!= null) {
                    if (root.val< root.right.val) {
                        return isValidBST(root.left) && isValidBST(root.right);
                    }
                    return false;
                }
                return isValidBST(root.left);
            }
            return false;
        }
        if (root.right != null) {
            if (root.right.val > root.val) {
                isValidBSTR(root.right);
            }
            return false;
        }
        return true;
    }
}
