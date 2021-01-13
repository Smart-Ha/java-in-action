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
     * 1026. 节点与其祖先之间的最大差值
     * https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/
     * @param root
     * @return
     */
    int maxDiff = 0;
    public int maxAncestorDiff(TreeNode root) {
        maxDiff = 0;
        maxAncestorDiffTraverse(root, root.val,root.val);
        return maxDiff;
    }

    private void maxAncestorDiffTraverse(TreeNode root, int min, int max) {
        if (root == null) {
            maxDiff = Math.max(maxDiff, max-min);
            return;
        }
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);
        maxAncestorDiffTraverse(root.left, min, max);
        maxAncestorDiffTraverse(root.right, min, max);
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
     * 删点成林
     * https://leetcode-cn.com/problems/delete-nodes-and-return-forest/
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<to_delete.length; i++) {
            set.add(to_delete[i]);
        }
        root = delNodesTraverse(root, set, result);
        if (root != null) {
            result.add(root);
        }
        return result;
    }

    private TreeNode delNodesTraverse(TreeNode root, Set<Integer> set, List<TreeNode> result) {
        if (root == null) {
            return null;
        }
        if (set.contains(root.val)) {
            TreeNode left = delNodesTraverse(root.left, set, result);
            if (left != null)  {
                result.add(left);
            }
            TreeNode right = delNodesTraverse(root.right, set, result);
            if (right != null)  {
                result.add(right);
            }
            return null;
        }
        root.left = delNodesTraverse(root.left, set, result);
        root.right = delNodesTraverse(root.right, set, result);
        return root;
    }


    /**
     * 最深叶节点的最近公共祖先
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/
     * @param root
     * @return
     */
    int maxDep = 0;
    TreeNode lcaDeepest = null;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        lcaDeepestLeavesTraverse(root,1);
        return lcaDeepest;
    }

    private int lcaDeepestLeavesTraverse(TreeNode root, int i) {
        if (root == null) {
            return i;
        }
        i++;
        if (root.left == null && root.right == null) {
            if (maxDep < i) {
                maxDep = i;
                lcaDeepest =  root;
            }
            return i;
        }
        int left = lcaDeepestLeavesTraverse(root.left, i);
        int right = lcaDeepestLeavesTraverse(root.right, i);
        if (left ==right && left>= maxDep) {
            lcaDeepest = root;
        }
        return Math.max(left, right);
    }

    @Test
    public void LcaDeepestTest() {
        TreeNode root = TreeNode.bfsBuild(Arrays.asList(3,5,1,6,2,0,8,null,null,7,4));
        root = lcaDeepestLeaves(root);
        System.out.println(root);
    }
}
