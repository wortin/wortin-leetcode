package com.wortin.leetcode.contest437;

public class Q4 {
    public static void main(String[] args) {
//        int[][] ints = new int[][]{{2, 2, 1, 2, 2}, {2, 0, 2, 2, 0}, {2, 0, 1, 1, 0}, {1, 0, 2, 2, 2}, {2, 0, 0, 2, 2}};
//        int i = new Solution().lenOfVDiagonal(ints);
//        System.out.println(i);
//        int[][] ints = new int[][]{{0}};
//        int i = new Solution().lenOfVDiagonal(ints);
//        System.out.println(i);
        int[][] ints = new int[][]{{1, 1, 1, 2, 0, 0}, {0, 0, 0, 0, 1, 2}};
        int i = new Solution().lenOfVDiagonal(ints);
        System.out.println(i);

    }

    static class Solution {
        int[][] grid;
        int n;
        int m;
        static final int[][] dirs = new int[][]{{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        static final int[][] turnDirs = new int[][]{
                {-1, 1, 1},
                {1, 1, 2},
                {1, -1, 3},
                {-1, -1, 0}
        };

        public int lenOfVDiagonal(int[][] grid) {
            this.grid = grid;
            n = grid.length;
            m = grid[0].length;
            int max = 0;
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        res = 1;
                        for (int k = 0; k < dirs.length; k++) {
                            int[] dir = dirs[k];
                            int ni = i + dir[0];
                            int nj = j + dir[1];
                            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                                continue;
                            }
                            if (grid[ni][nj] == 2) {
                                max = Math.max(max, nextMaxLen(ni, nj, k, false));
                            }
                        }
                    }
                }
            }
            return max + res;
        }


        private int nextMaxLen(int i, int j, int initDirIndex, boolean turned) {
            int ni = i + dirs[initDirIndex][0];
            int nj = j + dirs[initDirIndex][1];
            // old dir
            int max = 0;
            if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                if ((grid[i][j] == 2 && grid[ni][nj] == 0) || (grid[i][j] == 0 && grid[ni][nj] == 2)) {
                    max = Math.max(max, nextMaxLen(ni, nj, initDirIndex, turned));
                }
            }
            // turn
            if (!turned) {
                int[] ttd = turnDirs[initDirIndex];
                int ti = i + ttd[0];
                int tj = j + ttd[1];
                if (ti >= 0 && ti < n && tj >= 0 && tj < m) {
                    if ((grid[i][j] == 2 && grid[ti][tj] == 0) || (grid[i][j] == 0 && grid[ti][tj] == 2)) {
                        max = Math.max(max, nextMaxLen(ti, tj, ttd[2], true));
                    }
                }
            }
            return 1 + max;
        }
    }
}
