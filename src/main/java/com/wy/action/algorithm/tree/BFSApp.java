package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 层次遍历
 */
public class BFSApp {

    /**
     * 小偷又为他的偷窃行为找到了一个新地方。这个区域只有一个入口，叫做“根”。除了根，每个房子都有并且只有一个父母房子。在参观之后，聪明的小偷意识到“这个地方所有的房子形成了一棵二叉树”。如果两个直接相连的房子在同一晚被闯入，它会自动联系警察。
     * 确定小偷今晚能在不报警的情况下抢劫的最大金额。
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
       return 0;
    }
    /**
     * 给定一个二叉树，返回其节点值的级别顺序遍历。 （即，从左到右，逐级）。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> one = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
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
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3, 9, 20, null, null, 15, 7));
        System.out.println(levelOrder(treeNode));
        treeNode = TreeNode.bfsBuild(Arrays.asList(1, 2, null, 3, null, 4, null, 5));
        System.out.println(levelOrder(treeNode));
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean asc = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> one = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
                one.add(treeNode.val);
            }
            if (asc) {
                asc = false;
            } else {
                asc = true;
                Collections.reverse(one);
            }
            result.add(one);
        }

        return result;
    }

    @Test
    public void zigzagLevelOrderTest() {
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3, 9, 20, null, null, 15, 7));
        System.out.println(zigzagLevelOrder(treeNode));
        treeNode = TreeNode.bfsBuild(Arrays.asList(1, 2, null, 3, null, 4, null, 5));
        System.out.println(zigzagLevelOrder(treeNode));
    }


    public int maxDepthByBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int dep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            dep++;
        }
        return dep;
    }

    @Test
    public void maxDepthByBfsTest() {
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3, 9, 20, null, null, 15, 7));
        Assert.assertEquals(3, maxDepthByBfs(treeNode));
        treeNode = TreeNode.bfsBuild(Arrays.asList(1, 2, null, 3, null, 4, null, 5));
        Assert.assertEquals(5, maxDepthByBfs(treeNode));
    }

    /**
     * 打印二叉树每层的节点， 从底部往上打
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        recursion(queue, result);
        return result;
    }

    private void recursion(Queue<TreeNode> queue, List<List<Integer>> result) {
        int size = queue.size();
        List<Integer> one = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            one.add(node.val);
        }
        if (!queue.isEmpty()) {
            recursion(queue, result);
        }
        if (!one.isEmpty()) {
            result.add(one);
        }
    }

    @Test
    public void levelOrderBottomTest() {
        TreeNode root = TreeNode.bfsBuild(Arrays.asList(3, 9, 20, null, null, 15, 7));
        System.out.println(levelOrderBottom(root));
    }

    /**
     * 求二叉树每一层的平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double total = 0.0d;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
                total += treeNode.val;
            }

            result.add(total / size);
        }

        return result;
    }

}
