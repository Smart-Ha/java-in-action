package com.wy.action.algorithm.stack;

import java.util.Stack;

/**
 * @Author wangyong
 * @Date 2021-02-25
 */
public class StackApp {


    /**
     * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
     *  逆波兰表达式求值
     * @param tokens ["4", "13", "5", "/", "+"]
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        int one;
        for (int i=0; i< tokens.length; i++) {
            String str = tokens[i];
            if ("+".equals(str)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(str)) {
                one = stack.pop();
                stack.push( stack.pop() - one);
            } else if ("*".equals(str)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(str)) {
                one = stack.pop();
                stack.push(stack.pop() / one);
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }


}
