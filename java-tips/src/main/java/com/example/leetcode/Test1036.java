package com.example.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Author: kevin yang
 * @Description: 超时
 * @Date: create in 2020/9/9 15:15
 */
public class Test1036 {

    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {

        //已经访问过的不再访问
        Set<String> set = new HashSet<>();
        for (int i = 0; i < blocked.length; i++) {
            set.add("" + blocked[i][0] + blocked[i][1]);
        }

        //保存的路径
        Stack<String> stack = new Stack<>();
        int x = source[0];
        int y = source[1];
        set.add("" + x + y);
        stack.push("" + x + y);

        boolean flag = true;
        while (source[0] != target[0] || source[1] != target[1]) {

            if (right(source, set, stack)) {
                continue;
            }
            if (left(source, set, stack)) {
                continue;
            }
            if (down(source, set, stack)) {
                continue;
            }
            if (up(source, set, stack)) {
                continue;
            }
            //说明上下左右都走不通了
            if (stack.empty()) {//说明彻底GG
                flag = false;
                break;
            } else {
                stack.pop();
            }
        }
        return flag;
    }

    public static boolean right(int[] source, Set<String> set, Stack<String> stack) {

        int x = source[0];
        int y = source[1];

        //Math.pow(10, 6)
        if (++y < Math.pow(10, 6) && !set.contains("" + x + y)) {
            set.add("" + x + y);
            stack.push("" + x + y);
            source[1] = y;
            return true;
        }
        return false;
    }


    public static boolean left(int[] source, Set<String> set, Stack<String> stack) {

        int x = source[0];
        int y = source[1];

        if (--x >= 0 && !set.contains("" + x + y)) {
            set.add("" + x + y);
            stack.push("" + x + y);
            source[0] = x;
            return true;
        }
        return false;
    }


    public static boolean down(int[] source, Set<String> set, Stack<String> stack) {

        int x = source[0];
        int y = source[1];

        if (++x < Math.pow(10, 6) && !set.contains("" + x + y)) {
            set.add("" + x + y);
            stack.push("" + x + y);
            source[0] = x;
            return true;
        }
        return false;
    }

    public static boolean up(int[] source, Set<String> set, Stack<String> stack) {

        int x = source[0];
        int y = source[1];

        if (--y >= 0 && !set.contains("" + x + y)) {
            set.add("" + x + y);
            stack.push("" + x + y);
            source[1] = y;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        //int[][] blocked = {{0, 1}, {1, 0}};
        int[][] blocked = {};
        int[] source = {0, 0};
        int[] target = {999999, 999999};
        long t = System.currentTimeMillis();
        System.out.println(isEscapePossible(blocked, source, target));
        System.out.println(System.currentTimeMillis() - t);
    }
}
