package com.wortin.leetcode;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01760 {
    public static void main(String[] args) {
        int[] nums = {9};
        int i = new Solution().minimumSize(nums, 2);
        System.out.println(i);
    }


    static class Solution {
        public int minimumSize(int[] nums, int maxOperations) {
            // 1 <= nums.length <= 105
            // 1 <= maxOperations, nums[i] <= 109
            // 现在要找一个y，使得经过若干次操作后，所有数都不超过y
            // 显然，nums中最大的那个数nums[?]是符合要求的一个y_m，所有数都不超过这个最大值nums[?]
            // 对于给定的y，操作次数是可以确定的。一个nums[i]需要经过(nums[i]-1)/y次操作，nums求和即可。
            // 显然，对于y_m, 操作次数是0次，不需要操作，y_m它是最大的那个y
            // 对于给定的y，如果操作次数不超过maxOperations，那么它符合要求
            // 题目要找到所有符合要求的y中最小的y
            // 如果maxOperations足够多，y可能的最小值是1，给它切到最碎
            // 这样找y具有单调性，通过2分查找
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                }
            }
            return getY0(nums, maxOperations, max);
        }

        private int getY0(int[] nums, int maxOperations, int max) {
            int left = 1, right = max;
            int y0 = max;
            while (left <= right) {
                int y = left + (right - left) / 2;
                // 求出对于给定的y，所需的操作次数
                int opt = 0;
                for (int num : nums) {
                    opt += (num - 1) / y;
                }
                if (opt > maxOperations) {
                    left = y + 1;
                } else {
                    right = y - 1;
                    y0 = y;
                }
            }
            return y0;
        }
    }
}
