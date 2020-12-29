package com.wy.action.algorithm.tree;

import com.wy.action.entity.ListNode;
import com.wy.action.entity.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    /**
     * 删除二叉查找树的一个节点，并保证他还是一颗bst
     * KEY：目标节点删除之后，如果放置其左子树，那么将删除节点的右子树放在 target.left 的最右侧
     *                     如果放置其右子树，那么将删除节点的左子树放在 target.right 的最左侧
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if(key >root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            }

            // 将左子树放在替换节点的最小节点（最左）上
            TreeNode mostLeft = root.right;
            while (mostLeft.left != null) {
                mostLeft = mostLeft.left;
            }
            mostLeft.left = root.left;
            root = root.right;
        }
        return root;
    }


    @Test
    public void deleteNodeTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(5,3,6,2,4,null,7));
        TreeNode treeNode = deleteNode(node, 7);
        treeNode.print();
    }

    /**
     * 找出二叉查找树的节点值出现最多的数，此题中的bst是左子树<= root <= 右子树
     * KEY 中序遍历
     * @param root
     * @return
     */
    // 记录最大的count
    int max = 0;
    int count = 1;
    // 记录上一个的值
    Integer prev;
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int[] result = new int[list.size()];
        for (int i=0; i< list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null ) {
            return;
        }
        inOrder(root.left, list);
        if (prev != null) {
            if (prev == root.val) {
                count++;
            } else {
                // 出现了新的值
                count = 1;
            }
        }
        if (count > max) {
            list.clear();
            max = count;
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }

        prev = root.val;
        inOrder(root.right, list);
    }

    /**
     * bst的最小绝对差
     * @param root
     * @return
     */

    int min = Integer.MAX_VALUE;
    Integer pre = null;
    public int getMinimumDifference(TreeNode root) {
        minimumDiff(root);
        return min;
    }

    private void minimumDiff(TreeNode root) {
        if (root == null) {
            return;
        }
        minimumDiff(root.left);
        if (pre != null) {
            min = Math.min(min, Math.abs(root.val- pre));
        }
        pre = root.val;
        minimumDiff(root.right);
    }

    /**
     *  将bst变成 每个节点的值等于他加上所有比他大的值之和
     */
    TreeNode preNode = null;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        if (preNode != null) {
            root.val += preNode.val;
        }
        preNode = root;
        convertBST(root.left);
        return root;
    }
}
