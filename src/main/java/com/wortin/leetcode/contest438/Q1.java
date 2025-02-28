package com.wortin.leetcode.contest438;

public class Q1 {
    static class Solution {
        public boolean hasSameDigits(String s) {
            int[] nums = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                nums[i] = s.charAt(i) - '0';
            }
            int end = s.length();
            while (end > 2) {
                for (int i = 0; i < end - 1; i++) {
                    nums[i] = (nums[i] + nums[i + 1]) % 10;
                }
                end--;
            }
            return nums[0] == nums[1];
        }
    }
}
