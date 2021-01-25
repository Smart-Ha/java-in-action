package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TreeApp4 {


    /**
     * https://leetcode-cn.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
     * https://leetcode-cn.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
     * @param n
     * @param edges
     * @param hasApple
     * @return
     */
    int minTime = 0;
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, Node> map  = new HashMap<>(n);
        for(int i=0; i<edges.length; i++) {
            Node node = map.get(edges[i][0]);
            if (node == null) {
                node = new Node(edges[i][0]);
                map.put(edges[i][0], node);

            }
            if (node.children == null) {
                node.children = new ArrayList<>();
            }

            Node child = map.get(edges[i][1]);
            if (child == null) {
                child = new Node(edges[i][1]);
                map.put(edges[i][1], child);
            }
            node.children.add(child);
        }
        minTimeTraverse(map.get(0), hasApple);
        return minTime;
    }

    private boolean minTimeTraverse(Node treeNode, List<Boolean> hasApple) {
        if (treeNode == null ) {
            return false;
        }
        // 有苹果
        if(treeNode.children == null) {
            return hasApple.get(treeNode.val);
        }
        boolean childrenHas = false;
        for (Node node: treeNode.children) {
            if (minTimeTraverse(node, hasApple)) {
                minTime+=2;
                childrenHas = true;
            }
        }
        return childrenHas || hasApple.get(treeNode.val);
    }

    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    private int goodNodes(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        max = Math.max(max, root.val);
        return goodNodes(root.left, max) + goodNodes(root.right, max) +
                (max<=root.val ? 1:0) ;
    }

    @Test
    public void goodNodesTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(3,1,4,3,null,1,5));
        Assert.assertEquals(4, goodNodes(node));
    }

    /**
     * 二叉树中的伪回文路径
     * https://leetcode-cn.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
     * @param root
     * @return
     */
    int palindromicPath = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        pseudoPalindromic(root, map);
        return palindromicPath;
    }

    private void pseudoPalindromic(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        int count = map.getOrDefault(root.val, 0);
        map.put(root.val, ++count);
        if (root.left == null && root.right == null) {
            // 满足回文的条件是：最多只有一个数字出现频率是奇数次，其他都是偶数次
            int flag = 0;
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 == 1) {
                    flag++;
                }
                if (flag>2) {
                    break;
                }
            }
            if (flag<=1) {
                palindromicPath++;
            }
        }

        pseudoPalindromic(root.left, map);
        pseudoPalindromic(root.right, map);
        map.put(root.val, --count);
    }

    @Test
    public void pseudoPalindromicPathsTest() {

        TreeNode root = TreeNode.bfsBuild(Arrays.asList(2,1,1,1,3,null,null,null,null,null,1));
        Assert.assertEquals(1, pseudoPalindromicPaths(root));

    }

    /**
     * 二叉树中的最大路径和
     * @param root
     * @return
     */
    int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        int val = maxPathSumTraverse(root);
        maxPathSum = Math.max(val, maxPathSum);
        return maxPathSum;
    }

    private int maxPathSumTraverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumTraverse(root.left);
        int right = maxPathSumTraverse(root.right);
        int max = root.val;
        if (left > 0) {
            max += left;
        }

        if (right > 0) {
            max += right;
        }
        maxPathSum = Math.max(max,maxPathSum);
        if (left< 0 && right <0 ){
            return root.val;
        } else if (left> right) {
            return left + root.val;
        }
        return right + root.val;
    }
}
