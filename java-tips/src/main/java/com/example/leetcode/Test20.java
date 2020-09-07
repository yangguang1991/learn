package com.example.leetcode;

import java.util.Stack;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/6 17:27
 */
public class Test20 {


    public static boolean isValid(String s) {
        if (s.length() <= 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {

            if ((arr[i] == ')' && !stack.empty() && stack.peek() == '(') ||
                    (arr[i] == ']' && !stack.empty() && stack.peek() == '[') ||
                    (arr[i] == '}' && !stack.empty() && stack.peek() == '{')
            ) {
                stack.pop();
            } else {
                stack.push(arr[i]);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        String s = "";
        System.out.println(isValid(s));
    }
}
