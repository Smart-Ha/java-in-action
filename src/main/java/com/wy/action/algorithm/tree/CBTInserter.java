package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;

import java.util.*;

class CBTInserter {

    private List<TreeNode> index2Node = new ArrayList<>(1000);
    public CBTInserter(TreeNode root) {
        index2Node.add(null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                index2Node.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    public int insert(int v) {
        int counter = index2Node.size();
        TreeNode parent = index2Node.get(counter/2);
        TreeNode node = new TreeNode(v);
        if (counter % 2 == 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        index2Node.add(node);
        return parent.val;
    }

    public TreeNode get_root() {
        return index2Node.get(1);
    }
}
