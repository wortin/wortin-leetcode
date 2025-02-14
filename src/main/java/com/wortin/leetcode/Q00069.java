package com.wortin.leetcode;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00069 {

    public static void main(String[] args) {
//        int i = new Solution().mySqrt(2147395599);
//        System.out.println(i);
        int i = new Solution().mySqrt(0);
        System.out.println(i);
    }

    static class Solution {
        public int mySqrt(int x) {
            long left = 0, right = x;
            long res = 0;
            while (left <= right) {
                long mid = (left + right) / 2;
                long r = mid * mid;
                if (r == x) {
                    return (int) mid;
                }
                if (r < x) {
                    left = mid + 1;
                    res = mid;
                } else {
                    right = mid - 1;
                }
            }
            return (int) res;
        }
    }
}
