package com.wortin.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q02506 {
    static class Solution {
        public int similarPairs(String[] words) {
            Map<Integer, Integer> similarCountMap = new HashMap<>();
            for (String word : words) {
                int key = getKey(word);
                similarCountMap.compute(key, (k, v) -> {
                    if (v == null) {
                        return 1;
                    }
                    return v + 1;
                });
            }
            int count = 0;
            for (int c : similarCountMap.values()) {
                if (c > 1) {
                    count += c * (c - 1) / 2;
                }
            }
            return count;
        }

        private int getKey(String word) {
            int key = 0;
            for (int i = 0; i < word.length(); i++) {
                int shift = word.charAt(i) - 'a';
                key |= (1 << shift);
            }
            return key;
        }
    }
}
