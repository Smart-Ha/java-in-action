package com.wy.action.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author wangyong
 * @Date 2020-06-02
 */
public class TreeNode {
   public int val;
   public TreeNode left;
   public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 广度优先构造二叉树
     * @param list
     * @return
     */
    public static TreeNode bfsBuild(List<Integer> list) {
        TreeNode head = new TreeNode(list.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int index = 1;
        while (!queue.isEmpty() && index < list.size()) {
            TreeNode node =  queue.poll();
            Integer value = list.get(index++);
            if ( value != null) {
                TreeNode left = new TreeNode(value);
                node.left = left;
                queue.add(left);
            }
            if (index >= list.size()) {
                break;
            }
            value = list.get(index++);
            if (value != null) {
                TreeNode right = new TreeNode(value);
                node.right = right;
                queue.add(right);
            }
        }
        return head;
    }

    public void print() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            TreeNode node =  queue.poll();

            if ( node.left != null) {
                queue.add(node.left);
            }
            if ( node.right != null) {
                queue.add(node.right);
            }
            System.out.printf(node.val+",");
        }
    }

}
