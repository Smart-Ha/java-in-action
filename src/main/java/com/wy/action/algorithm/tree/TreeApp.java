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
        int result = 0;
       // 先计算左子树
        if (root.left != null) {
            if (root.left.left == null && root.left.right ==null) {
                result += root.left.val;
            } else {
                result += sumOfLeftLeaves(root.left);
            }
        }
        return result + sumOfLeftLeaves(root.right);
    }

    /**
     * 找出最频繁的子树和
     * @param root
     * @return
     */
    int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {

        Map<Integer, Integer> counter = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        treeSumTraverse(root, list, counter);
        int[] result = new int[list.size()];
        for (int i=0; i< list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }

    private int treeSumTraverse(TreeNode root, List<Integer> list, Map<Integer, Integer> counter) {
        if (root == null) {
            return 0;
        }
        int leftSum = treeSumTraverse(root.left, list, counter);
        int rightSum = treeSumTraverse(root.right, list, counter);
        int sum = root.val + leftSum +rightSum;
        int count = counter.getOrDefault(sum, 0);
        count++;
        if (count > max) {
            list.clear();
            list.add(sum);
            max = count;
        } else if (count == max) {
            list.add(sum);
        }
        counter.put(sum, count);
        return sum;
    }

    /**
     * 找出最底层的最左边的节点值
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftTraverse(root, 0, new int[]{0,0});
    }

    private int findBottomLeftTraverse(TreeNode root, int dep, int[] ints) {
        if (root == null) {
            return 0;
        }
        if (dep > ints[0]) {
            ints[0] = dep;
            ints[1] = root.val;
        }
        findBottomLeftTraverse(root.left, dep+1, ints);
        findBottomLeftTraverse(root.right, dep+1, ints);
        return ints[1];
    }

    /**
     * 求最长路径的直径 求节点两颗子树的相加最大高度
     * @param root
     * @return
     */
    int dep = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeTraverse(root);
        return dep;
    }
    public int diameterOfBinaryTreeTraverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTreeTraverse(root.left);
        int right = diameterOfBinaryTreeTraverse(root.right);;
        dep =  Math.max(left+right, dep);
        return Math.max(left, right)+1;
    }


    public int maxDepth(com.wy.action.algorithm.tree.Node root) {

        if (root == null) {
            return 0;
        }
        int dep = 0;
        for (com.wy.action.algorithm.tree.Node node: root.children) {
            dep = Math.max(maxDepth(node), dep);
        }
        return dep+1;
    }

    /**
     * https://leetcode.com/problems/binary-tree-tilt/
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        findTiltTraverse(root);
        return sum;
    }


    private int findTiltTraverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = findTiltTraverse(root.left);
        int right =  findTiltTraverse(root.right);
        sum += Math.abs( left- right);
        // 返回的是当前的子树和自身之和
        return left+ right+ root.val;
    }

    /**
     * 判断t是否为s的子树
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (s.val == t.val && isSameTree(s, t)) {
            return true;
        }

        if (isSubtree(s.left, t) || isSubtree(s.right, t)) {
            return true;
        }

        return false;

    }

    /**
     * n叉树的先序遍历
     */
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorder(com.wy.action.algorithm.tree.Node root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        if (root.children != null) {
            for (com.wy.action.algorithm.tree.Node node : root.children) {
                preorder(node);
            }
        }
        return result;
    }

    /**
     * n叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorder(com.wy.action.algorithm.tree.Node root) {
        if (root == null) {
            return result;
        }

        if (root.children != null) {
            for (com.wy.action.algorithm.tree.Node node : root.children) {
                postorder(node);
            }
        }
        result.add(root.val);
        return result;
    }

    /**
     * 将二叉树的子树用() 包括起来，返回字符串
     * [1,2,3,4] -> "1(2(4))(3)"
     * [1,2,3,null,4] "1(2()(4))(3)"
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        tree2strTraverse(t, sb);
        return sb.toString();
    }

    private void tree2strTraverse(TreeNode t, StringBuilder sb) {
        if (t == null) {
            return;
        }
        sb.append(t.val);
        if (t.left == null && t.right == null) {
            return;
        }
        sb.append("(");
        tree2strTraverse(t.left, sb);
        sb.append(")");

        if (t.right != null) {
            sb.append("(");
            tree2strTraverse(t.right, sb);
            sb.append(")");
        }


    }

    @Test
    public void tree2strTest() {
        TreeNode root = TreeNode.bfsBuild(Arrays.asList(1,2,3,4));
        Assert.assertEquals(tree2str(root), "1(2(4))(3)");
        root = TreeNode.bfsBuild(Arrays.asList(1,2,3,null,4));
        Assert.assertEquals(tree2str(root), "1(2()(4))(3)");

    }

    /**
     * 合并两棵树 ，相同位置的节点 值相加
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        // 以t1为结果
        if (t1 != null && t2 != null) {
            t1.val += t2.val;
        } else if (t1 == null) {
            TreeNode temp = t1;
            t1 = t2;
            t2 = temp;
        }

        t1.left = mergeTrees(t1.left, t2 == null ? null: t2.left);
        t1.right = mergeTrees(t1.right, t2 == null ? null: t2.right);

        return t1;
    }


    /**
     * 添加一行
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null || d <0) {
            return root;
        }
        return addOneRowTraverse(root, v,d, 1, true);
    }

    private TreeNode addOneRowTraverse(TreeNode root, int v, int d, int i, boolean fromLeft) {
        if (d == i) {
            TreeNode one = new TreeNode(v);
            if (fromLeft) {
                one.left = root;
            } else {
                one.right = root;
            }
            return one;
        }
        if (root == null) {
            return null;
        }
        root.left = addOneRowTraverse(root.left, v,d, i+1, true);
        root.right = addOneRowTraverse(root.right, v,d, i+1, false);
        return root;
    }

}
