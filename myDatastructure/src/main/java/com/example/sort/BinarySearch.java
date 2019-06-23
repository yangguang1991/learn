package com.example.sort;

/**
 * @Description: 二分查找，用来在有序数组中寻找目标值
 * @user: yangguang_lc
 * @Time: 2018/7/18  8:50
 */
public class BinarySearch {

    public static int method(int[] arr, int result) {
        if (null == arr || arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] == result) {
                return mid;
            } else if (arr[mid] > result) {
                high = mid - 1;
            } else if (result > arr[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int result = 90;
        System.out.println(method(arr, result));

    }

}
