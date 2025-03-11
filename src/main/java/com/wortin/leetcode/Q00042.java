package com.wortin.leetcode;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * <p>
 * 想到单调栈，或者扫描线
 * <p>
 * 先分析问题的本质，柱子接雨水，我如果从左往右遍历，是看柱子右边有没有低于我的柱子，如果低那么有可能接雨水，并且还需要遇到一个至少和我一样高的柱子，那么这个区间能接
 * 如果我右边等于或比我高，我不是左边界。
 * 单调栈是能够找出对每个元素，第一个比我大或小的元素。
 * 验证一下是否能解决这个问题，用递减栈
 * 我先简单一点，先求每个位置的右边界
 * [0,1,0,2,1,0,1,3,2,1,2,1]
 * 0入栈
 * 1入栈，要严格递减，所以0出栈，0第一个大于我的是1，这是0的右边界
 * 0入栈，是严格递减的
 * 2入栈，0、1出栈，0记录2，1记录2
 * 1入栈
 * 0入栈
 * 1入栈，0、1出栈，0记录1，1记录1
 * ...
 * 然后同样反方向遍历求左边界
 * 然后每个位置能接多少雨水？就是如果有左边界和右边界，那么 max(左边界,右边界)就是雨水的水平线，然后雨水的深度就是水平线-我自己的高度
 * 我想了实践了一下不行，因为单调栈求的是第一个大于我的，是第一个大于我的柱子，不是最远的那个大于我的柱子
 * 所以官解中用动态规划的思想求左边界是可以接受的，单调栈的思想还是要再看下官解的思路
 * 先来动态规划的思想，就是我一个柱子i，如果我右边的柱子比我低，那我的右边界是我；如果我右边没柱子，我的右边界是我；如果我右边大于等于我，我的右边界就是它的右边界，这里就是动规地拿到了最右边那个边界
 *
 *
 *
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00042 {
    public static void main(String[] args) {
        int trap = new Solution().trap(new int[]{4, 2, 0, 3, 2, 5});
        System.out.println(trap);
    }

    static class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int[] rb = new int[n];
            rb[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                if (height[i] > rb[i + 1]) {
                    rb[i] = height[i];
                } else {
                    rb[i] = rb[i + 1];
                }
            }
            int[] lb = new int[n];
            lb[0] = height[0];
            for (int i = 1; i < n; i++) {
                if (height[i] > lb[i - 1]) {
                    lb[i] = height[i];
                } else {
                    lb[i] = lb[i - 1];
                }
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (rb[i] > 0 && lb[i] > 0) {
                    res += Math.min(rb[i], lb[i]) - height[i];
                }
            }
            return res;
        }
    }
}
