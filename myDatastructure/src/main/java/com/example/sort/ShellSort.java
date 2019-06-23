package com.example.sort;

/**
 * @Description: 希尔排序, 设置不同的gap，将待排数组分成一个的子序列，分别对这个子序列实现插入排序
 * 最后一轮的gap一定是1，也就是退化为直接插入排序
 * 1. 为什么会有希尔排序？
 * 因为直接插入排序适合待排数组基本有序，希尔排序前几轮就是为了实现待排序列基本有序的操作
 * @user: yang
 * @Time: 2019/6/17  22:42
 */
public class ShellSort {


    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 4, 3};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] arr) {

        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
            System.out.println("gap=" + gap);
            shellSort(arr, gap);
        }

    }


    public static void shellSort(int[] arr, int gap) {

        for (int i = 0; i < gap; i++) {//总共进行gap个子序列

            int num = 0;

            for (int j = i + gap; j < arr.length; j = j + gap) {//对每个子序列执行直接插入排序

                num = arr[j];
                int k = 0;
                for (k = j - gap; k >= i; k = k - gap) {

                    if (arr[k] > num) {
                        continue;
                    } else {
                        break;
                    }
                }

                if (k == j - gap) {//不需要移动
                    continue;
                }

                for (int m = j - gap; m > k; m = m - gap) {

                    arr[m + gap] = arr[m];
                }

                arr[k + gap] = num;

            }


        }


    }


}
