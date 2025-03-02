package com.wortin.leetcode;

/**
 * 刚才是从上往下推，递归的走法
 * 看了人家解答，有一个top1，更好更快代码量更少
 * 学习下人家的思路。然后看官解，就是2个，第一个是记忆化搜索，就是我刚才那个，但是官解更少代码量；第二个就是更好的dp
 * 先来看第一个：好像官解思路和我是一样的，稍微细节不一样，比如初始化dp[coin]不需要，因为dp[coin]->dp[0]+1
 * 第一个解法，官方都不叫动态规划，叫记忆化搜索，第二个解法才叫动态规划
 * 因为第一个解法不是通过子问题的最优解得到的，dp[i] = dp[i-c]+1，需要先计算出dp[i-c]
 * 再来下一个：采用自下而上的思路。
 * dp[0] = 0 组成0元需要0个硬币
 * dp[1] = 1 组成1元需要 min{dp[1-c]+1} = 1 个硬币 这里 dp[1-c]只要有就行，全无就-1，1-c负也-1
 * dp[2] = 1 组成2元需要 min{dp[2-c]+1} = 1 个硬币
 * 根据这个思路，重写一个
 * 对比官解，官解不赋值-1，直接按默认的MAX不管
 * 最后是 dp[amount]>amount的话，就是-1
 * 学到一个技巧，如果dp中比较min的，dp初始值就设最大，然后 dp[i] = min(dp[i], cur) 就好了
 */
public class Q00322_2 {
    public static void main(String[] args) {
        int i = new Solution().coinChange(new int[]{186, 419, 83, 408}, 6249);
        System.out.println(i);
    }

    static class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                int min = Integer.MAX_VALUE;
                for (int c : coins) {
                    if (i - c >= 0) {
                        if (dp[i - c] >= 0) {
                            min = Math.min(dp[i - c] + 1, min);
                        }
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    dp[i] = min;
                } else {
                    dp[i] = -1;
                }
            }
            return dp[amount];
        }
    }

}
