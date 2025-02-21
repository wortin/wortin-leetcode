package com.wortin.leetcode.contest437;

import java.util.Arrays;

public class Q2 {
    public static void main(String[] args) {
        long l = new Solution().maxWeight(new int[]{1, 2, 3, 4});
        System.out.println(l);
    }

    static class Solution {
        public long maxWeight(int[] pizzas) {
            int n = pizzas.length;
            int days = n / 4;
            Arrays.sort(pizzas);
            int oddDay = (days + 1) / 2;
            int ouDay = days - oddDay;
            int res = 0;
            for (int i = n - 1; i >= n - oddDay; i--) {
                res += pizzas[i];
            }
            for (int i = n - oddDay - 2; i >= (n - oddDay) - 2 * ouDay; i -= 2) {
                res += pizzas[i];
            }
            return res;
        }
    }
}
