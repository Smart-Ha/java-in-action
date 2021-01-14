package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

    /**
     * 二叉树寻路
     * https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/
     * @param label
     * @return
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<Integer>();
        pathInZigZag(result, label);
        result.add(label);
        return result;
    }

    private void pathInZigZag(List<Integer> result, int label) {
        if (label <=1 ){
            return;
        }
        int dep = (int)(Math.log(label) / Math.log(2));

        int parent = 3*(int) Math.pow(2, --dep) -1 - label/2;
        pathInZigZag(result, parent);
        result.add(parent);
    }


    @Test
    public void pathInZigZagTest() {
        List<Integer> list = pathInZigZagTree(14);
        System.out.println(list);
    }


    /**
     * 最大层内元素和
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        int max = Integer.MIN_VALUE;
        int result = 1;
        while(!queue.isEmpty()) {
            int sum = 0;
            int size = queue.size();
            for (int i=0; i<size; i++) {

                TreeNode node =queue.poll();
                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (sum > max) {
                max = sum;
                result = index;
            }
            index++;

        }
        return result;
    }
}
