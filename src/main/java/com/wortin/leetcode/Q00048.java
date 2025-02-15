package com.wortin.leetcode;

import java.util.Arrays;

public class Q00048 {
    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        new Solution().rotate(matrix);
//        System.out.println(Arrays.deepToString(matrix));
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        new Solution().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    static class Solution {
        public void rotate(int[][] matrix) {
            int length = matrix.length;
            if (length == 1) {
                return;
            }
            for (int k = 0; k < length / 2; k++) {
                rotate(k, k, length - 2 * k, matrix);
            }
        }

        private void rotate(int i, int j, int width, int[][] matrix) {
            if (width <= 1) {
                return;
            }
            for (int w = 0; w < width - 1; w++) {
                int i1 = i;
                int j1 = j + w;
                int i2 = i1 + w;
                int j2 = j + width - 1;
                int i3 = i + width - 1;
                int j3 = j + width - 1 - w;
                int i4 = i + width - 1 - w;
                int j4 = j;

                int v2 = matrix[i2][j2];
                matrix[i2][j2] = matrix[i1][j1];

                int v3 = matrix[i3][j3];
                matrix[i3][j3] = v2;

                int v4 = matrix[i4][j4];
                matrix[i4][j4] = v3;

                matrix[i1][j1] = v4;
            }
        }
    }
}
