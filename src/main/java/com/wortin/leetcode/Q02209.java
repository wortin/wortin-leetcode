package com.wortin.leetcode;

/**
 * 铺地毯
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02209 {

    public static void main(String[] args) {
        int i = new Solution().minimumWhiteTiles("10110101", 2, 2);
        System.out.println(i);
    }

    static class Solution {
        public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
            int n = floor.length();
            // dp[i][j] 表示在[0,i]下标的地板上铺j块毛毯所能达到的最小空白格数量
            // dp[i][j] = 下面2者取最小
            //             i处不放地毯，那么就是j块全放在[0,i-1]上，有 dp[i-1][j] + floor[i]若白则1否0
            //             i处放地毯，那么还剩j-1块，放在[0,i-len]，有 dp[i-len][j-1]
            // 初始边界 dp[0][0]=floor[0]
            //         dp[0][j]=0
            //         dp[i][0]= [0,i]中白格的总个数
            int[][] dp = new int[n][numCarpets + 1];
            dp[0][0] = floor.charAt(0) - '0';
            for (int j = 1; j <= numCarpets; j++) {
                dp[0][j] = 0;
            }
            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][0] + (floor.charAt(i) - '0');
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= numCarpets; j++) {
                    int c1 = dp[i - 1][j] + (floor.charAt(i) - '0');
                    int c2 = 0;
                    if (i - carpetLen >= 0) {
                        c2 = dp[i - carpetLen][j - 1];
                    }
                    dp[i][j] = Math.min(c1, c2);
                }
            }
            return dp[n - 1][numCarpets];
        }
    }
}
