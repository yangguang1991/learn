package com.example.search;

/**
 * @Description: 二分查找的代码，还包括两个变种包括找到最左或者最右的元素，返回的是-1表示没有找到，其他表示下标
 * @user: yang
 * @Time: 2019/7/28  9:20
 */
public class BinarySearch {


    //找到这个元素
    public static int binarySearch(int[] nums, int temp) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {

            int m = (l + r) / 2;

            if (nums[m] == temp) {

                return m;

            } else if (nums[m] > temp) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    //出现相同的元素返回最左边的
    public static int binarySearch1(int[] nums, int temp) {
        int l = 0;
        int r = nums.length - 1;
        int pre = -1;

        while (l <= r) {

            int m = (l + r) / 2;

            if (nums[m] > temp) {

                r = m - 1;

            } else if (nums[m] == temp) {
                pre = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return pre;
    }


    //出现相同的元素返回最右边的
    public static int binarySearch2(int[] nums, int temp) {
        int l = 0;
        int r = nums.length - 1;
        int pre = -1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] > temp) {
                r = m - 1;

            } else if (nums[m] == temp) {
                pre = m;
                l = m + 1;
            } else {
                l = m + 1;
            }
        }
        return pre;
    }


    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 3, 3, 3, 4, 5, 7};


        System.out.println(binarySearch1(nums, 6));

    }
}
