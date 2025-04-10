package com.wortin.leetcode;

import java.util.Arrays;

/**
 * we need enumerate the diagonal
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02711 {
    public static void main(String[] args) {
        int[][] ints = new Solution().differenceOfDistinctValues(new int[][]{{15, 42, 48, 22, 36, 47, 13, 23, 31, 41}, {25, 3, 44, 17, 37, 9, 14, 37, 4, 43}, {7, 15, 38, 10, 25, 7, 37, 6, 43, 4}, {50, 9, 14, 36, 35, 36, 44, 17, 10, 44}, {46, 50, 45, 28, 10, 18, 18, 3, 42, 24}, {14, 11, 13, 32, 37, 31, 50, 32, 12, 38}, {44, 24, 42, 9, 32, 40, 8, 20, 46, 39}, {33, 5, 42, 30, 20, 37, 26, 38, 30, 30}, {32, 39, 31, 33, 41, 23, 4, 29, 44, 22}, {8, 8, 11, 21, 9, 2, 37, 19, 30, 37}});
        System.out.println(Arrays.deepToString(ints));
//        long ans = 0;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 37;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 44;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 38;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 8;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 31;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 10;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 36;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 38;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 3;
//        System.out.println(Long.bitCount(ans));
//        ans |= 1L << 15;
//        System.out.println(Long.bitCount(ans));
    }

    static class Solution {
        public int[][] differenceOfDistinctValues(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            long[][] ans = new long[n][m];
            for (int i = 0; i < n; i++) ans[i] = new long[m];
            for (int k = 1; k < m + n; k++) {
                int sj = Math.max(m - k, 0);
                int ej = Math.min(m + n - 1 - k, m - 1);
                for (int j = sj; j <= ej; j++) {
                    int i = k - m + j;
                    if (i == 0 || j == 0) {
                        ans[i][j] = 0;
                    } else {
                        ans[i][j] = ans[i - 1][j - 1] | (1L << grid[i - 1][j - 1]);
                    }
                }
            }
            int pre = 0;
            for (int k = 1; k < m + n; k++) {
                int sj = Math.max(m - k, 0);
                int ej = Math.min(m + n - 1 - k, m - 1);
                for (int j = ej; j >= sj; j--) {
                    int i = k - m + j;
                    if (i == n - 1 || j == m - 1) {
                        pre = grid[i][j];
                        grid[i][j] = Long.bitCount(ans[i][j]);
                        ans[i][j] = 0;
                    } else {
                        int leftAbove = Long.bitCount(ans[i][j]);
                        ans[i][j] = ans[i + 1][j + 1] | (1L << pre);
                        int rightAbove = Long.bitCount(ans[i][j]);
                        pre = grid[i][j];
                        grid[i][j] = Math.abs(leftAbove - rightAbove);
                    }
                }
            }
            return grid;
        }
    }
}
