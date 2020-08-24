package com.example.leetcode;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/2/28  18:21
 */
public class Test8 {

    public static int myAtoi(String str) {

        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        boolean flag = false;
        if ((str.charAt(0) == 45 || str.charAt(0) == 43) && str.length() >= 2
                && str.charAt(1) >= 48 && str.charAt(1) <= 57) {
            //说明是负数至少2位，并且第二位不为0的数字
            flag = true;
        }
        if (!flag && (str.charAt(0) < 48 || str.charAt(0) > 57)) {
            return 0;
        }
        String resultStr = flag ? str.charAt(0) + "" : "";
        int i = flag ? 1 : 0;
        while (i <= (str.length() - 1) && str.charAt(i) >= 48 && str.charAt(i) <= 57) {
            resultStr = resultStr + str.charAt(i++);
        }
        int result = 0;
        try {
            result = Integer.valueOf(resultStr);
        } catch (Exception e) {
            result = str.charAt(0) == 45 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("   -00000000000000000"));

    }
}
