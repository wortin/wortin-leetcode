package com.wortin.leetcode.contest438;

import java.util.Arrays;

public class Q2 {
    public static void main(String[] args) {
        long l = new Solution().maxSum(new int[][]{{1, 2}, {3, 4}}, new int[]{1, 2}, 2);
        System.out.println(l);
    }

    static class Solution {
        public long maxSum(int[][] grid, int[] limits, int k) {
            if (k == 0) {
                return 0;
            }
            long sum = 0;
            int n = grid.length;
            int m = grid[0].length;
            int[] curs = new int[n];
            for (int i = 0; i < n; i++) {
                Arrays.sort(grid[i]);
                curs[i] = m - 1;
            }
            for (int t = 0; t < k; t++) {
                int max = -1;
                int maxI = -1;
                for (int i = 0; i < n; i++) {
                    int cur = curs[i];
                    if (cur >= 0 && limits[i] > 0 && grid[i][cur] > max) {
                        max = grid[i][cur];
                        maxI = i;
                    }
                }
                if (maxI > -1) {
                    curs[maxI]--;
                    limits[maxI]--;
                    sum += max;
                }
            }
            return sum;
        }
    }
}
