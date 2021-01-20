package com.wy.action.algorithm.tree;

import com.wy.action.entity.ListNode;
import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TreeApp6 {

    /**
     * 判断一课二叉树是否为奇偶树
     * https://leetcode-cn.com/problems/even-odd-tree/
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null || root.val %2 ==0) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int dep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer pre = null;
            boolean flag = dep%2==0;
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (flag && node.val%2==0) {
                    return false;
                }
                if (!flag && node.val%2 ==1) {
                    return false;
                }
                if (pre != null) {
                    if (flag) {
                        if (node.val<=pre) {
                            return false;
                        }
                    } else if(node.val>=pre) {
                        return false;
                    }
                }
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                pre = node.val;

            }
            dep++;
        }
        return true;
    }

    /**
     * 先序遍历和中序遍历构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    int preOrderIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrderIndex = 0;
        Map<Integer, Integer> map= new HashMap<>();
        for (int i=0; i< inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeTraverse(preorder, inorder, map, 0, inorder.length-1);
    }

    private TreeNode buildTreeTraverse( int[] preorder, int[] inorder,
                                       Map<Integer, Integer> map, int start, int end) {
        if (start> end) {
            return null;
        }

        if (start == end) {
            preOrderIndex++;
            return new TreeNode(inorder[start]);
        }

        TreeNode root = new TreeNode(preorder[preOrderIndex]);

        int index = map.get(preorder[preOrderIndex]);
        preOrderIndex++;
        root.left = buildTreeTraverse( preorder, inorder, map, start, index-1);
        root.right = buildTreeTraverse( preorder, inorder, map, index+1, end);
        return root;
    }

    @Test
    public void buildTreeTest() {
        TreeNode node = buildTree(new int[]{1,2,3}, new int[] {1,2,3});
        TreeNode node1 = buildTree(new int[]{3,9,20,15,7}, new int[] {9,3,15,20,7});
    }

    /**
     * b是否为a的子树
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B== null || A == null) return false;
        return  subStructureDFS(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean subStructureDFS(TreeNode a, TreeNode b) {
        boolean aEmp = a == null;
        boolean bEmp = b == null;
        if (bEmp) {
            return true;
        }
        if (aEmp ^ bEmp) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return subStructureDFS(a.left, b.left) && subStructureDFS(a.right, b.right);
    }

    @Test
    public void Test() {
        System.out.println(true ^ false);
        System.out.println(true ^ true);
    }


    /**
     * 输出二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(root.left);
        return root;
    }

    /**
     * 是否为对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return symmetric(root.left, root.right);
    }

    private boolean symmetric(TreeNode left, TreeNode right) {
        boolean aEmp = left == null;
        boolean bEmp = right == null;
        if ( aEmp && bEmp) {
            return true;
        }
        if (aEmp ^ bEmp) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return symmetric(left.left, right.right) && symmetric(left.right, right.left);
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue =new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue =new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 给定一个值，找出二叉树中所有从上到下路径和等于这个值的个数
     * @param root
     * @param sum
     * @return
     */
    int pathSum = 0;
    public int pathSum(TreeNode root, int sum) {
        pathSumTraverse(root, sum, 0);
        return pathSum;
    }

    private void pathSumTraverse(TreeNode root, int sum, int i) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (i == sum) {
                pathSum ++;
            }
            return;
        }
        pathSumTraverse(root.left, sum, i+root.val);
        pathSumTraverse(root.right, sum, i+root.val);
    }


    @Test
    public void pathSumIITest() {
        Assert.assertEquals(3, pathSum(TreeNode.bfsBuild(Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,5,1)), 22));
    }
    int kthIndex = 0;
    int kth = 0;
    public int kthLargest(TreeNode root, int k) {
        kthLargestTraverse(root, k);
        return kth;
    }

    private void kthLargestTraverse(TreeNode root, int k) {
        if (root == null || kthIndex == k) {
            return ;
        }

        kthLargestTraverse(root.right, k);
        kthIndex++;
        if (kthIndex == k) {
            kth = root.val;
            return;
        }
        kthLargestTraverse(root.left, k);
    }

    /**
     * 是否为平衡二叉树
     * @param root
     * @return
     */
    boolean balance = true;
    public boolean isBalanced(TreeNode root) {
        isBalanced(root, 0);
        return balance;
    }

    private int isBalanced(TreeNode root, int dep) {
        if (root == null || !balance) {
            return dep;
        }
        int left = isBalanced(root.left, dep+1);
        int right = isBalanced(root.right, dep+1);
        if (Math.abs(left-right) >1) {
            balance = false;
        }
        return Math.max(left, right);
    }

    /**
     * 最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p,q);
        if (left == null) {
            return lowestCommonAncestor(root.right, p,q);
        }
        TreeNode right = lowestCommonAncestor(root.right, p,q);
        if (right == null) {
            return left;
        }
        return root;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];
        }
        List<ListNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode pre = null;
            ListNode root = null;
            for(int i=0;i<size; i++) {
                TreeNode node = queue.poll();
                if (pre == null) {
                    pre = new ListNode(node.val);
                    root = pre;
                } else {
                    pre.next = new ListNode(node.val);
                    pre = pre.next;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(root);
        }
        ListNode[] result = new ListNode[list.size()];
        list.toArray(result);
        return result;
    }

    /**
     * t2是否为t1的子树
     * @param t1
     * @param t2
     * @return
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        }
        return subTreeDFS(t1,t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean subTreeDFS(TreeNode t1, TreeNode t2) {
        boolean t1Emp = t1 == null;
        boolean t2Emp = t2 == null;
        if (t1Emp && t2Emp ) {
            return true;
        }
        if (t1Emp ^ t2Emp) {
            return false;
        }
        return t1.val == t2.val && subTreeDFS(t1.left, t2.left) && subTreeDFS(t1.right, t2.right);
    }

}
