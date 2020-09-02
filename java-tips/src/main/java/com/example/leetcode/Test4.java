package com.example.leetcode;

import java.util.Stack;

/**
 * @Author: kevin yang
 * @Description: 寻找中位的数字
 * @Date: create in 2020/9/2 17:40
 */
public class Test4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //奇数的话，中间的就是中位数
        int all = nums1.length + nums2.length;
        int middle = all / 2 + 1;
        int num = 0;
        int i = 0;
        int j = 0;
        if (nums2.length == 0) {
            int[] temp = null;
            temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        //
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;
        while (++num <= middle) {
            for (; i < nums1.length; ) {
                if (j >= nums2.length) {
                    flag = true;
                    stack.push(nums1[i]);
                    i++;
                    break;
                }

                if (nums1[i] <= nums2[j]) {
                    flag = true;
                    stack.push(nums1[i]);
                    i++;
                    break;
                }
                break;
            }
            for (; j < nums2.length && !flag; ) {
                if (nums1.length == 0 || i >= nums1.length) {
                    stack.push(nums2[j]);
                    j++;
                    break;
                }
                if ((nums1.length != 0 && nums1[i] > nums2[j]) || i == nums1.length - 1) {
                    stack.push(nums2[j]);
                    j++;
                    break;
                }

            }
            flag = false;
        }
        return getResult(all, stack);
    }

    public static double getResult(int all, Stack<Integer> stack) {
        double result = all % 2 == 0 ? (double) (stack.pop() + stack.pop()) / 2 : stack.pop();
        return result;
    }

    public static void main(String[] args) {
        int[] num2 = {1};
        int[] num1 = {3, 4};
        System.out.println(findMedianSortedArrays(num1, num2));
    }
}