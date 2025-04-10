package com.wortin.leetcode;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00075 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0};
        new Solution().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        int n;
        int i;
        int j;
        int[] nums;

        public void sortColors(int[] nums) {
            // 3 partition
            // nums[0,i) is all 0
            // nums(j,n-1] is all 2
            // nums[i,k) is all 1
            this.nums = nums;
            n = nums.length;
            if (n <= 1) return;
            i = 0;
            j = n - 1;
            while (i <= j && nums[i] == 0) i++;
            while (j >= i && nums[j] == 2) j--;
            moveI();
            moveJ();
            for (int k = i; k <= j; k++) {
                if (k < i) {
                    continue;
                }
                if (nums[k] == 0) {
                    nums[k] = nums[i]; // nums[i] must be 1
                    nums[i] = 0;
                    moveI();
                }
                if (nums[k] == 2) {
                    nums[k] = nums[j];
                    nums[j] = 2;
                    moveJ();
                }
            }
        }

        private void moveI() {
            while (i <= j) {
                if (nums[i] == 1) {
                    break;
                }
                if (nums[i] == 0) {
                    i++;
                    continue;
                }
                if (nums[i] == 2) {
                    nums[i] = nums[j];
                    nums[j] = 2;
                    while (j >= i && nums[j] == 2) j--;
                }
            }
        }

        private void moveJ() {
            while (j >= i) {
                if (nums[j] == 1) {
                    break;
                }
                if (nums[j] == 2) {
                    j--;
                    continue;
                }
                if (nums[j] == 0) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                    while (i <= j && nums[i] == 0) i++;
                }
            }
        }
    }
}
