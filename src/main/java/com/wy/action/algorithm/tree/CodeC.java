package com.wy.action.algorithm.tree;


import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeC {
    /**
     * 序列化和反序列化一个树
     */

    private static String SEP = ",";
    private static String NULL = "#";

    /**
     * Encodes a tree to a single string.
     */

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
           return NULL;
        }
        serT(root, sb);
        return sb.substring(0, sb.length()-1);
    }

    /**
     * 先序遍历树
     * @param root
     * @param tree
     */
    private void serT(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(",");
            return;
        }
        sb.append(root.val).append(",");
        serT(root.left, sb);
        serT(root.right, sb);
    }
    int index = 0;

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode root = new TreeNode(1);
        String[] strs = data.split(SEP);
        index = 0;
        return serD(strs);
    }

    private TreeNode serD(String[] strs) {
        String one = strs[index++];
        if (NULL.equals(one)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(one));
        node.left = serD(strs);
        node.right = serD(strs);
        return node;
    }


    @Test
    public void serializeTest(){
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(1,2,3,4,null, 5));
        String ser = serialize(treeNode);
        System.out.printf(ser);
        TreeNode treeNode2 = deserialize(ser);
        Assert.assertEquals(true, new TreeApp().isSameTree(treeNode, treeNode2));
    }
}
