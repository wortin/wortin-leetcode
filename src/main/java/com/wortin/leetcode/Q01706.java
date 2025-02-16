package com.wortin.leetcode;

import java.util.Arrays;

public class Q01706 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1},{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1}};
        int[] ball = new Solution().findBall(ints);
        System.out.println(Arrays.toString(ball));
    }

    static class Solution {
        public int[] findBall(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[] res = new int[m];
            for (int j = 0; j < m; j++) {
                int col = j;
                int row = 0;
                while (true) {
                    if (row >= n) {
                        res[j] = col;
                        break;
                    }
                    if (grid[row][col] == 1) {
                        if (col + 1 == m || grid[row][col + 1] == -1) {
                            res[j] = -1;
                            break;
                        } else {
                            col++;
                            row++;
                        }
                    } else {
                        if (col - 1 < 0 || grid[row][col - 1] == 1) {
                            res[j] = -1;
                            break;
                        } else {
                            col--;
                            row++;
                        }
                    }
                }
            }
            return res;
        }
    }
}
