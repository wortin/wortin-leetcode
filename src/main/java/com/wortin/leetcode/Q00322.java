package com.wortin.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * <p>
 * 前几天看到这题，一开始以为是dp，看了官方解答好像不是用的dp，似乎是另一种方法
 * 今天先自己再想想。记得之前还问豆包，是否有用数学定理比如数论相关来求解的，豆包说是没有的
 * <p>
 * p1*x1+p2*x2+...+pn*xn=amount, 求 min p1+p2+...+pn, 0<=pi
 * <p>
 * 想起题解有人用贪心的，每次拿最大的，不行就回溯，这个其实就变成了暴力解了，会超时的
 * 我还是回到dp来看看，他看起来和背包有点像的，背包是这样的问题：
 * p1*V1+p2*V2+...+pn*Vn<=V, 求 max p1*v1+p2*v2+...+pn*vn, pi=0 or 1
 * <p>
 * 我有点想起来了，amount-5,amount-2,amount-1, 找一个最小的 似乎是这个思路
 */
public class Q00322 {
    public static void main(String[] args) {
        int i = new Solution().coinChange(new int[]{186, 419, 83, 408}, 6249);
        System.out.println(i);
    }

    static class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            dp[0] = 0;
            Arrays.sort(coins);
            for (int c : coins) {
                if (c <= amount) {
                    dp[c] = 1;
                }
            }
            return cal(coins, amount, dp);
        }

        private int cal(int[] coins, int amount, int[] dp) {
            if (amount < 0) {
                return -1;
            }
            if (amount == 0) {
                return 0;
            }
            if (dp[amount] != 0) {
                return dp[amount];
            }
            boolean ok = false;
            int min = Integer.MAX_VALUE;
            for (int i = coins.length - 1; i >= 0; i--) {
                int res = cal(coins, amount - coins[i], dp);
                if (res != -1) {
                    ok = true;
                    min = Math.min(min, res + 1);
                }
            }
            if (ok) {
                dp[amount] = min;
                return min;
            }
            dp[amount] = -1;
            return -1;
        }
    }

}
