package com.wy.action.algorithm.link;

import java.util.Stack;

public class MinStack {

    private int min ;
    private Stack<Node> stack;
    public class Node {
        public int min;
        public int val;

        public Node(int min, int val) {
            this.min = min;
            this.val = val;
        }
    }
    public MinStack() {
        min = Integer.MAX_VALUE;
        stack = new Stack<>();
    }

    public void push(int x) {
        if (x< min) {
            min = x;
        }
        Node node = new Node(min, x);
        stack.push(node);
    }

    public void pop() {
        stack.pop();
        if (stack.empty()) {
            min = Integer.MAX_VALUE;
            return;
        }
        min = stack.peek().min;
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }
}
