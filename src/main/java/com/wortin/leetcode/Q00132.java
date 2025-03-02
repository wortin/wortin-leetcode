package com.wortin.leetcode;

import java.util.Arrays;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回符合要求的 最少分割次数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：s = "ab"
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 * <p>
 * 现在已经有直觉了，2000就一定是dp了
 * 然后之前还做一题，如何判断s[i,j]是回文用p[i][j]的
 * 然后一个月前看这题题解还比较痛苦，今天堪堪自己回忆出来怎么做，dp[i] = min{ dp[k]+ 1 when 0<=k<=i-1 and p[k+1][i]; 0 when k=-1 and p[0][i]}
 */
public class Q00132 {
    class Solution {
        public int minCut(String s) {
            int n = s.length();
            boolean[][] p = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(p[i], true);
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) != s.charAt(j) || !p[i + 1][j - 1]) {
                        p[i][j] = false;
                    }
                }
            }
            int[] dp = new int[n];
            dp[0] = 0;
            for (int i = 1; i < n; i++) {
                if (p[0][i]) {
                    dp[i] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k >= 0; k--) {
                    if (p[k + 1][i]) {
                        min = Math.min(dp[k] + 1, min);
                    }
                }
                dp[i] = min;
            }
            return dp[n - 1];
        }
    }
}
