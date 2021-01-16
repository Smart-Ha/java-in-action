package com.wy.action.algorithm.link;

import com.wy.action.entity.ListNode;
import com.wy.action.entity.Node;
import com.wy.action.entity.TreeNode;
import com.wy.action.util.Print;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 链表
 * @Author wangyong
 * @Date 2019-12-21
 */
public class LinkedApp {

    /**
     * 判断是否为回文
     * // 使用快慢两个指针 定位中点，并逆转前半部分
     */
    public boolean palindrome (Node node) {
        if(node == null) {
            return false;
        }
        Node one = node;
        Node pre = null;
        Node two = node;
        boolean isOdd = false;
        while (two != null) {
            two = two.next;
            if (two != null) {
                two = two.next;
                // 反转前半本部分链表
                Node temp = one.next;
                one.next = pre;
                pre = one;
                one = temp;
            } else {
                isOdd = true;
            }

        }
        if (isOdd) {//单数
            one = one.next;
        }
        while (pre!=null && one !=null) {
            if(!pre.value.equals(one.value)) {
                return false;
            }
            pre = pre.next;
            one = one.next;
        }
        return true;
    }

    @Test
    public void test() {
        String[] values = {"aa","bb","cc","ccc","bb","aa"};
        Node pre = new Node(values[0]);
        Node head = pre;
        for (int i=1; i<values.length; i++) {
            Node n = new Node(values[i]);
            pre.next =n;
            pre = n;
        }
        System.out.println(palindrome(head));
    }

    /**
     * 向右循环移动k次链表
     *https://leetcode.com/problems/rotate-list/
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head ==null || head.next ==null || k==0) {
            return head;
        }
        ListNode node = head;
        ListNode tail = null;
        int n=1;
        while (node.next != null) {
            n++;
            node = node.next;
        }
        tail = node;
        k= k%n;
        if(k==0) {
            return head;
        }
        int i=1;
        node = head;
        while (i<n-k) {
            node = node.next;
            i++;
        }
        ListNode newHead = node.next;
        node.next = null;
        tail.next = head;
        return newHead;
    }

    /**
     * 移除所有重复元素的节点
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
     * @param head 1->2->3->3->4->4->5
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode last = null;
        ListNode node = head;
        ListNode newHead = null;
        while (node != null) {
            boolean flag = true;
            while (node.next!= null && node.val == node.next.val) {
                node = node.next;
                flag = false;
            }

            if (flag)  {//本次没有移除
                if (last != null) {
                    last.next = node;
                }
                last = node;

                if (newHead == null) {
                    newHead = node;
                }
            }
            node = node.next;
        }
        if(last != null) {
            last.next = null;
        }
        return newHead;
    }

    @Test
    public void deleteDuplicatesTest() {
        ListNode head = ListNode.construct(Arrays.asList(1,2,3,3,4,4,5));
        ListNode.print(deleteDuplicates(head));

        ListNode head1 = ListNode.construct(Arrays.asList(2,2,2));
        ListNode.print(deleteDuplicates(head1));
    }


    /**
     * 移除重复的节点
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head== null || head.next == null) return head;
        ListNode last = head;
        ListNode node = head.next;
        while (node != null) {
            if (node.val == last.val) {
                node = node.next;
                continue;
            }
            last.next = node;
            last = node;
            node = node.next;
        }
        last.next = null;
        return head;
    }

    @Test
    public void deleteDuplicatesTest2() {
        ListNode head = ListNode.construct(Arrays.asList(1,2,3,3,4,4,5));
        ListNode.print(deleteDuplicates2(head));

        ListNode head1 = ListNode.construct(Arrays.asList(2,2,2));
        ListNode.print(deleteDuplicates2(head1));
    }

    /**
     * 将链表中小于x的节点排到 大于等于x的节点前面，保持原来的相对顺序
     * https://leetcode.com/problems/partition-list/
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null,node = head, before = null, after = null;
        while (node != null) {
            ListNode next = node.next;
            if (node.val < x) {

                if (before != null) {
                    ListNode temp = before.next;
                    before.next = node;
                    if (temp != node) {
                        node.next = temp;
                    }

                }
                before = node;
                if (newHead == null) {
                    newHead = node;
                    if (after != null)
                        before.next = head;
                }
                if (after != null) {
                    after.next = next;
                }

            } else {
                after = node;
            }
            node = next;
        }
        if (newHead == null) {
            newHead = head;
        }
        if (before !=null && before.next == null) {
            before.next = after;
        }
        if (after != null) {
            after.next = null;
        }


        return newHead;
    }

    @Test
    public void partitionTest() {
        ListNode head = ListNode.construct(Arrays.asList(1,4,3,2,5,2));
        ListNode.print(partition(head, 3));
        System.out.println();
        ListNode.print(partition(ListNode.construct(Arrays.asList(1,1)), 0));
        System.out.println();
        ListNode.print(partition(ListNode.construct(Arrays.asList(1,1)), 2));
        System.out.println();
        ListNode.print(partition(ListNode.construct(Arrays.asList(2,1)), 2));
        System.out.println();
        ListNode.print(partition(ListNode.construct(Arrays.asList(2,1,3)), 2));
    }

    /**
     * 反转链表从m到n的元素
     * https://leetcode.com/problems/reverse-linked-list-ii/
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode curr = head, pre=null;
        while (m>1) {
            pre = curr;
            curr = curr.next;
            m--;
            n--;
        }

        ListNode newHead = pre, temp,last=curr;
        while (n >0) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
            n--;
        }
        if (newHead != null) {
            newHead.next = pre;
        } else {//第一个元素调换了
            head = pre  ;
        }
        last.next = curr;
        return head;
    }

    @Test
    public void reverseBetweenTest() {
        ListNode.print(reverseBetween(ListNode.construct(Arrays.asList(1,2,3,4,5)), 2,4));
        ListNode.print(reverseBetween(ListNode.construct(Arrays.asList(1,2,3,4,5)), 1,4));
        ListNode.print(reverseBetween(ListNode.construct(Arrays.asList(1,2,3,4,5)), 1,5));
        ListNode.print(reverseBetween(ListNode.construct(Arrays.asList(1)), 1,1));
    }


    /**
     * 返回中序遍历的结果
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        while (!stack.empty()) {
            TreeNode one = stack.pop();
            result.add(one.val);
            curr = one.right;
            while (curr !=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return result;
    }

    /**
     * 二叉搜索树
     * key: 先生成所有的排列组合，再判断组合是否满足二叉查找树 左子树的节点都比根节点小，右子树的都比根节点大
     * https://leetcode.com/problems/unique-binary-search-trees-ii/
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return genTreeList(1, n);
    }


    private List<TreeNode> genTreeList(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
        }
        List<TreeNode> left, right;
        for(int i=start; i<=end; i++) {
            left = genTreeList(start, i-1);
            right = genTreeList(i+1, end);
            for (TreeNode treeNode: left) {
                for (TreeNode  treeNode1: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = treeNode;
                    root.right = treeNode1;
                }
            }
        }
        return list;
    }




    /**
     * 构造全排列
     * @param n
     * @param list
     * @param arr
     * @param count
     */
    private void dfsArray(int n, Vector<Integer> list, int[] arr, int count){
        if (count == n) {
            for(int i=1;i<arr.length; i++) {
                System.out.println(arr);
            }
            return;
        }
        for(int i=0; i<list.size(); i++) {
            int value = list.get(i);
            list.remove(i);
            arr[count] = value;
            dfsArray(n, list, arr, count+1);
            list.insertElementAt(value, i);
        }
    }

    @Test
    public void generateTreesTest() {
        generateTrees(3);
    }


    /**
     * 二叉树中的是否存在一条自顶向下的链表
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        return isSubPathDps(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSubPathDps(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        if (root.val != head.val) {
            return false;
        }
        head = head.next;
        return isSubPathDps(head, root.left) || isSubPathDps(head, root.right);
    }

    @Test
    public void isSubPathTest() {
        TreeNode root = TreeNode.bfsBuild(Arrays.asList(1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3));
        ListNode listNode = ListNode.construct(Arrays.asList(4,2,8));
        Assert.assertEquals(true, isSubPath(listNode, root));

        root = TreeNode.bfsBuild(Arrays.asList(2,null,2,null,2,null,1));
        listNode = ListNode.construct(Arrays.asList(2,2,1));
        Assert.assertEquals(true, isSubPath(listNode, root));
    }
}
