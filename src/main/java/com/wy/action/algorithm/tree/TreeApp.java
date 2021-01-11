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

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();

        return result;
    }

    /**
     * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTargetTraverse(root, k, set);
    }


    private boolean findTargetTraverse(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTargetTraverse(root.left, k, set) || findTargetTraverse(root.right, k, set);
    }

    /**
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return maximumBinaryTree(nums,0, nums.length-1);
    }

    private TreeNode maximumBinaryTree(int[] nums, int start, int end) {
        if (end<0 ||  start>=nums.length || start>end) {
            return null;
        }
        int index = -1;
        int max = -1;
        for(int i=start; i<=end;i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = maximumBinaryTree(nums, start, index-1);
        root.right = maximumBinaryTree(nums, index+1, end);

        return root;
    }


    @Test
    public void constructMaximumBinaryTreeTest() {
        TreeNode root = constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
        root.print();
    }


    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {return 0;}
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer start = null;
            int end = 0;
            boolean hasValue = false;
            for (int i=1;i<=size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (start == null) {
                        start = i;
                    }
                    end = i;
                    queue.add(node.left);
                    queue.add(node.right);
                    hasValue = true;
                }
            }
            // 队列没有值了
            if (!hasValue) {
                break;
            }
            max = Math.max(max, end-start+1);
        }
        return max;
    }

    public int findSecondMinimumValue(TreeNode root) {
        return findSecondMinimumTraverse(root, root.val);
    }

    private int findSecondMinimumTraverse(TreeNode root, int rootVal) {
        if (root == null) {
            return -1;
        }
        if (root.val > rootVal) {
            return root.val;
        }
        int left  = findSecondMinimumTraverse(root.left, rootVal);
        int right = findSecondMinimumTraverse(root.right, rootVal);
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left == -1) {
            return right;
        }
        return left;
    }

    public int[] findRedundantConnection(int[][] edges) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int i=0; i< edges.length; i++) {
            Set<Integer> startSet = map.getOrDefault(edges[i][0], new HashSet<>());
            if (startSet.contains(edges[i][0])) {
                if (startSet.contains(edges[i][1])) {
                    return edges[i];
                }
            } else {
                startSet.add(edges[i][1]);
                map.put(edges[i][0],startSet);
            }
        }

        return new int[0];
    }

    private boolean leafSmr = true;
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Queue<Integer> list = new LinkedList<>();
        leafSimilarTraverse1(root1, list);
        leafSimilarTraverse2(root2, list);
        return (leafSmr && list.isEmpty()) ;
    }

    private void leafSimilarTraverse2(TreeNode root, Queue<Integer> list) {
        if (!leafSmr) {
            return;
        }
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if(list.isEmpty() || list.poll() != root.val) {
                leafSmr = false;
                return;
            }
        }
        leafSimilarTraverse2(root.left, list);
        leafSimilarTraverse2(root.right, list);
    }

    private void leafSimilarTraverse1(TreeNode root, Queue<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.offer(root.val);
            return;
        }
        leafSimilarTraverse1(root.left, list);
        leafSimilarTraverse1(root.right, list);
    }

    @Test
    public void leafSimilarTest() {
        TreeNode node1 = TreeNode.bfsBuild(Arrays.asList(3,5,1,6,2,9,8,null,null,7,4));
        TreeNode node2 = TreeNode.bfsBuild(Arrays.asList(3,5,1,6,7,4,2,null,null,null,null,null,null,9,8));
        Assert.assertEquals(true, leafSimilar(node1, node2));
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root.val == 1 ? root : null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null) {
            return root.val == 1 ? root : null;
        }
        return root;
    }

    TreeNode subTree = null;
    int deepest = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        subtreeTraverse(root,0);
        return subTree;
    }

    private int subtreeTraverse(TreeNode root, int dep) {
        if (root == null) {
            return dep;
        }
        int left = subtreeTraverse(root.left, dep+1);
        int right = subtreeTraverse(root.right, dep+1);
        if (left == right) {
            if (left>= deepest) {
                subTree = root;
                deepest = left;
            }
            return left;
        } else if (left >right) {
            if (left>deepest) {
                deepest = left;
                subTree = root.left;
            }
            return left;
        }
        if (right>deepest) {
            deepest = right;
            subTree = root.right;
        }
        return right;
    }

    @Test
    public void subtreeWithAllDeepestTest() {
        TreeNode treeNode = TreeNode.bfsBuild(Arrays.asList(3,5,1,6,2,0,8,null,null,7,4));
        treeNode = subtreeWithAllDeepest(treeNode);
    }

    /**
     * 返回n个节点的全二叉树， 全二叉树有2个或者0个子节点
     * @param N
     * @return
     */
    public List<TreeNode> allPossibleFBT(int N) {
        if (N%2 == 0) {
            return Collections.emptyList();
        }
        if (N == 1) {
            return Arrays.asList(new TreeNode(0));

        }


        List<TreeNode> list = new ArrayList<>();
        for (int i=1; i<N; i+=2) {
            List<TreeNode> leftList = allPossibleFBT(i);
            List<TreeNode> rightList = allPossibleFBT(N-i-1);

            for (TreeNode left: leftList) {

                for (TreeNode right: rightList) {
                    TreeNode node = new TreeNode(0);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }
        return list;
    }

    @Test
    public void allPossibleFBTTest() {
        List<TreeNode> list = allPossibleFBT(5);
        System.out.println(list);
    }

    /**
     * 给定一个值，找出二叉树中所有从上到下路径和等于这个值的个数
     * @param root
     * @param sum
     * @return
     */
    int pathSum = 0;
    public int pathSumII(TreeNode root, int sum) {
        return pathSum;
    }


    @Test
    public void pathSumIITest() {
        Assert.assertEquals(3, pathSumII(TreeNode.bfsBuild(Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,5,1)), 22));
    }


    /**
     * 可反转任意节点的左右子树，判断两颗树经过若干反转后是否相等
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        boolean emp1 = root1 == null;
        boolean emp2 = root2 == null;
        if (emp1 && emp2) {
            return true;
        } else if (emp1^emp2) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
         flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right);

    }

    /**
     * 判断是否为全二叉树
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Integer lastLevel = null;
        int index = 0;
        while (!queue.isEmpty()) {
            if (lastLevel != null&& lastLevel != (int)Math.pow(2, index++)) {
                return false;
            }
            int size = queue.size();
            boolean preNotFull = false;
            for(int i=0; i< size; i++) {
                TreeNode node = queue.poll();
                // 左子树为空，右子树不空
                boolean leftEmp = node.left == null;
                boolean rightEmp = node.right == null;
                if (leftEmp && !rightEmp) {
                    return false;
                }
                // 前一个同级节点子树不全，后面节点还有子树
                if (!leftEmp && preNotFull) {
                    return false;
                }
                if (!leftEmp) {
                    queue.add(node.left);
                } else {
                    preNotFull = true;
                }
                if (!rightEmp) {
                    queue.add(node.right);
                } else {
                    preNotFull = true;
                }
            }
            lastLevel = size;
        }
        return true;
    }

    public boolean isCompleteTreeII(TreeNode root){
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.peek() != null) {
            TreeNode treeNode = queue.poll();
            queue.add(treeNode.left);
            queue.add(treeNode.right);
        }
        while (!queue.isEmpty() && queue.peek()== null) {
            queue.poll();
        }
        return queue.isEmpty();
    }

    @Test
    public void isCompleteTreeTest() {

        TreeNode root = TreeNode.bfsBuild(Arrays.asList(1,2,3,4,5,6));
        Assert.assertEquals(true, isCompleteTree(root));
    }

    /**
     * 是否是值唯一的二叉树
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val!= root.left.val) {
            return false;
        }
        if (root.right != null && root.val!= root.right.val) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);

    }

    /**
     * 通过反转之后，先序遍历数据的路径和给定路径一样，返回反转的中心节点
     * @param root
     * @param voyage
     * @return
     */
    int currentVoyage = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        currentVoyage = 0;
        List<Integer> list = new ArrayList<>();
        matchVoyage(root, voyage, list);
        if (currentVoyage < voyage.length) {
            return Arrays.asList(-1);
        }
        return list;
    }

    private boolean matchVoyage(TreeNode root, int[] voyage, List<Integer> list) {
        if (root == null) {
            return true;
        }
        if (root.val != voyage[currentVoyage]) {
            return false;
        }
        currentVoyage++;
        boolean left = matchVoyage(root.left, voyage, list);
        if (!left) {
            // 交换左右子树
            matchVoyage(root.right, voyage, list);
            matchVoyage(root.left, voyage, list);
            list.add(root.val);
            return true;
        }
        matchVoyage(root.right, voyage, list);
        return true;
    }
    @Test
    public void flipMatchVoyageTest() {
        System.out.println(flipMatchVoyage(TreeNode.bfsBuild(Arrays.asList(1,2,3)) ,new int[] {1,3,2}));
        System.out.println(flipMatchVoyage(TreeNode.bfsBuild(Arrays.asList(1,2)) ,new int[] {2,1}));
    }


    /**
     * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币,
     * 求将n枚硬币分配到n个节点的步数
     * KEY： 我们把子树多出的硬币和欠缺的硬币数归总的根节点, 归总的路径就是结果
     * @param root
     * @return
     */
    private int coin = 0;
    public int distributeCoins(TreeNode root) {
        coin = 0;
        distributeCoin(root);
        return coin;
    }

    private int distributeCoin(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = distributeCoin(root.left);
        int right = distributeCoin(root.right);
        coin += Math.abs(left) + Math.abs(right);
        return root.val + left+ right-1;
    }

    public static class Location implements Comparable<Location>{
        int x;
        int y;
        int val;
        public Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Location o) {
            if (o.x != x) {
                return x-o.x;
            } else if (o.y != y) {
                return y - o.y;
            } else {
                return val - o.val;
            }

        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Location> list = new ArrayList<>();
        vertical(root, 0,0, list);
        Collections.sort(list);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int pre = list.get(0).x;
        for (Location location: list) {
            if (location.x != pre) {
                List<Integer> one = new ArrayList<>();
                one.add(location.val);
                result.add(one);
            } else {
               result.get(result.size()-1).add(location.val);
            }
            pre = location.x;
        }
        return result;
    }

    private void vertical(TreeNode root, int x,int y, List<Location> list) {
        if (root == null) {
            return;
        }

        Location location  = new Location(x, y, root.val);
        list.add(location);
        vertical(root.left, x-1, y+1,list);
        vertical(root.right, x+1, y+1, list);
    }
    @Test
    public void verticalTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(verticalTraversal(node));
    }
}
