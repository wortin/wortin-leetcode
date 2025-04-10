package com.wortin.leetcode;

/**
 * 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * <p>
 * <p>
 * 这题感觉就是动态规划了
 * <p>
 * dp[i][j]表示s[i...j]是否是有效括号(0<=i<=j<n)，那么
 * 如果 s[i]=')' 肯定不是
 * 如果 s[j]='(' 肯定不是
 * 如果 s[i]='(' && s[j]=')' 如果 dp[i+1][j-1] 是，那么是
 * 或者如果存在i<=k<j s.t. dp[i][k-1] && dp[k][j]
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00032 {

    class Solution {
        public int longestValidParentheses(String s) {
            return 0;
        }
    }
}
