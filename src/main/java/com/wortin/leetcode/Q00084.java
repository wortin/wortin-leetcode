package com.wortin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 提示：
 * <p>
 * 1 <= heights.length <=10^5
 * 0 <= heights[i] <= 10^4
 *
 * <p>
 * // 数量和高度都不适合dp
 * // 先朴素思考：遍历每个矩形，从这个矩形的左下角扫出矩形
 * // 1. 尽可能往上找，
 * // 2. 或者尽可能往右找
 * // 3. 上述两者都可能不是最大的
 * // 那就是最高，就是自己的高度，宽就是右边高度大于等于我的矩形个数
 * // 然后是次高，那就是往我旁边去找，第一个比我最高小的高度
 * // 然后是次次高，直到找不到遇到高度0
 * // 我们按高度为0的矩形作为分割组的标志，分组来算
 * // 如果不优化，复杂度是n^2的
 * // 换一种思路
 * // 短板原理，给定下标从x = [i,j] 的底，高度最小的矩形决定了最大了面积
 * // 如果包含最短的矩形t，那么最大面积是个数*高度
 * // 如果包含次短的矩形t，宽度被夹在高度小于我的矩形里面
 * // 好像有一种算法，叫做扫 分切 用来求面积的
 * // 可以考虑用二分法，去验证最大面积是否可能存在 0-10^9
 * <p>
 * // 这个题目是用单调栈，好题目，目前是你的知识盲区，要好好看
 * // 单调栈是说，这个栈内的元素，从栈底到栈顶是单调的，递增或递减。
 * // 比如，对于单调递减栈来说，每次有新元素e入栈的时候：
 * //     如果栈为空，直接入栈
 * //     如果栈不空，下面循环：如果e>top,那么top出栈，直到e<=top,那么e入栈
 * // 比如，我们求一个数组中每个元素的下一个更大元素，可以用单调递减栈
 * // 数组[2,1,2,4,3]
 * // 下标0(元素2): 栈=[0(2)]
 * // 下标1(元素1): 栈=[0(2),1(1)]
 * // 下标2(元素2): 此时栈顶出栈然后才能放进去，栈=[0(2),2(2)]
 * //             1(1)出栈，说明对1(1)来说，已经找到一个比它更大的元素了，就是2(2)，所以记录对1(1)来说的更大元素为2(2)
 * // 下标3(元素4): 此时栈顶要出栈然后才能放进去，栈=[3(4)]
 * //             0(2),2(2)，说明对它们来说，已经找到一个比它更大的元素了，就是3(4)
 * // 下标4(元素3): 栈=[3(4),4(3)]
 * // 好，遍历完了，此时栈里还剩下元素，说明对3(4)来说，没有下一个更大的，同理，对4(3)来说，也没有下一个更大的
 * <p>
 * // [2,1,5,6,2,3]
 * // 那么本题，对任意一楼，往左往右拓展，本质是求，往右找第一个小于我高度的，往左找第一个小于我高度的
 * // 这样利用递增栈，单调栈顺序遍历一遍拿到每栋楼的右边界，再倒序遍历拿到左边界
 * // 事实上，可以只遍历一遍，也就是楼i进栈时，弹出的楼j确定了自己的右边界是楼i，而楼i确定了栈中剩下的楼k是楼i的左边界。
 *
 * </p>
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00084 {
    public static void main(String[] args) {
//        int i = new Solution().largestRectangleArea(new int[]{2,1,2});
//        System.out.println(i);
//        int i = new Solution().largestRectangleArea(new int[]{4, 2, 0, 3, 2, 5});
//        System.out.println(i);
        int i = new Solution().largestRectangleArea(new int[]{3, 6, 5, 7, 4, 8, 1, 0});
        System.out.println(i);
    }


    static class Solution {
        public int largestRectangleArea(int[] heights) {
            int[] left = new int[heights.length];
            int[] right = new int[heights.length];
            List<Integer> stack = new ArrayList<>();
            for (int i = 0; i < heights.length; i++) {
                int h = heights[i];
                if (stack.isEmpty()) {
                    stack.add(i);
                    // 入空栈，自己就是自己的左边界
                    left[i] = i;
                } else {
                    int top = stack.get(stack.size() - 1);
                    // 是递增的，入栈就好
                    if (heights[top] <= h) {
                        stack.add(i);
                        if (heights[top] == h) {
                            // 如果我们高度一样，你的左边界，就是我的左边界
                            left[i] = left[i - 1];
                        } else {
                            // 如果我比你高，我的左边界就是我
                            left[i] = i;
                        }
                    }
                    // 不是递增的了，栈顶元素比我大，那要把里面比我大的弹出栈
                    else {
                        int lastPopped = -1;
                        while (heights[top] > h) {
                            // 楼top的右边界就是i
                            right[top] = i;
                            lastPopped = top;
                            stack.remove(stack.size() - 1);
                            if (stack.isEmpty()) {
                                break;
                            }
                            top = stack.get(stack.size() - 1);
                        }
                        if (stack.isEmpty()) {
                            stack.add(i);
                            // 左边界是我最后一个弹出的楼
                            left[i] = left[lastPopped];
                        } else {
                            stack.add(i);
                            if (heights[top] == h) {
                                // 如果我们高度一样，你的左边界，就是我的左边界
                                left[i] = left[i - 1];
                            } else {
                                // 如果我比你高，我的左边界就是我
                                left[i] = left[lastPopped];
                            }
                        }
                    }
                }
            }

            // 最后处理一下栈里剩下的元素，它们没有遇到第一个比自己小的楼，那么它们的右边界就是length-1
            for (int index : stack) {
                right[index] = heights.length;
            }
            int max = -1;
            for (int i = 0; i < heights.length; i++) {
                max = Math.max(max, heights[i] * (right[i] - left[i]));
            }
            return max;
        }
    }
}