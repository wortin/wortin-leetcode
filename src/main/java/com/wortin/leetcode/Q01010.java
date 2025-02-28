package com.wortin.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Q01010 {
    public static void main(String[] args) {
        int i = new Solution().numPairsDivisibleBy60(new int[]{60, 60, 60});
        System.out.println(i);
    }

    static class Solution {
        private static final int[] divs = new int[]{60, 120, 180, 240, 300, 360, 420, 480, 540, 600, 660, 720, 780, 840, 900, 960};

        public int numPairsDivisibleBy60(int[] time) {
            int[] timeArr = new int[501];
            for (int t : time) {
                timeArr[t]++;
            }
            int count = 0;
            for (int t : time) {
                timeArr[t]--;
                for (int d : divs) {
                    int diff = d - t;
                    if (diff > 500) {
                        break;
                    }
                    if (diff > 0 && timeArr[diff] > 0) {
                        count += timeArr[diff];
                    }
                }
            }
            return count;
        }
    }
}
