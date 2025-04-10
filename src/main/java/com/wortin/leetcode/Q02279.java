package com.wortin.leetcode;

/**
 * 装满石头的背包的最大背包个数
 * 现有编号从 0 到 n - 1 的 n 个背包。给你两个下标从 0 开始的整数数组 capacity 和 rocks 。第 i 个背包最大可以装 capacity[i] 块石头，当前已经装了 rocks[i] 块石头。另给你一个整数 additionalRocks ，表示你可以放置的额外石头数量，石头可以往 任意 背包中放置。
 * <p>
 * 请你将额外的石头放入一些背包中，并返回放置后装满石头的背包的 最大 数量。
 * 提示：
 * <p>
 * n == capacity.length == rocks.length
 * 1 <= n <= 5 * 104
 * 1 <= capacity[i] <= 109
 * 0 <= rocks[i] <= capacity[i]
 * 1 <= additionalRocks <= 109
 * <p>
 * 首先想到dp,因为是背包问题嘛，但是一看数据范围好像很大，先不管，硬着头皮上
 * dp[i][j] 表示 0...i的背包，在给到j个石头下，装满的最大背包个数
 * dp[i][j] 对i个背包来说，装满或者不装满
 * 装满：= dp[i-1][j-diff[i]] + 1
 * 不装满：= dp[i-1][j]
 * 考虑优化的点，求dp[i][j]的时候，j不用都给它填上，比如dp[0][0]=0 dp[0][1]=0...dp[0][j0]=max dp[0][j0+n]=max 如果大于j0后面不用给数据了 直接max
 * 感觉记住分界点就好了，就是dp[0]的时候，分界点就是diff[0] 如果j<diff[0]那就是0，否则是1
 * dp[1]的时候，不装满1，就是dp[0][ar] 就是看 j=ar   = 0/1
 * 装满1（够不够装满哦，可能都不够），就是dp[0][ar-diff[1]]  就是看 j=ar-diff[1]  0/1/2
 * HashMap<Integer,HashMap<Integer,Integer>>
 * <p>
 * 先不优化了，先用最笨的来一下
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02279 {
    class Solution {
        int n;
        int[][] dp;
        int[] mj;
        int[] diff;

        public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
            n = capacity.length;
            dp = new int[n][additionalRocks + 1];
            mj = new int[n];
            diff = new int[n];
            for (int i = 0; i < n; i++) {
                diff[i] = capacity[i] - rocks[i];
            }
            for (int j = 0; j <= additionalRocks; j++) {
                dp[0][j] = j >= diff[0] ? 1 : 0;
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= additionalRocks; j++) {
                    int fillMe = 0;
                    int notFillMe = 0;
                    if (j >= diff[i]) {
                        fillMe = 1 + dp[i - 1][j - diff[i]];
                    } else {
                        notFillMe = dp[i - 1][j];
                    }
                    dp[i][j] = Math.max(fillMe, notFillMe);
                }
            }
            return dp[n - 1][additionalRocks];
        }
    }
}
