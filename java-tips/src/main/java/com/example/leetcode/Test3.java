package com.example.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 按照递归排序的思想，全部排序，以后按照递归的思想来找
 * @user: yang
 * @Time: 2020/2/21  18:53
 */
public class Test3 {


    public static int lengthOfLongestSubstring(String s) {

        char[] arr = s.toCharArray();
        int reslut = 0;
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int temp = set.size();
                set.add(arr[j]);
                if (temp + 1 == set.size()) {
                    continue;
                } else {
                    break;
                }
            }
            if (set.size() > reslut) {
                reslut = set.size();
            }
            set.clear();
        }
        return reslut;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("12223"));
    }
}
