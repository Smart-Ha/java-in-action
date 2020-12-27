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
     *
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
     * 输出先序遍历的结果
     *
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
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }
        int left = sum - root.val;
        return hasPathSum(root.left, left) || hasPathSum(root.right, left);
    }

    /**
     * 给定一个数，判断二叉树是否存在一个路径是的路径上的值相加等于这个数,将所有路径返回
     *
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

        if (root.left == null && root.right == null && sum - root.val == 0) {
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
        flattenTraversal(root, null);
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
     *
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

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                pre = node;
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current != null) {
            Node node = current;
            // 先把一层的遍历完
            while (node != null) {
                if (node.left != null) {
                    node.left.next = node.right;
                }
                if (node.right != null && node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            // 遍历下一层
            current = current.left;

        }
        return root;
    }


    public Node connectII(Node root) {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current != null) {
            Node node = current;
            Node temp = null;
            // 先把一层的遍历完
            Node pre = null;
            while (node != null) {
                if (node.left != null) {
                    if (pre != null) {
                        pre.next = node.left;
                    } else {
                        temp = node.left;
                    }
                    pre = node.left;
                }
                if (node.right != null) {
                    if (pre != null) {
                        pre.next = node.right;
                    } else {
                        temp = node.right;
                    }
                    pre = node.right;
                }
                node = node.next;
            }
            // 遍历下一层
            if (temp != null) {
                current = temp;
            } else {
                current = current.left;
            }

        }
        return root;
    }

    /**
     * 求各个路径组成的数相加之和
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        sumNumbersRecursion(root, 0);
        return sum;
    }

    private void sumNumbersRecursion(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        i = i*10 +root.val;
        if (root.left == null && root.right == null) {
            sum += i;
            return;
        }
        sumNumbersRecursion(root.left, i);
        sumNumbersRecursion(root.right,i);

    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);

    }

    /**
     * 返回一棵树从右侧视角看的所有节点值
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> reuslt = new ArrayList<>();
        if (root == null) {
            return reuslt;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode last = null;
            for (int i=0; i< size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                last = node;
            }
            reuslt.add(last.val);
        }
        return reuslt;
    }

    public List<Integer> rightSideViewII(TreeNode root) {
        List<Integer> reuslt = new ArrayList<>();
        if (root == null) {
            return reuslt;
        }
        rightSide(root, reuslt, 0);
        return reuslt;
    }

    private void rightSide(TreeNode root, List<Integer> reuslt, int dep) {
        if (root == null) {
            return;
        }

        if (reuslt.size() == dep) {
            reuslt.add(root.val);
        }
        rightSide(root.right, reuslt, dep+1);
        rightSide(root.left, reuslt, dep+1);

    }


    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) +1;
    }

    /**
     * 交换左右子树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 打印路径  1->2->3
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        binaryTreePathsRecursion(root, "", result);
        return result;
    }

    public void binaryTreePathsRecursion(TreeNode root, String str, List<String> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(str+root.val);
            return;
        }
        String val = str + root.val+"->";
        if (root.left != null) {
            binaryTreePathsRecursion(root.left, val, result);
        }

        if (root.right != null) {
            binaryTreePathsRecursion(root.right, val, result);
        }
    }


    public String smallestFromLeaf(TreeNode root) {
        return dps(root, "");

    }

    private String dps(TreeNode root, String suffix) {
        if (root == null) {
            return suffix;
        }
        suffix = toString(root.val) + suffix;
        if (root.left == null && root.right == null) {
            return suffix;
        }
        if (root.left == null) {
            return dps(root.right, suffix);
        }
        if (root.right == null) {
            return dps(root.left, suffix);
        }

        String leftMin = dps(root.left, suffix);
        String rightMin = dps(root.right, suffix);

        return leftMin.compareTo(rightMin) <= 0 ? leftMin: rightMin;

    }


    private String toString(int val) {
        char c = (char) (val+'a');
        return String.valueOf(c);
    }

    @Test
    public void smallestFromLeafTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(4,0,1,1));
        String val = smallestFromLeaf(node);
        System.out.println(val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (q ==p) {
            return q;
        }
        LinkedList<TreeNode> pParents = new LinkedList<>();
        LinkedList<TreeNode> qParents = new LinkedList<>();
        findNode(root, p, pParents);
        findNode(root, q, qParents);
        TreeNode node = null;
        while (!pParents.isEmpty() && !qParents.isEmpty()) {
            TreeNode one = pParents.pollFirst();
            if (one != qParents.pollFirst()) {
                return node;
            }
            node = one;
        }
        return node;
    }

    /**
     * 找出两个节点的最近公共父节点
     * KEY： 递归调用，要不在左子树，要不在右子树，否则就是跟节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestorII(root.left, p,q);
        TreeNode right = lowestCommonAncestorII(root.right, p,q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    private boolean findNode(TreeNode root, TreeNode q,LinkedList list) {
        if (root == null) {
            return false;
        }
        if (root == q) {
            list.addFirst(root);
            return true;
        }

        if (findNode(root.left, q, list) || findNode(root.right, q, list)) {
            list.addFirst(root);
            return true;
        }
        return false;

    }

    @Test
    public void lowestCommonAncestorTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(3,5,1,6,2,0,8,null,null,7,4));
        TreeNode target = lowestCommonAncestor(node, node.left, node.left.right.right);
    }

//    /**
//     * 左子树之和
//     * @param root
//     * @return
//     */
//    public int sumOfLeftLeaves(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        if (root.left != null) {
//            return root.left.val + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
//        }
//        return sumOfLeftLeaves(root.right);
//    }

    /**
     * 左叶子节点之和
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumOfLeftLeavesRecursion(root.left, true) +
               sumOfLeftLeavesRecursion(root.right, false) ;
    }

    public int sumOfLeftLeavesRecursion(TreeNode root, boolean fromLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            if (fromLeft) {
                return root.val;
            }
            return 0;
        }
        return sumOfLeftLeavesRecursion(root.left, true) +
                sumOfLeftLeavesRecursion(root.right, false);
    }
}
