package com.wortin.leetcode;

/**
 * 乘积最大子数组
 * <p>
 * 不用c加速算，老老实实累乘
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00152_2 {
    public static void main(String[] args) {
        int i = new Solution().maxProduct(new int[]{2, 3, -2, 4});
        System.out.println(i);
    }

    static class Solution {
        public int maxProduct(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = nums[0];
            for (int i = 1; i < n; i++) {
                int mul = nums[i];
                int r = Math.max(dp[i - 1], mul);
                for (int j = i - 1; j >= 0; j--) {
                    mul *= nums[j];
                    r = Math.max(r, mul);
                }
                dp[i] = r;
            }
            return dp[n - 1];
        }
    }
}
