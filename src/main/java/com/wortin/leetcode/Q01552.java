package com.wortin.leetcode;

import java.util.Arrays;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01552 {
    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1, 1000000000};
        int i = new Solution().maxDistance(nums, 2);
        System.out.println(i);
    }

    static class Solution {

        private int[] position;
        private int m;

        public int maxDistance(int[] position, int m) {
            this.position = position;
            this.m = m;
            // 从小到大升序排序
            Arrays.sort(position);
            int n = position.length;
            int left = 1, right = position[n - 1] - position[0];
            int res = 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (isValid(mid)) {
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return res;
        }

        private boolean isValid(int val) {
            int count = 1;
            int prev = 0;
            for (int i = 1; i < position.length && count < m; i++) {
                if (position[i] - position[prev] >= val) {
                    prev = i;
                    count++;
                }
            }
            return count == m;
        }
    }
}
