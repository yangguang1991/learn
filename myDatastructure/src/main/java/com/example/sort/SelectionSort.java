package com.example.sort;

/**
 * @Description: 选择排序，每次都选择一个最小的放在左边
 * @user: yang
 * @Time: 2019/6/12  22:30
 */
public class SelectionSort {

    public static void main(String[] args) {


        int[] arr = {2, 1, 5, 4, 3};

        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] arr) {

        int index = 0;//记录当前最小的位置
        int temp = 0;//
        int num = 0;
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //每次循环找到最小值，放到左边
            num = arr[i];
            index = i;
            for (j = i; j < arr.length; j++) {
                if (arr[j] < num) {
                    index = j;
                }
            }

            if (index == i) {//不需要移动
                continue;
            } else {
                temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
