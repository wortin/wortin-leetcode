package com.wortin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * <p>
 * 从s的长度在16的数量级，大胆猜测是要dp
 * 或者用回溯（暴力）
 * 先考虑最朴素的暴力，分割子串，对长度n的字符串，切0刀，切1刀，切n-1刀，得到1、C(n-1,1)、C(n-1,2)、...C(n-1,n-1) 是O(2^n)数量级
 * 不行！
 * 然后考虑指针扫的思路？
 * 用dp了，dp[i]表示s[0,i]的分割方案数，那么dp[i]就是：
 * dp[i-1]个（最后一个字符单独一组，和前面的方案）
 * dp[i-2]个 （最后2个字符单独一组，和前面的方案，前提是最后2个字符是回文）
 * ...
 * dp[-1] s[0,i]是回文
 * 这样是n^2的速度
 * 现在是要给他搞个是否是回文的判断方法
 * <p>
 * 啊，他现在是要求所有分割的方案，不是个数啊！那还是一样的，给他记下来
 */
public class Q00131 {

    class Solution {
        public List<List<String>> partition(String s) {
            int n = s.length();
            boolean[][] isPalindrome = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(isPalindrome[i], true);
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1];
                }
            }

            List[] dp = new List[s.length()];
            List<List<String>> dp0 = new ArrayList<>();
            dp0.add(Collections.singletonList(s.substring(0, 1)));
            dp[0] = dp0;
            for (int i = 1; i < s.length(); i++) {
                List<List<String>> dpi = new ArrayList<>();
                for (int k = i; k >= 0; k--) {
                    if (isPalindrome[k][i]) {
                        if (k > 0) {
                            List<List<String>> pres = dp[k - 1];
                            for (List<String> pre : pres) {
                                List<String> cur = new ArrayList<>(pre);
                                cur.add(s.substring(k, i + 1));
                                dpi.add(cur);
                            }
                        } else {
                            dpi.add(Collections.singletonList(s.substring(k, i + 1)));
                        }
                    }
                }
                dp[i] = dpi;
            }
            return dp[s.length() - 1];
        }
    }
}
