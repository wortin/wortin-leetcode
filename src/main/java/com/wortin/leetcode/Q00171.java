package com.wortin.leetcode;

public class Q00171 {
    class Solution {
        public int titleToNumber(String columnTitle) {
            int res = 0;
            for (int i = 0; i <columnTitle.length(); i++) {
                res = res * 26 + (columnTitle.charAt(i) - 64);
            }
            return res;
        }
    }
}
