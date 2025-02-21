package com.wortin.leetcode.contest437;

public class Q3 {
    public static void main(String[] args) {
        new Solution();
    }

    static class Solution {
        public boolean maxSubstringLength(String s, int k) {
            if (k == 0) return true;
            if (k > s.length()) return false;
            int[] charCount = new int[26];
            for (int i = 0; i < s.length(); i++) {
                charCount[s.charAt(i) - 'a']++;
            }
            int c = 0;
            for (int i = 0; i < 26; i++) {
                if (charCount[i] == 1) {
                    c++;
                }
            }
            return c >= k;
        }
    }
}
