package com.wortin.leetcode;

import java.util.Arrays;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00080 {
    public static void main(String[] args) {
        int[] ints = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3};
        int i = new Solution().removeDuplicates(ints);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));

    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length <= 2) return nums.length;

            int preVal = nums[0];
            int preCount = 1;
            int putIndex = 1;
            for (int i = 1; i < nums.length; i++) {
                int val = nums[i];
                if (val == preVal) {
                    if (preCount == 2) {
                        // 这个值重复超过2次要删掉，把后面那个数挪过来
                        int nextIndex = i + 1;
                        while (nextIndex < nums.length && nums[nextIndex] == preVal) {
                            nextIndex++;
                        }
                        if (nextIndex == nums.length) {
                            return putIndex;
                        }
                        nums[putIndex] = nums[nextIndex];
                        preVal = nums[nextIndex];
                        preCount = 1;
                        i = nextIndex;
                        putIndex++;
                        continue;
                    } else {
                        preCount++;
                    }
                } else {
                    preVal = val;
                    preCount = 1;
                }
                // 如果已经被挪过，并且中间发生了空档，要往前挪
                if (putIndex != i) {
                    nums[putIndex] = nums[i];
                }

                putIndex++;
            }
            return putIndex;
        }
    }
}
