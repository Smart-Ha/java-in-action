package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Test;

import java.util.*;

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

    /**
     * 给定一个二叉树，返回其节点值的级别顺序遍历。 （即，从左到右，逐级）。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> one = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null ) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
                one.add(treeNode.val);
            }

            result.add(one);
        }

        return result;
    }

    @Test
    public void levelOrderTest() {
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(levelOrder(treeNode));
        treeNode = TreeNode.bfsBuild(Arrays.asList(1,2,null,3,null,4,null,5));
        System.out.println(levelOrder(treeNode));
    }


}
