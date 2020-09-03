package com.example.leetcode;

/**
 * @Author: kevin yang
 * @Description: 暴力破解 11
 * @Date: create in 2020/9/2 19:17
 */
public class Test11 {

    public static int maxArea(int[] height) {
        int result = 0;
        for (int n = 1; n < height.length; n++) {
            for (int i = 0; i + n < height.length; i++) {
                int temp = height[i] > height[i + n] ? height[i + n] : height[i];
                if (temp * n > result) {
                    result = temp * n;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(arr));
    }
}
