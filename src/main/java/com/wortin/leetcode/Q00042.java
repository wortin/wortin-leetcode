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
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00042 {
    public static void main(String[] args) {
        int trap = new Solution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(trap);
    }

    static class Solution {
        public int trap(int[] height) {
            int n = height.length;
            // 寻找右边界
            int[] rb = new int[n];
            int[] stack = new int[n];
            int top = -1;
            for (int i = 0; i < n; i++) {
                if (top == -1) {
                    top++;
                    stack[top] = i;
                    continue;
                }
                int topIdx = stack[top];
                int topH = height[topIdx];
                int curH = height[i];
                // 右边界必须比我大
                if (curH <= topH) {
                    top++;
                    stack[top] = i;
                    continue;
                }
                while (curH > topH) {
                    // 你的右边界就是我，你出栈
                    rb[topIdx] = i;
                    top--;
                    if (top >= 0) {
                        topIdx = stack[top];
                        topH = height[topIdx];
                    } else {
                        break;
                    }
                }
                top++;
                stack[top] = i;
            }
            // 寻找左边界
            int[] lb = new int[n];
            top = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (top == -1) {
                    top++;
                    stack[top] = i;
                    continue;
                }
                int topIdx = stack[top];
                int topH = height[topIdx];
                int curH = height[i];
                // 左边界必须比我大
                if (curH <= topH) {
                    top++;
                    stack[top] = i;
                    continue;
                }
                while (curH > topH) {
                    // 你的左边界就是我，你出栈
                    rb[topIdx] = i;
                    top--;
                    if (top >= 0) {
                        topIdx = stack[top];
                        topH = height[topIdx];
                    } else {
                        break;
                    }
                }
                top++;
                stack[top] = i;
            }
            int res = 0;
            // 累计雨水
            for (int i = 0; i < n; i++) {
                if (lb[i] > 0 && rb[i] > 0) {
                    res += Math.max(lb[i], rb[i]) - height[i];
                }
            }
            return res;
        }
    }
}
