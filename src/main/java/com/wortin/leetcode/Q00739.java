package com.wortin.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * <p>
 * 这个问题我用单调栈解决，单调栈是一个栈，java中可以使用Deque,java是推荐使用Deque，不使用Stack，因为Deque是接口，有多个实现类可以选择，
 * 例如ArrayDeque，LinkedList,性能上比Stack好，在不考虑线程安全的情况下，因为Stack基于Vector，性能上比ArrayDeque差，而且Deque支持双端操作等更多功能
 * 但是这里我自己手写一个栈可能更快？
 * <p>
 * // 单调栈的思路是，栈始终要保持单调，当一个元素入栈时，栈顶元素和我不单调了，要把栈里面所有不单调的清空出来
 * // 我现在要找下一个比我大的元素，吐出来的元素说他们找到比我大的元素了，所以我是维护一个单调递减的栈
 * // 比如 [30,40,50,60]
 * // 30入栈
 * // 40入栈的时候，30要出来，30说我找到比我大的了
 * // 50入栈的时候，40要出来，40说找到大的了
 * // 60入栈的时候，50出来，遍历完了，剩下的都没找到更大的
 * // 再比如，[73,74,75,71,69,72,76,73]
 * // 73入栈
 * // 74入栈，73要出来
 * // 75入栈，74要出来
 * // 71入栈，没问题
 * // 69
 * // 72入栈，69要出来，69说我找到大的了；再看71，要出来
 * //     现在我怎么知道是下第几个？这里粗粗看是1、2
 * //     但是现在如果75要出去，那可是要把前面2个出去的算上的，就是我前面赶走了几位，我是要记下来的
 * // 那我封装一个Node{val, index}好了，这个肯定对了吧，还有更好的吗？我本来就要有一个空间返回int[] res的
 * // 直接放下标，取值按下标取
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00739 {

    public static void main(String[] args) {
        int[] ints = new Solution().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] stack = new int[temperatures.length];
            int[] res = new int[temperatures.length];
            int top = -1;
            for (int i = 0; i < temperatures.length; i++) {
                int curTemp = temperatures[i];
                while (true) {
                    if (top < 0) {
                        stack[0] = i;
                        top++;
                        break;
                    }
                    int topIdx = stack[top];
                    int topTemp = temperatures[topIdx];
                    if (curTemp <= topTemp) {
                        stack[top + 1] = i;
                        top++;
                        break;
                    }
                    res[topIdx] = i - topIdx;
                    top--;
                }
            }
            return res;
        }
    }
}
