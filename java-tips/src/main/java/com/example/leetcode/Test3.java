package com.example.leetcode;

/**
 * @Description: 按照递归排序的思想，全部排序，以后按照递归的思想来找
 * @user: yang
 * @Time: 2020/2/21  18:53
 */
public class Test3 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

//1. 二分排序，把一个数据加入到另外一个有序数组中，


        int i = 0;
        int j = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid1 = 0;
        int mid2 = 0;

        mid1=(i+len1)/2;



        return 0;
    }

    public static void main(String[] args) {

        int[] num1 = {1, 3, 5};
        int[] num2 = {6, 8, 9};
        findMedianSortedArrays(num1, num2);

    }
}
