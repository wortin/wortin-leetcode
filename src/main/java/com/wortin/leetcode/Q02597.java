package com.wortin.leetcode;

/**
 * 给你一个由正整数组成的数组 nums 和一个 正 整数 k 。
 * <p>
 * 如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。
 * <p>
 * 返回数组 nums 中 非空 且 美丽 的子集数目。
 * <p>
 * nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 18
 * 1 <= nums[i], k <= 1000
 * <p>
 * 看题目，看数据规模，第一个想到动态规划，但是直接来dp[i][j]表示arr[0...i]任意差不等于j的子数组个数，不好推
 * 对arr[i],不选是好弄,就是dp[i-1][j]; 选的话,arr[i]要和前面任意差不等于j，好像不用二维了，也好像用不到dp[i-1]，这完全是一次新的处理
 * 也就是说一定要有arr[i]，首先arr[i]就是一个子集，然后二个元素的，...，然后到i+1个元素的，又变成2^(i+1)-1个，就是前面任意组合再加个arr[i]，然后判断有没有打破差等于j
 * 如果前面arr[0...i-1]有s个子集，差不等于k，然后我再加个arr[i]，我去验证arr[i]+k、arr[i]-k在不在里面就好了
 * 所以我如果是维护一个List<Set<>>的话，就是不断追加
 * 0: arr[0]
 * 1: arr[0]arr[1]            arr[1]
 * 2: arr[0]arr[2]
 * arr[0]arr[1]arr[2]      arr[1]arr[2]      arr[2]
 * 3: 8个，前面7个加arr[3] 再来一个arr[3]
 * 那我这样的算法复杂度是多少？就是去生成了2^n次呗
 * 然后看看官方的解答，首先差不等于k，差等于k的，一定是n%k同余且相邻的，如果不同余差一定不等于k
 * 所以它想到按照余k分组，每一组可以取0个，或者多个。
 * 每组中，相邻的不能取，相同的可以取多个。假设k=2 问题转化成 224466 这样的一组能够取多少
 * 对2来说，4不能取，2能取0..2，6能取0..2 总共有3x3-1=8个，/ 6 66 2 26 266 22 226 2266，去掉空就是8个
 * 对4来说，26不能取，就是0..2，取3-1=2个
 * 对6来说，啊，把不是算重复了啊
 * 官方到这里给了一个dp思路，就是224466看出arr=246，分别计数222
 * dp[i][0]表示arr[0...i]中不选i的子集数，dp[i][1]表示arr[0...i][1]中选i的子集数
 * dp[i][0]=dp[i-1][0]+dp[i-1][1]
 * dp[i][1]=如果前面是相邻的dp[i-1][0]*(2^count[i]-1)
 * 如果前面是不相邻的(dp[i-1][0]+dp[i-1][1])*(2^count[i]-1)
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
class Q02597 {
    class Solution {
        public int beautifulSubsets(int[] nums, int k) {
            int n = nums.length;
            int[] count = new int[1001];
            // 先分类，k类，每类最多n个
            int[][] g = new int[k][n];
            for (int i = 0; i < k; i++) {
                g[i] = new int[n];
            }
            int[] gl = new int[k];
            for (int i = 0; i < n; i++) {
                int v = nums[i];
                count[v]++;
                int ged = v % k;
                if (count[v] == 1) {
                    g[ged][gl[ged]] = v;
                    gl[ged]++;
                }
            }
            int res = 1;
            int[][] dp = new int[n][2];
            for (int i = 0; i < k; i++) {
                if (gl[i] == 0) {
                    continue;
                }
                int[] gvs = g[i];

            }
            return res;
        }
    }
}