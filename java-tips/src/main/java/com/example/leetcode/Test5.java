package com.example.leetcode;

import java.util.Stack;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/2/22  13:24
 */
public class Test5 {

    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int pre_index;//前一个
        int next_index;//后一个
        String temp_result = "";
        String result = s.charAt(0) + "";
        for (int i = 1; i < s.length(); i++) {
            //aba这种形式
            next_index = i + 1;
            pre_index = i - 1;
            temp_result = s.charAt(i) + "";
            while (pre_index >= 0 && next_index < s.length() &&
                    s.charAt(next_index) == s.charAt(pre_index)) {
                temp_result = s.charAt(next_index) + temp_result + s.charAt(pre_index);
                --pre_index;
                ++next_index;
            }
            if (temp_result.length() > result.length()) {
                result = temp_result;
            }
            // aa这种形式
            temp_result = "";
            next_index = i;
            pre_index = i - 1;
            while (pre_index >= 0 && next_index < s.length() &&
                    s.charAt(next_index) == s.charAt(pre_index)) {
                temp_result = (s.charAt(pre_index) + "") +temp_result+ s.charAt(next_index);
                --pre_index;
                ++next_index;
            }
            if (temp_result.length() > result.length()) {
                result = temp_result;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(longestPalindrome(""));


    }
}
