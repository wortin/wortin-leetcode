package com.wortin.leetcode;

/**
 * 乘积最大子数组
 * <p>
 * 考虑dp
 * dp[i]是nums[0,i]中乘积最大子数组的乘积
 * 那么如果已知dp[0...i-1]，怎么推出dp[i]?
 * 对nums[i] 要不要加入本次结果？不加入，就是dp[i-1]；加入，那么就是往前推，找最大乘积
 * 为了加快往前推，用累乘提前算出
 * c[i][j]是nums[i..j]的乘积
 * c[i][j] = c[i+1][j-1]*nums[i]*nums[j] i<j
 * <p>
 * 超出内存限制了！说明c[i][j]太占内存了
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00152 {
    public static void main(String[] args) {
        int i = new Solution().maxProduct(new int[]{2, 3, -2, 4});
        System.out.println(i);
    }

    static class Solution {
        public int maxProduct(int[] nums) {
            int n = nums.length;
            int[][] c = new int[n][n];
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                c[i][i] = nums[i];
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    c[i][j] = nums[i] * nums[j];
                    if (i + 1 <= j - 1) {
                        c[i][j] *= c[i + 1][j - 1];
                    }
                }
            }
            dp[0] = nums[0];
            for (int i = 1; i < n; i++) {
                int r = dp[i - 1];
                for (int j = i; j >= 0; j--) {
                    r = Math.max(r, c[j][i]);
                }
                dp[i] = r;
            }
            return dp[n - 1];
        }
    }
}
