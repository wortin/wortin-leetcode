package com.wortin.leetcode;

import java.util.HashSet;

/**
 * 最长连续序列
 *
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 示例 3：
 * <p>
 * 输入：nums = [1,0,1,2]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * </p>
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00128 {
    class Solution {
        public int longestConsecutive(int[] nums) {
            // 因为时间复杂度要求O(n)所以不能排序了
            // 连续就是按1严格递增或递减
            // 首先我把数组的每个值放到Map里
            // 可以考虑使用并查集吗？比如我对每个元素nums[i]，如果nums[i]+1存在，就说 nums[i]和nums[i]+1是一组的，-1同理
            // 然后看最后家族树中最大的那个
            // 并查集的插入和查找效率是多少啊？log_n
            // 或者这样，就不停找下去呗，如果+1有，找下一个，把找过的不要找了
            HashSet<Integer> numSet = new HashSet<>();
            for (int n : nums) {
                numSet.add(n);
            }
            int maxCount = 0;
            for (int n : nums) {
                if (numSet.contains(n)) {
                    int count = 1;
                    numSet.remove(n);

                    int cur = n + 1;
                    while (numSet.contains(cur)) {
                        numSet.remove(cur);
                        count++;
                        cur++;
                    }
                    cur = n - 1;
                    while (numSet.contains(cur)) {
                        numSet.remove(cur);
                        count++;
                        cur--;
                    }
                    maxCount = Math.max(maxCount, count);
                }
            }
            return maxCount;
        }
    }
}
