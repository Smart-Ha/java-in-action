package com.wy.action.algorithm.tree;

import com.wy.action.entity.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author wangyong
 * @Date 2020-03-13
 */
public class BinarySearchTree0 {

    /**
     * 找出数组中是否有目标值 数组值从左到右有序，下一行比上一行的值大
     * https://leetcode.com/problems/search-a-2d-matrix/
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length==0) {
            return false;
        }
        int start = 0, end = matrix.length-1;
        if (matrix[0][0]>target || matrix[end][matrix[0].length-1]<target) {
            return false;
        }
        int row = 0;
        int mid;
        while (true) {
            mid = (start + end) /2;
            if (matrix[mid][0]> target) {
                end = mid-1;
            } else if( matrix[mid][0] < target) {// 第一个值比目标值小
                if (mid +1 < matrix.length) {
                    if (matrix[mid+1][0]> target) {//下一行比目标值大
                        row = mid;
                        break;
                    }
                    start = mid+1;
                } else {//最后一行
                    row = mid;
                    break;
                }
            } else {
                return true;
            }
        }
        start = 1;
        end = matrix[0].length-1;
        while (true) {

            mid = (start+ end)/2;
            if (matrix[row][mid] == target) {
                return true;
            } if (matrix[row][mid]> target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
            if (start > end) {
                return false;
            }
        }
    }

    @Test
    public void searchMatrixTest() {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30, 34, 50}
        };
        Assert.assertEquals(true, searchMatrix(matrix, 3));
        Assert.assertEquals(true, searchMatrix(matrix, 16));
        Assert.assertEquals(false, searchMatrix(matrix, 15));
        Assert.assertEquals(false, searchMatrix(matrix, 45));
    }

    /**
     * 求平方根
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 1) {
            return 0;
        }
        int start=1,end = x;
        int mid ;
        while (true) {
            mid = (start+end)/2;
            if(mid * mid > x) {
                end = mid-1;
            } else {
                if((mid +1)*(mid+1) > x) {
                    return mid;
                }
                start = mid+1;
            }

        }
    }

    @Test
    public void mySqrtTest() {
        Assert.assertEquals(1,mySqrt(2));
        Assert.assertEquals(8,mySqrt(64));
        Assert.assertEquals(10,mySqrt(101));
        Assert.assertEquals(100,mySqrt(10000));
    }


    public int search(int[] nums, int target) {

        if(nums.length == 0) return -1;
        boolean inLeft = true;
        if(target == nums[0]){
            return 0;
        }else if(target < nums[0]){
            inLeft = false;
        }
        return searchRecursion(nums,1,nums.length-1,target,inLeft);
    }

    private int searchRecursion(int[] nums, int left, int right,int target, boolean inLeft) {
        if(left>right)  return -1;
        int mid = (left+right)/2;
        if(nums[mid] == target) return mid;
        if(inLeft && nums[mid]< nums[0]){
            return searchRecursion(nums,left,mid-1,target,inLeft);
        }

        if(!inLeft && nums[mid]>nums[right]){
            return searchRecursion(nums,mid+1,right,target,inLeft);
        }

        if(target > nums[mid]){
            return searchRecursion(nums,mid+1,right,target,inLeft);
        } else {
            return searchRecursion(nums,left,mid-1,target,inLeft);
        }
    }

    @Test
    public void searchTest() {
        int[] arr = {4,5,6,7,0,1,2};
        Assert.assertEquals(2, search(new int[]{5,1,3}, 3));
        Assert.assertEquals(0, search(new int[]{1,2,3}, 1));
        Assert.assertEquals(2, search(new int[]{1,2,3}, 3));
        Assert.assertEquals(4, search(new int[]{4,5,6,7,8,1,2,3}, 8));
        Assert.assertEquals(0, search(new int[]{5,1,3}, 5));

        Assert.assertEquals(4, search(arr, 0));
        Assert.assertEquals(2, search(arr, 6));
        Assert.assertEquals(-1, search(arr, 3));
    }

    /**
     * 将一个排序的数组部分挪移之后，查找数组中是否存在target
     * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
     * @param nums 4,5,6,7,0,1,2,2
     * @param target 0
     * @return
     */
    public boolean search2(int[] nums, int target) {
        if (nums.length == 0) return false;
        boolean inLeft;
        if (target == nums[0]) {
            return true;
        } else if( target > nums[0]){
            inLeft = true;
        } else {
            inLeft = false;
        }
        return search2Recursion(nums, 0, nums.length-1, target, inLeft);
    }

    private boolean search2Recursion(int[] nums, int left, int right, int target, boolean inLeft) {
        if (left>right) return false;
        int mid = (left+right) /2;
        if (nums[mid] == target) return true;
        if (inLeft && nums[0]>= nums[mid]) {// 在左边临界
            return search2Recursion(nums,left, mid-1, target, inLeft);
        }
        if (!inLeft && nums[mid]>= nums[right]) {// 在右边临界
            return search2Recursion(nums,mid+1 , right, target, inLeft);
        }

        if (target > nums[mid]) {
            return search2Recursion(nums,mid+1, right, target, inLeft);
        } else {
            return search2Recursion(nums,left, mid-1, target, inLeft);
        }

    }

    @Test
    public void test(){
        int[] arr = {2,5,6,0,0,1,2};
//        Assert.assertEquals(true, search2(arr, 5));
//        Assert.assertEquals(true, search2(arr, 0));
//        Assert.assertEquals(false, search2(arr, 3));
        Assert.assertEquals(true, search2(new int[]{1,3,1,1,1}, 3));
    }


    /**
     * 判断是否为二叉搜索树,
     * key: 对于左子树，他的取值范围为 (最左子树, 根节点）; 对于右子树，取值范围为 (根节点， 最右子树)
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSTR(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTR(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBSTR(root.left, minVal, root.val) && isValidBSTR(root.right, root.val, maxVal);
    }

    @Test
    public void isValidBSTTest() {
        TreeNode root = TreeNode.bfsBuild(Arrays.asList(5,1,4,null,null, 3,6));
        Assert.assertEquals(false, isValidBST(root));
    }

    /**
     * 二叉查找树中有两个节点位置反了，纠正过来
     * @param root
     */


    public void recoverTree(TreeNode root) {
        if (root == null) return;

        TreeNode errorOne = null;
        TreeNode errorTwo = null;
        TreeNode pre = null;

        //  key 中序遍历
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (pre != null) {
                if (pre.val > root.val) {
                    if (errorOne  == null) {
                        errorOne = pre;
                        errorTwo = root;
                    } else {
                        errorTwo = root;
                    }
                }
            }
            pre = root;
            // 中序遍历
            root = root.right;
        }
        int temp = errorOne.val;
        errorOne.val = errorTwo.val;
        errorTwo.val = temp;
    }

    @Test
    public void recoverTreeTest() {
        TreeNode node = TreeNode.bfsBuild(Arrays.asList(5,3,6,2,4,null,null,1));
        recoverTree(node);


    }
}
