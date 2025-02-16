package com.wortin.leetcode;

import java.util.Arrays;

public class Q01706_2 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{{1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}};
        int[] ball = new Solution().findBall(ints);
        System.out.println(Arrays.toString(ball));
    }

    static class Solution {
        public int[] findBall(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[] res = new int[m];
            for (int j = 0; j < m; j++) {
                res[j] = ans(grid, j, n, m);
            }
            return res;
        }

        private int ans(int[][] grid, int j, int n, int m) {
            int col = j;
            for (int i = 0; i < n; i++) {
                int nCol = col + grid[i][col];
                if (nCol < 0 || nCol == m || grid[i][nCol] != grid[i][col]) {
                    return -1;
                } else {
                    col = nCol;
                }
            }
            return col;
        }
    }
}
