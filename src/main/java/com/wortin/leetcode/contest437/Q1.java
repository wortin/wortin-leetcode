package com.wortin.leetcode.contest437;

public class Q1 {
    public static void main(String[] args) {
        boolean res = new Solution().hasSpecialSubstring("jkjhfgg", 2);
        System.out.println(res);
    }

    static class Solution {
        public boolean hasSpecialSubstring(String s, int k) {
            if (s.length() == 1) return true;
            for (int i = 0; i < s.length() - k + 1; i++) {
                if (good(s, k, i)) {
                    return true;
                }
            }
            return false;
        }

        private boolean good(String s, int k, int i) {
            char c = s.charAt(i);
            for (int j = i + 1; j < i + k; j++) {
                char nc = s.charAt(j);
                if (nc != c) {
                    return false;
                }
            }
            if (i - 1 >= 0 && s.charAt(i - 1) == c) {
                return false;
            }
            if (i + k < s.length() && s.charAt(i + k) == c) {
                return false;
            }
            return true;
        }

    }
}
