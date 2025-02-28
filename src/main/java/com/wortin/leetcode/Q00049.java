package com.wortin.leetcode;

import java.util.*;

public class Q00049 {
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<ByteArray, List<String>> identity2StrMap = new HashMap<>();
            for (String str : strs) {
                byte[] identity = countChar(str);
                List<String> wordSetList = identity2StrMap.computeIfAbsent(new ByteArray(identity), k -> new ArrayList<>());
                wordSetList.add(str);
            }
            return new ArrayList<>(identity2StrMap.values());
        }

        private byte[] countChar(String str) {
            byte[] charCount = new byte[26];
            for (int i = 0; i < str.length(); i++) {
                charCount[str.charAt(i) - 'a']++;
            }
            return charCount;
        }

        private static class ByteArray {
            byte[] val;

            public ByteArray(byte[] val) {
                this.val = val;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ByteArray byteArray = (ByteArray) o;
                return Arrays.equals(val, byteArray.val);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(val);
            }
        }
    }
}
