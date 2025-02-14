package com.wortin.leetcode;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01742_2 {

    public static void main(String[] args) {
        int i = new Solution().countBalls(1, 10);
        System.out.println(i);
    }

    static class Solution {
        int[] map = new int[60];

        public int countBalls(int lowLimit, int highLimit) {
            for (int i = 0; i < 60; i++) {
                map[i] = 0;
            }
            for (int i = lowLimit; i <= highLimit; i++) {
                int n = cal(i);
                map[n]++;
            }
            int max = 0;
            for (int i = 0; i < 60; i++) {
                if (map[i] > max) max = map[i];
            }
            return max;
        }

        private int cal(int n) {
            int res = 0;
            while (n > 0) {
                res += n % 10;
                n /= 10;
            }
            return res;
        }
    }


}
