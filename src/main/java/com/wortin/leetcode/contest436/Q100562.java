package com.wortin.leetcode.contest436;

import java.util.Arrays;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q100562 {
    public static void main(String[] args) {
//        int[][] ints = {{1, 7, 3}, {9, 8, 2}, {4, 5, 6}};
//        int[][] res = new Solution().sortMatrix(ints);
//        System.out.println(Arrays.deepToString(res));
//        int[][] ints = {{0, 1}, {1, 2}};
//        int[][] res = new Solution().sortMatrix(ints);
//        System.out.println(Arrays.deepToString(res));
        int[][] ints = {{4}};
        int[][] res = new Solution().sortMatrix(ints);
        System.out.println(Arrays.deepToString(res));
    }

    static class Solution {
        public int[][] sortMatrix(int[][] grid) {
            int n = grid.length;
            for (int k = 0; k < n - 1; k++) {
                int[] nums = new int[n - k];

                for (int i = 0; i < n - k; i++) {
                    nums[i] = grid[k + i][i];
                }

                Arrays.sort(nums);
                for (int i = 0; i < n - k; i++) {
                    grid[k + i][i] = nums[n - k - 1 - i];
                }
            }
            for (int k = 1; k < n - 1; k++) {
                int[] nums = new int[n - k];
                for (int i = 0; i < n - k; i++) {
                    nums[i] = grid[i][k + i];
                }
                Arrays.sort(nums);
                for (int i = 0; i < n - k; i++) {
                    grid[i][k + i] = nums[i];
                }
            }

            return grid;
        }
    }
}
