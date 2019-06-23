package com.example.sort;

/**
 * @Description: 冒泡排序，其中加入了优化，也就是冒泡排序没有移动的时候，就认为整个数组有序了，直接退出
 * @user: yang
 * @Time: 2019/6/12  22:58
 */
public class BubbleSort {


    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 4, 3};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] arr) {
        boolean flag = true;

        for (int i = 0; (i < arr.length - 1) && flag == true; i++) {

            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] + arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];
                    flag = true;
                }
            }


        }


    }

}
