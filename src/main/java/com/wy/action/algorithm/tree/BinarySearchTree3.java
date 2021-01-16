package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1000 往上的题
 */
public class BinarySearchTree3 {

    /**
     * 前序遍历构造二叉搜索树
     * https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
     */
    int bstFromIdx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPre(preorder,0, 1000000000);
    }

    private TreeNode bstFromPre(int[] preorder,int start, int end) {
        if (bstFromIdx >= preorder.length ||
                preorder[bstFromIdx] < start || preorder[bstFromIdx] > end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[bstFromIdx++]);

        root.left = bstFromPre(preorder, start, root.val);
        root.right = bstFromPre(preorder, root.val, end);
        return root;

    }

    /**
     * 从根到叶的二进制数之和
     * https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/
     * @param root
     * @return
     */
    int sumRoot2Leaf = 0;
    public int sumRootToLeaf(TreeNode root) {
        sumRoot2Leaf = 0;
        sumRootToLeafTraverse(root,0);
        return sumRoot2Leaf;
    }

    private void sumRootToLeafTraverse(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        i *=2;
        if (root.left == null && root.right == null) {
            sumRoot2Leaf = sumRoot2Leaf+ i+root.val;
            return;
        }
        sumRootToLeafTraverse(root.left, i+root.val);
        sumRootToLeafTraverse(root.right, i+root.val);
    }

    @Test
    public void setSumRoot2LeafTest() {
        Assert.assertEquals(22,
                sumRootToLeaf(TreeNode.bfsBuild(Arrays.asList(1,0,1,0,1,0,1))));
    }

    /**
     * 两棵二叉搜索树中的所有元素
     * https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
     * @param root1
     * @param root2
     * @return
     */
    int eleIndex = 0;
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        getAllElement1(root1, list1);
        eleIndex = 0;
        getAllElement2(root2, list1, list2);

        while (eleIndex <list1.size()) {
            list2.add(list1.get(eleIndex++));
        }
        return list2;
    }

    private void getAllElement2(TreeNode root1, List<Integer> list1, List<Integer> list2) {
        if (root1 == null) {
            return;
        }
        getAllElement2(root1.left, list1, list2);

        while (eleIndex<list1.size() && list1.get(eleIndex) < root1.val) {
            list2.add(list1.get(eleIndex++));
        }
        list2.add(root1.val);
        getAllElement2(root1.right, list1, list2);
    }

    private void getAllElement1(TreeNode root1, List<Integer> list1) {
        if (root1 == null) {
            return;
        }
        getAllElement1(root1.left, list1);
        list1.add(root1.val);
        getAllElement1(root1.right, list1);
    }

    @Test
    public void getAllElementsTest() {
        TreeNode node1 = TreeNode.bfsBuild(Arrays.asList(2,1,4));
        TreeNode node2 = TreeNode.bfsBuild(Arrays.asList(1,0,3));
        List<Integer> result = getAllElements(node1, node2);
    }

    /**
     * 层数最深的叶子节点之和
     * https://leetcode-cn.com/problems/deepest-leaves-sum/
     * @param root
     * @return
     */
    int depLeaveSum = 0;
    int maxDep = 0;
    public int deepestLeavesSum(TreeNode root) {
        deepestLeaves(root, 0);
        return depLeaveSum;
    }

    private void deepestLeaves(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        i++;
        if (root.left == null && root.right == null) {
            if(i> maxDep) {
                maxDep = i;
                depLeaveSum = root.val;
            } else if (i == maxDep) {
                depLeaveSum += root.val;
            }
        }
        deepestLeaves(root.left, i);
        deepestLeaves(root.right, i);
    }

    int sumEvenGrand = 0;
    public int sumEvenGrandparent(TreeNode root) {
        sumEvenGrand = 0;
        evenGrandparent(root, null, null);
        return sumEvenGrand;
    }

    private void evenGrandparent(TreeNode root, TreeNode father, TreeNode grand) {
        if (root == null) {
            return ;
        }
        if (grand != null && grand.val %2==0) {
            sumEvenGrand += root.val;
        }
        evenGrandparent(root.left, root,father) ;
        evenGrandparent(root.right, root, father);
    }

    @Test
    public void sumEventGrandParentTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(6,7,8,2,7,1,3,9,null,1,4,null,null,null,5));
        Assert.assertEquals(18, sumEvenGrandparent(node));

    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left,target);
        root.right = removeLeafNodes(root.right,target);
        if (root.val == target && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    /**
     * 分裂二叉树的最大乘积
     * @param root
     * @return
     */
    long productMax = 0;
    public int maxProduct(TreeNode root) {
        int sum = getSumByDFS(root);
        maxProduct(root, sum);
        return (int)(productMax % 1000000007);
    }

    private long maxProduct(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        long left = maxProduct(root.left, sum);
        long right = maxProduct(root.right, sum);
        long tem = left * (sum- left);
        if ( tem > productMax) {
            productMax = tem;
        }
        tem = right * (sum - right);
        if (tem > productMax) {
            productMax = tem;
        }
        return root.val + left + right;

    }

    private int getSumByDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getSumByDFS(root.left) + getSumByDFS(root.right) + root.val;
     }
}
