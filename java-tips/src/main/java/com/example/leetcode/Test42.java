package com.example.leetcode;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/4 9:19
 */
public class Test42 {

    public static int trap(int[] height) {
        int result = 0;
        if (height.length <= 2) {//小于3的是不可能组成容器，直接返回0
            return result;
        }
        for (int i = 0; i < height.length; i++) {
            if (i + 1 >= height.length || height[i] <= height[i + 1] || height[i] == 0) {//若是到达边界，或者里面比边上高，或者是长度是0 都是不可以作为左边界
                continue;
            } else {
                int j = 0;
                int index = i + 1;
                for (j = i + 2; j < height.length; j++) {
                    if (height[i] > height[j]) {
                        if (height[index] <= height[j]) {//右边界一定是大于桶内最高的那一个的，因此需要将该位置记录
                            index = j;
                        }
                        continue;
                    } else {// 若是高于左边界那么一定是右边界
                        index = j;
                        break;
                    }
                }
                j = j == height.length ? --j : j;
                int temp = 0;
                if (height[i + 1] <= height[index]) {
                    temp = temp + (index - i - 1) * Math.min(height[i], height[index]);
                    for (int k = i + 1; k < index; k++) {
                        temp = temp - height[k];
                    }
                    result = result + temp;
                    i = temp > 0 ? i = --index : i;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //int[] arr = {4, 0, 3, 2};
        //int[] arr = {4, 2, 0, 3, 2, 5};
        int[] arr = {5, 0, 1, 9};
        //int[] arr = {9, 6, 8, 8, 5, 6, 3};
        System.out.println(trap(arr));
    }
}
