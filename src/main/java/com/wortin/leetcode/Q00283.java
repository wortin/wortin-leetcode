package com.wortin.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * 进阶：你能尽量减少完成的操作次数吗？
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00283 {

    public static void main(String[] args) {
//        int[] ints = {0};
//        new Solution().moveZeroes(ints);
//        System.out.println(Arrays.toString(ints));
        int[] ints = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(ints);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public void moveZeroes(int[] nums) {
            // 如何减少操作次数，但必须是稳定的，保持原来数据的顺序不变
            // 直观来看，我需要把非0的元素都保持顺序的情况下往前移，把0直接吃掉都没事，后面补0就好
            // 是0我就计数器+1
            // 我是记录一个第一个0的下标
            // 不是0，如果计数器不是0，说明前面有空位，要往前移
            // 0 1 0 3 12
            // 快慢游标
            int i = 0; // 表示从头开始最后一个非0元素的下标
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != 0) {
                    nums[i] = nums[j];
                    i++;
                }
            }
            for (int k = i; k < nums.length; k++) {
                nums[k] = 0;
            }
        }
    }

}
