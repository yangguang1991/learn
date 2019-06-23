package com.example.sort;

/**
 * @Description:
 * @user: yang
 * @Time: 2019/6/1  21:14
 */
public class InsertSort {

    //插入排序，每次找到合适的位置讲数据放进去，其他元素都后移一位

    public static void main(String[] args) {

        int[] arr = {2, 1, 4, 5, 8, 6, 0};
        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }


    public static void sort(int[] arr) {


        int temp = 0;
        int j = 0;
        int k = 0;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    continue;
                } else {
                    break;
                }
            }
            if (j + 1 == i) {//说明天然有序，不需要移动
                continue;
            }
            //说明j的位置是小雨等于temp的，那么后面的元素都可以移动了
            for (int m = i - 1; m > j; m--) {
                arr[m + 1] = arr[m];
            }

            arr[j + 1] = temp;

        }
    }
}
