package com.example.leetcode;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/5 21:54
 */
public class Test16 {

    public static int threeSumClosest(int[] nums, int target) {

        int div = Integer.MAX_VALUE;
        int result = 0;
        int temp = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sum = nums[i] + nums[j] + nums[k];
                    temp = Math.abs(sum - target);
                    if (temp < div) {
                        if (temp == 0) {
                            return sum;
                        }
                        div = temp;
                        result = sum;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1 };
        int target = 1;
        System.out.println(threeSumClosest(nums, target));

    }
}
