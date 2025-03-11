package com.wortin.leetcode;

/**
 * 我本来以为官解是用单调栈求右边最大柱，其实不是，它还是求最近一个大于我的柱子
 * 这样的话，它不是去求每个柱子上面能顶多少雨水了，而是横这来看，当我遇到第一个比我高的柱子时，左右两边比我高的柱子夹住我的情况下，我这个区间这个限高下能接多少
 * 它是横着切条来累加雨水的，不是竖着切条按每根柱子上的雨水累加来算的。
 * 就是每次都算一个凹槽，至于凹槽里面的凹槽，已经被前面的i算好相当于填平了。
 * <p>
 * 它是用到了拓展的单调栈，要看凹槽的话，除了top指针，还需要一个second指针，这样second、top、i就形成凹槽的左中右柱子
 * 现在用top表示位于栈顶的柱子下标，second表示位于栈顶后一个柱子下标
 * 有：height[second] >= height[top]，那么如果 height[i] > height[top]，那么就有凹槽，至于height[second]=height[top]是就是接不住雨水的意思，因为相当于没有形成凹槽
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00042_2 {
    public static void main(String[] args) {
        int trap = new Solution().trap(new int[]{4, 2, 0, 3, 2, 5});
        System.out.println(trap);
    }

    static class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int res = 0;
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
                if (curH <= topH) {
                    top++;
                    stack[top] = i;
                    continue;
                }
                while (curH > topH) {
                    if (top > 0) {
                        res += (i - stack[top - 1] - 1) * (Math.min(height[stack[top - 1]], curH) - topH);
                    }
                    top--;
                    if (top == -1) {
                        break;
                    }
                    topIdx = stack[top];
                    topH = height[topIdx];
                }
                top++;
                stack[top] = i;
            }
            return res;
        }
    }
}
