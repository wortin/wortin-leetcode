package com.wortin.leetcode;

import java.util.Arrays;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * Log means we should use binary search algorithm
 * In face, we only need to perform a binary search on one of the arrays.
 * <p>
 * The median divides the merged array into three part. Assume the index of median is m0 (and when m+n is even,
 * the index of another median is m1), then the 1st part is nums[0...m0), 2nd is nums[m0,m1], 3rd is nums(m1,n-1]
 * <p>
 * Assume c = (m+n-1)/2 is the 1st part length.
 * For example, m+n=4, c=1, (0) (m0, m1) (3)
 * For example, m+n=5, c=2, (0,1) (m0) (3,4)
 * e.g., m+n=1, c=0, (m0)
 * e.g., m+n=2, c=0, (m0,m1)
 * And in searching loop we get nums1[i], if i is the biggest index which is less than m0,
 * it meets: nums1[0,i] and nums2[0,j] will be merged into 1st part, and j=c-i-2,
 * so
 * nums1[i]<=nums2[j+1] and nums2[j] <= nums1[i+1]
 * And we need consider the boundary case:
 * i must be [0,n) don't worry and i must be less than c i<c
 * and j should be [0,m) j = c-i-2 , if i=c-1 j=-1 means no element in nums2 should be selected
 * we choose the minor length of array for enumerate i
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00004 {

    public static void main(String[] args) {
//        double mid = new Solution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
//        System.out.println(mid);
//        double mid = new Solution().findMedianSortedArrays(new int[]{4}, new int[]{});
//        System.out.println(mid);
        double mid = new Solution().findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17});
        System.out.println(mid);
    }

    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            if (n > m) {
                int temp = n;
                n = m;
                m = temp;
                int[] numsTemp = nums1;
                nums1 = nums2;
                nums2 = numsTemp;
            }
            int c = (m + n - 1) / 2;
            int l = -1;
            int r = Math.min(c - 1, n - 1);
            int i;
            int j;
            int t1;
            int t1h;
            int t2;
            int t2h;
            int m0;
            int m1;
            while (l <= r) {
                i = (l + r) / 2;
                j = c - i - 2;
                if (j < 0) {
                    t2h = Integer.MIN_VALUE;
                } else {
                    t2h = nums2[j];
                }
                if (j + 1 >= m) {
                    t2 = Integer.MAX_VALUE;
                } else {
                    t2 = nums2[j + 1];
                }
                if (i < 0) {
                    t1h = Integer.MIN_VALUE;
                } else if (i >= n) {
                    t1h = nums1[n - 1];
                } else {
                    t1h = nums1[i];
                }
                if (i + 1 >= n) {
                    t1 = Integer.MAX_VALUE;
                } else {
                    t1 = nums1[i + 1];
                }
                if (t1h > t2) {
                    r = i - 1;
                } else if (t2h > t1) {
                    l = i + 1;
                } else {
                    m0 = Math.min(t1, t2);
                    if ((m + n) % 2 == 1) {
                        return m0;
                    }
                    int t1m1, t2m1;
                    if (i + 2 >= n) {
                        t1m1 = Integer.MAX_VALUE;
                    } else {
                        t1m1 = nums1[i + 2];
                    }
                    if (j + 2 >= m) {
                        t2m1 = Integer.MAX_VALUE;
                    } else {
                        t2m1 = nums2[j + 2];
                    }
                    if (t1 <= t2) {
                        m1 = Math.min(Math.min(t2, t1m1), t2m1);
                    } else {
                        m1 = Math.min(Math.min(t1, t1m1), t2m1);
                    }
                    return ((double) m0 + (double) m1) / 2d;
                }
            }
            return 0;
        }
    }

}
