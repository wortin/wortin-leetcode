package com.wortin.leetcode;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 如果是log n，那么一定是二分搜索了
 * 如果是排好序的，要找target，只要看到nums[mid]>target就往左找，否则往右找，相等就找到，就可以判断了
 * 但是现在是旋转了的，好在值互不相同，那就严格升序
 * 如果 nums[l] + (j-i) >= nums[j] 这个是严格升序的区间 否则，旋转点一定在i,j之中
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00033 {
    public static void main(String[] args) {
        int search = new Solution().search(new int[]{88, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 15, 66}, 4);
        System.out.println(search);
    }

    static class Solution {
        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 1) {
                return nums[0] == target ? 0 : -1;
            }
            if (n == 2) {
                return nums[0] == target ? 0 : nums[1] == target ? 1 : -1;
            }
            int l = 0;
            int r = n - 1;
            int mid;
            if (nums[n - 1] - nums[0] >= n - 1) {
                l = -1;
            } else {
                while (l < r - 1) {
                    mid = (l + r) / 2;
                    if (nums[mid] - nums[l] >= (mid - l)) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
            }
            int k = l;
            // 如果不是刚好升序的，l就是断层的最后一个最大的元素
            // 也就是说 [0,k] [k,n-1] 是2个升序的
            if (k == -1) {
                l = 0;
                r = n - 1;
            } else {
                if (target == nums[n - 1]) {
                    return n - 1;
                }
                if (target < nums[n - 1]) {
                    l = k + 1;
                    r = n - 1;
                } else {
                    l = 0;
                    r = k;
                }
            }
            while (l <= r) {
                mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return -1;
        }
    }
}
