package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;

/**
 * @Author wangyong
 * @Date 2020-12-16
 */
public class TreeApp {

    /**
     * 判断是否为同一颗树
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean b1 = p == null;
        boolean b2 = q == null;
        if (b1 && b2) {
            return true;
        }
        if (b1 || b2) {
            return false;
        }


        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 判断一个树是否为镜面对称
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricRec(root.left, root.right);
    }

    private boolean isSymmetricRec(TreeNode p, TreeNode q) {
        boolean b1 = p == null;
        boolean b2 = q == null;
        if (b1 && b2) {
            return true;
        }
        if (b1 || b2) {
            return false;
        }

        return p.val == q.val && isSymmetricRec(p.left, q.right) && isSymmetricRec(p.right, q.left);
    }

}
