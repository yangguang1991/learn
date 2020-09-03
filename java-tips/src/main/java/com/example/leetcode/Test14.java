package com.example.leetcode;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/3 14:09
 */
public class Test14 {


    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {

            return strs[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            for (int j = 0; j + 1 < strs.length; j++) {
                try {
                    if (strs[j].charAt(i) == strs[j + 1].charAt(i)) {
                        continue;
                    } else {//不相同就结束
                        return stringBuilder.toString();
                    }
                } catch (Exception e) {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(strs[0].charAt(i));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        String[] arr = {"321", "321190"};
        System.out.println(longestCommonPrefix(arr));

    }
}
