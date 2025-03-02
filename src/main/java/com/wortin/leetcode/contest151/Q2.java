package com.wortin.leetcode.contest151;

public class Q2 {
    public static void main(String[] args) {
        int[] original = new int[]{17, 81};
        int[][] bounds = new int[][]{{36, 58}, {67, 110}};
        int i = new Solution().countArrays(original, bounds);
        System.out.println(i);
    }

    static class Solution {
        public int countArrays(int[] original, int[][] bounds) {
            int n = original.length;
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < n; i++) {
                int bd = bounds[i][1] - bounds[i][0];
                if (bd < min) {
                    min = bd;
                    minIdx = i;
                }
            }
            // int[] det = new int[n];
            // for(int i=0;i<n-1;i++) {
            //     det[i] = original[i+1]-original[i];
            // }
            int left = bounds[minIdx][0];
            int right = bounds[minIdx][1];
            int mid;
            int ml = left;
            int mr = right;
            boolean any = false;

            while (left <= right) {
                mid = (left + right) / 2;
                int s = mid;

                boolean ok = true;
                for (int j = minIdx + 1; j < n; j++) {
                    s = s + original[j] - original[j - 1];
                    if (s < bounds[j][0]) {
                        left = mid + 1;
                        ok = false;
                        break;
                    } else if (s > bounds[j][1]) {
                        right = mid - 1;
                        ok = false;
                        break;
                    }
                }
                if (!ok) {
                    continue;
                }
                s = mid;
                for (int j = minIdx - 1; j >= 0; j--) {
                    s = s + original[j] - original[j + 1];
                    if (s < bounds[j][0]) {
                        ok = false;
                        left = mid + 1;
                        break;
                    } else if (s > bounds[j][1]) {
                        right = mid - 1;
                        ok = false;
                        break;
                    }
                }
                if (!ok) {
                    continue;
                }
                ml = mid;
                any = true;
                right = mid - 1;
            }

            left = bounds[minIdx][0];
            right = bounds[minIdx][1];
            while (left <= right) {
                mid = (left + right) / 2;
                int s = mid;

                boolean ok = true;
                for (int j = minIdx + 1; j < n; j++) {
                    s = s + original[j] - original[j - 1];
                    if (s < bounds[j][0]) {
                        left = mid + 1;
                        ok = false;
                        break;
                    } else if (s > bounds[j][1]) {
                        right = mid - 1;
                        ok = false;
                        break;
                    }
                }
                if (!ok) {
                    continue;
                }
                s = mid;
                for (int j = minIdx - 1; j >= 0; j--) {
                    s = s + original[j] - original[j + 1];
                    if (s < bounds[j][0]) {
                        ok = false;
                        left = mid + 1;
                        break;
                    } else if (s > bounds[j][1]) {
                        right = mid - 1;
                        ok = false;
                        break;
                    }
                }
                if (!ok) {
                    continue;
                }
                mr = mid;
                any = true;
                left = mid + 1;
            }

            return any ? mr - ml + 1 : 0;
        }
    }
}
