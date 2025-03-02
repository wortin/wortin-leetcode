package com.wortin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * 指针窗口大小是确定的，如果暴力去判断，计char数量要O(nm) Arrays.equals(pc, sc) 要m=p.length
 * 优化的点是能否降低到O(n)? 官方答案是说维护pc-sc
 */
public class Q00438 {
    class Solution {
        int[] counts;
        int[] curs;

        public List<Integer> findAnagrams(String s, String p) {
            counts = new int[26];
            curs = new int[26];
            List<Integer> res = new ArrayList<>();
            if (p.length() > s.length()) {
                return res;
            }
            for (int i = 0; i < p.length(); i++) {
                counts[p.charAt(i) - 'a']++;
                curs[s.charAt(i) - 'a']++;
            }
            int diff = 0;
            for (int i = 0; i < 26; i++) {
                diff += Math.abs(counts[i] - curs[i]);
            }
            if (diff == 0) {
                res.add(0);
            }

            for (int i = 1; i <= s.length() - p.length(); i++) {
                int l = s.charAt(i - 1) - 'a';
                if (counts[l] >= curs[l]) {
                    diff++;
                } else {
                    diff--;
                }
                curs[l]--;
                int r = s.charAt(i + p.length() - 1) - 'a';
                if (counts[r] > curs[r]) {
                    diff--;
                } else {
                    diff++;
                }
                curs[r]++;
                if (diff == 0) {
                    res.add(i);
                }
            }
            return res;
        }
    }
}
