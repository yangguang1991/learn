package com.example.sort;

/**
 * @Description:
 * @user: yang
 * @Time: 2019/4/23  21:21
 */
public class QuickSort {


    public static void sort(int[] arr, int i, int j) {

        int left = i;
        int right = j;
        if(i>=j){
            return;
        }
        //找到一個基准位
        int temp = arr[i];
        while (i < j) {
            //右边的都比temp要大
            while (arr[j] >= temp && i < j) {

                j--;
            }

            //左边的都比temp要小
            while (arr[i] <= temp && i < j) {
                i++;
            }

        }
        sort(arr,left,i);
        sort(arr,j,right);

    }


    public static void main(String[] args) {

        int[] arr = {1, 4, 3, 2, 5, 7, 6};
        sort(arr, 0, arr.length - 1);


    }
}
