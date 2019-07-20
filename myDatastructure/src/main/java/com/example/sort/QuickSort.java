package com.example.sort;

/**
 * @Description:
 * @user: yang
 * @Time: 2019/7/20  10:40
 */
public class QuickSort {


    //快速排序，找到一个基准，比他大的放左边 比他小的放右边

    public static void sort(int[] arr, int s, int e) {

        if (s >= e) {
            return;
        }
        int i = s;
        int j = e;
        int temp = 0;
        int num = arr[s];
        while (i < j) {
            //必须先从右边开始遍历，这样确保遍历以后i和j所指向的数字肯定是小于等于num的，下面37行才可以进行交换
            while (arr[j] > num && i < j)
                j--;
            while (arr[i] <= num && i < j)
                i++;


            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i];
        arr[i] = num;
        arr[s] = temp;
        sort(arr, s, i - 1);
        sort(arr, i + 1, e);

    }

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 4, 3, 4, 4};
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
