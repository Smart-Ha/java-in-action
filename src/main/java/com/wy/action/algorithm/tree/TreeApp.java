package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
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


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        boolean asc = true;
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
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(zigzagLevelOrder(treeNode));
        treeNode = TreeNode.bfsBuild(Arrays.asList(1,2,null,3,null,4,null,5));
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

            for(int i=0; i< size; i++) {
                TreeNode treeNode = queue.poll();
                if(treeNode.left != null) {
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
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3,9,20,null,null,15,7));
        Assert.assertEquals(3, maxDepthByBfs(treeNode));
        treeNode = TreeNode.bfsBuild(Arrays.asList(1,2,null,3,null,4,null,5));
        Assert.assertEquals(5, maxDepthByBfs(treeNode));
    }

    /**
     * 打印二叉树每层的节点， 从底部往上打
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
        for (int i=0; i< size; i++) {
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
        TreeNode root = TreeNode.bfsBuild(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(levelOrderBottom(root));
    }

    /**
     * 求二叉树每一层的平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double total = 0.0d;
            for (int i=0; i<size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null ) {
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

    /**
     * 输出先序遍历的结果
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversalRecursion(root, result);
        return result;
    }

    private void preorderTraversalRecursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderTraversalRecursion(root.left, result);
        preorderTraversalRecursion(root.right, result);
    }

    /**
     * 给定一个数，判断二叉树是否存在一个路径是的路径上的值相加等于这个数
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null &&  sum- root.val == 0) {
            return true;
        }
        int left = sum - root.val;
        return hasPathSum(root.left, left) || hasPathSum(root.right, left);
    }

    /**
     * 给定一个数，判断二叉树是否存在一个路径是的路径上的值相加等于这个数,将所有路径返回
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        hasPathSumRecursion(root, sum, result, new LinkedList<Integer>());
        return result;
    }

    private boolean hasPathSumRecursion(TreeNode root, int sum, List<List<Integer>> result,
                                        LinkedList<Integer> list) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null &&  sum- root.val == 0) {
            // 重新拷贝一份，不影响后续的list操作
            List<Integer> one = new ArrayList<>(list);
            one.add(root.val);
            result.add(one);
            return true;
        }
        int left = sum - root.val;
        list.add(root.val);
        hasPathSumRecursion(root.left, left, result, list);
        hasPathSumRecursion(root.right, left, result, list);
        // 移除上次操作
        list.removeLast();
        return true;
    }

    TreeNode current = null;
    /**
     * 将一个二叉树平铺为链表，先序遍历
     */
    public void flatten(TreeNode root) {
        flattenTraversal(root,null);
    }


    private void flattenTraversal(TreeNode root, TreeNode father) {
        if (root == null) {
            current = father;
            return;
        }
        if (father != null) {
            father.right = root;
            father.left = null;
        }
        TreeNode temp = root.right;
        flattenTraversal(root.left, root);
        flattenTraversal(temp, current);
    }

    TreeNode pre = null;
    /**
     * key: 记录最后一个位置，然后从右往左遍历，将当前节点的右指针指向上一个节点
     * @param root
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten2(root.right);
        flatten2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }


}
