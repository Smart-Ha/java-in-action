package com.wy.action.algorithm.tree;

import com.wy.action.entity.ListNode;
import com.wy.action.entity.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树
 * @Author wangyong
 * @Date 2020-12-19
 */
public class BinarySearchTree {


    /**
     * 给定一个排序数据，构建一个高度均衡（任意节点的左右字数高度差不大于1）的二叉查找树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return sortedArrayToBSTRecursion(0, nums.length-1, nums);
    }

    private TreeNode sortedArrayToBSTRecursion(int start, int end, int[] nums) {
        if (start > end) {
            return null;
        }
        int mid = start + (end- start)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTRecursion(start, mid-1, nums);
        node.right = sortedArrayToBSTRecursion(mid+1, end, nums);
        return node;

    }

    /**
     * 给一个有序链表，构建一个高度均衡（任意节点的左右字数高度差不大于1）的二叉查找树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortedListToBSTRecursion(head, null);
    }

    private TreeNode sortedListToBSTRecursion(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }
        ListNode fast = start;
        ListNode slow = start;
        while (fast!= end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBSTRecursion(start, slow);
        node.right = sortedListToBSTRecursion(slow.next, end);
        return node;
    }


    /**
     * 给一个数n， 返回1-n 构成的所有二叉查找树
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildTree(1,n);
    }

    private List<TreeNode> buildTree(int start, int end) {

        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }



        for (int i=start; i<=end; i++) {
            List<TreeNode> left = buildTree(start, i-1);
            List<TreeNode> right = buildTree(i+1, end);

            for (TreeNode lNode : left) {
                for (TreeNode rNode: right) {
                    TreeNode node = new TreeNode(i);
                    node.left = lNode;
                    node.right = rNode;
                    list.add(node);
                }
            }
        }
        return list;
    }

    @Test
    public void generateTreesTest() {
        List<TreeNode> list = generateTrees(3);
        System.out.println(1);
    }

    int kth = 0;
    int kthValue = 0;
    /**
     * 找出二叉查找树冲第k小的数 中序遍历
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        kthInOrder(root,k);
        return kthValue;
    }

    private void kthInOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthInOrder(root.left, k);
        kth++;
        if (kth == k) {
            kthValue = root.val;
            return;
        }
        kthInOrder(root.right, k);
    }


    /**
     * 最近的公共父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 两差相乘大于0 说明在同一边
       while ((root.val-p.val) * (root.val - q.val) > 0 ) {

           root = root.val > p.val ? root.left : root.right;
       }
       return root;
    }


}
