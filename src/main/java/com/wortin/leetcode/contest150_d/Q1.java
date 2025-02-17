package com.wortin.leetcode.contest150_d;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q1 {
    public static void main(String[] args) {
        int[] ints = {1, 3, 2, 1, 5, 4};
        int i = new Solution().sumOfGoodNumbers(ints, 2);
        System.out.println(i);
    }

    static class Solution {
        public int sumOfGoodNumbers(int[] nums, int k) {
            int n = nums.length;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((i - k < 0 || nums[i] > nums[i - k]) && (i + k >= n || nums[i] > nums[i + k])) {
                    sum += nums[i];
                }
            }
            return sum;
        }
    }
}
