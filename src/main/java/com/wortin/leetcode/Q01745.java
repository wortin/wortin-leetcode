package com.wortin.leetcode;

/**
 * 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
 * <p>
 * 当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
 * <p>
 * 提示：
 * <p>
 * 3 <= s.length <= 2000
 * s只包含小写英文字母。
 * <p>
 * 这题如果简单想，我可以知道isPalindrome[i][j]拿到s[i][j]是否是回文
 * 然后我只要两个for循环就做完
 * 那么判断是否可以3个非空，是昨天那题的简化版本
 * 就是我 dp[r][k] 表示s[0][r]是否有k个非空回文 那么我就从r往左遍历，找到第一个回文后，看dp[i][k-1]是否成立
 * <p>
 * 现在是总结是这样，自顶向下是递归实现，自底向上是迭代实现，但是其实都是动态规划的思想，就是拆分成子问题
 * <p>
 * 官解中判断s[i][j]回文的代码是先把p[][]都初始化True，这样p[i][j]对于i>=j都是true的，然后因为i要往右，j要往左，所以迭代的时候，
 * i要从最后一个开始往左，j要从i开始往右，这样自底向上
 * <p>
 * 然后分割的时候用迭代，思路是dp[r][k]你r是往左的，k是减少的，所以迭代的时候
 * r是要往右，所以r就是从0开始往右，k就是从1开始变大
 * 那么dp[0][1]就是0号字符份1份，因为p[0][0]是回文，就可以；然后dp[i][1] = p[0][i]
 * 然后k=2，现在看dp[i][2]，dp[0][2]不行的不够分2份，dp[1][2]肯定可以，dp[2][2]就要从2下标开始份，往回看dp[1][1]&p[2][2] dp[0][1]&p[1][2]
 * ....
 * dp优化成一维还没想想清楚
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01745 {
    public static void main(String[] args) {
        System.out.println(new Solution().checkPartitioning("abcbdd"));
    }

    static class Solution {
        boolean[][] p;
        int[][] dp;
        int n;
        String s;

        public boolean checkPartitioning(String s) {
            this.s = s;
            this.n = s.length();
            this.p = new boolean[n][n];
            this.dp = new int[n][4];
            initIsPalindrome();
            return cal(n - 1, 3) == 1;
        }

        private int cal(int r, int k) {
            // 如果要求剩下是一个回文，那么就是看剩下是不是回文
            if (k == 1 && r >= 0) {
                return p[0][r] ? 1 : -1;
            }
            if (dp[r][k] != 0) {
                return dp[r][k];
            }
            // 否则，我先从r往左遍历，找到第一个回文 [i,r]
            // i不能太小，必须让剩下的字符长度大于等于k-1
            // 剩下字符长度就是i，所以i>=k-1
            int res = -1;
            for (int i = r; i >= k - 1; i--) {
                if (p[i][r]) {
                    // 调下一层，检查i-1是否一定>=0,k-1>=1?
                    // k 只能是2进来，所以i一定>=0,所以一定被拦到
                    int sub = cal(i - 1, k - 1);
                    if (sub == 1) {
                        res = 1;
                        break;
                    }
                }
                dp[r][k] = res;
            }
            return res;
        }

        private void initIsPalindrome() {
            // 单个字符一定是回文
            for (int i = 0; i < n; i++) {
                p[i][i] = true;
            }
            // 我现在从2个2个来，然后就是l个l个来
            for (int l = 1; l < n; l++) {
                for (int i = 0; i + l < n; i++) {
                    boolean internal;
                    if (i + 1 >= i + l - 1) {
                        internal = true;
                    } else {
                        internal = p[i + 1][i + l - 1];
                    }
                    if (internal) {
                        p[i][i + l] = s.charAt(i) == s.charAt(i + l);
                    } else {
                        p[i][i + l] = false;
                    }
                }
            }
        }
    }
}
