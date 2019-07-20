package com.example.sort;

/**
 * @Description:
 * @user: yang
 * @Time: 2019/7/19  23:12
 */
public class MergeSort {


    public static int[] merge(int[] arr, int s, int end) {


        //若长度是1，那么返回原来的数组
        if (s == end) {
            int[] temp = new int[1];
            temp[0] = arr[s];
            return temp;
        }


        int mid = (s + end) / 2;

        int[] leftArr = merge(arr, s, mid);
        int[] rightArr = merge(arr, mid + 1, end);

        int[] newArr = new int[leftArr.length + rightArr.length];

        int m = 0;
        int i = 0;
        int j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newArr[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }

        //两个数组不相等的时候，一个遍历完了，另外一个还有数据

        while (i < leftArr.length) {
            newArr[m++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            newArr[m++] = rightArr[j++];
        }
        return newArr;

    }


    public static void main(String[] args) {


        int[] arr = {2, 1, 5, 4, 3,19,20,34,15};

       int[]  temp= merge(arr, 0, arr.length - 1);

        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }


    }

}
