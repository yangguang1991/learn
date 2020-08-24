package com.example.leetcode;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/2/28  13:20
 */
public class Test7 {


    public static int reverse(int x) {

        String str = x + "";
        StringBuilder stringBuilder = new StringBuilder();
        int end = 0;
        if (x < 0) {
            end = 1;
            stringBuilder.append("-");
        }
        for (int i = str.length() - 1; i >= end; i--) {
            stringBuilder.append(str.charAt(i));
        }
        int result = 0;

        try {
            result = Integer.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(reverse(-2147483648));
    }
}
