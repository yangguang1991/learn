package com.example.leetcode;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/3/14  12:56
 */
public class Test9 {

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x <= 9) {
            return true;
        }
        if (x >= 10) {
            if (x % 10 == 0) {
                return false;
            } else {
                String str = x + "";
                int end = str.length() - 1;
                int start = 0;
                while (start < end && str.charAt(end) == str.charAt(start)) {
                    start++;
                    end--;
                }
                return start >= end;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(90));
    }
}
