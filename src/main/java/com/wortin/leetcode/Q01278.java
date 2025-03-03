package com.wortin.leetcode;

import java.util.Arrays;

/**
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。
 * <p>
 * 请你按下面的要求分割字符串：
 * <p>
 * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 * 请返回以这种方式分割字符串所需修改的最少字符数。
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= s.length <= 100
 * s 中只含有小写英文字母。
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01278 {
    public static void main(String[] args) {
//        int i = new Solution().palindromePartition("abc", 2);
//        System.out.println(i);
        int i = new Solution().palindromePartition("aabbc", 3);
        System.out.println(i);
    }

    static class Solution {
        /**
         * dp[i][j] 表示对字符串s[0,j](包含0和j)分成i份回文子串的最少修改次数
         */
        int[][] dp;
        int n;
        int k;
        String s;
        /**
         * modCost[i][j]表示将s[i,j]修改成回文需要的修改次数
         */
        int[][] modCost;

        public int palindromePartition(String s, int k) {
            this.s = s;
            this.k = k;
            this.n = s.length();
            dp = new int[k + 1][n];
            modCost = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(modCost[i], -1);
            }
            for (int i = 0; i <= k; i++) {
                Arrays.fill(dp[i], -1);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    calModCost(i, j);
                }
            }
            return doPalindromePartition(k, n - 1);
        }

        private int calModCost(int i, int j) {
            if (modCost[i][j] != -1) {
                return modCost[i][j];
            }
            // 如果只有一个字符，已经是回文，不用改
            if (i >= j) {
                modCost[i][j] = 0;
                return 0;
            }
            int cost = calModCost(i + 1, j - 1);
            cost += s.charAt(i) == s.charAt(j) ? 0 : 1;
            modCost[i][j] = cost;
            return cost;
        }

        /**
         * 我要把入参的边界处理好
         * r == 0 表示就剩一个字符,r < 0 表示已经没有字符了
         * d == 0 表示份成0份，没有意义，d == 1 表示份成1份，如果r>=0就有意义，否则r<0没有意义
         * 所以 d>=1, 并且r+1>=d,所以r>=0
         *
         * @param r 当前处理s[0,r]
         * @param d 当前处理s[0,r]分成d份
         */
        private int doPalindromePartition(int d, int r) {
            // 切1份，就是直接不切了，就是直接返回这一份需要修成回文的次数
            if (d == 1 && r >= 0) {
                dp[d][r] = modCost[0][r];
                return dp[d][r];
            }
            if (dp[d][r] != -1) {
                return dp[d][r];
            }

            // 对我来说，我现在r=0,d=1是可以的，是我能处理的边界
            // 但是我下一个迭代，-1，0是我不能处理的，我可以提前避免

            // 现在我要分成d份，我先去1份，剩下d-1份让下一个迭代处理
            // 我这1份从哪里开始切，从 i 到 r还是切，就是s[i,r]（包含i和r）
            // 我从i切，切了r-i个字符，剩下i个字符，剩下的字符要必须大于等于d-1，才能够继续切

            int min = Integer.MAX_VALUE;
            for (int i = r; i + 1 >= d; i--) {
                int res = doPalindromePartition(d - 1, i - 1);
                min = Math.min(min, res + modCost[i][r]);
            }
            dp[d][r] = min;
            return min;
        }
    }

}
