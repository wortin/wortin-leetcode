package com.wortin.leetcode;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00174 {
    class Solution {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            int[][] dp = new int[m][n];
            int life = dungeon[m - 1][n - 1];
            // 如果 life = +5，那么只要1点生命值就够了；如果life=-1，那么要保证至少2点生命值才能达到
            int minHP;
            if (life >= 0) {
                minHP = 1;
            } else {
                minHP = -life + 1;
            }
            dp[m - 1][n - 1] = minHP;

        }
    }
}
