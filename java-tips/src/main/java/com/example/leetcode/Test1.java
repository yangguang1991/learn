package com.example.leetcode;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/2/12  12:51
 */
public class Test1 {

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] result = new int[2];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 1; j < nums.length&&i!=j; j++) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Solution s1 = new Test1().new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        nums=s1.twoSum(nums, target);
        for (int temp: nums ) {
            System.out.println(temp);
        }
    }
}
