package com.wortin.leetcode;

public class Q00063 {
    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int i = new Solution().uniquePathsWithObstacles(obstacleGrid);
        System.out.println(i);
    }

    static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
                return 0;
            }

            int[][] dp = new int[m][n];
            int k = 0;
            dp[0][0] = 1;
            for (int j = k + 1; j < n; j++) {
                if (obstacleGrid[k][j] == 1 || dp[k][j - 1] == 0) {
                    dp[k][j] = 0;
                } else {
                    dp[k][j] = 1;
                }
            }
            for (int i = k + 1; i < m; i++) {
                if (obstacleGrid[i][k] == 1 || dp[i - 1][k] == 0) {
                    dp[i][k] = 0;
                } else {
                    dp[i][k] = 1;
                }
            }
            k++;
            while (k < m && k < n) {
                for (int j = k; j < n; j++) {
                    if (obstacleGrid[k][j] == 1) {
                        dp[k][j] = 0;
                    } else {
                        dp[k][j] = dp[k - 1][j] + dp[k][j - 1];
                    }
                }
                for (int i = k + 1; i < m; i++) {
                    if (obstacleGrid[i][k] == 1) {
                        dp[i][k] = 0;
                    } else {
                        dp[i][k] = dp[i - 1][k] + dp[i][k - 1];
                    }
                }
                k++;
            }
            return dp[m - 1][n - 1];
        }
    }
}
