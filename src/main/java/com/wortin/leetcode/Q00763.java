package com.wortin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * 示例 2：
 * <p>
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 * <p>
 * 本题思路感觉在哪里又看到过，就是从第一个char开始，找该char最后一个的位置，然后中间的char都去找最后的，这样框住一段，然后下一段又开始
 * 双指针去扫，感觉要n^2了，可以的，通过记录最后一个的下标，成功自己写出了O(n)，可答案一样，注意，虽然有2层循环，其实只遍历了一遍
 */
public class Q00763 {

    class Solution {
        public List<Integer> partitionLabels(String s) {
            List<Integer> res = new ArrayList<>();
            int[] lastIdxMap = new int[26];
            for (int i = 0; i < s.length(); i++) {
                lastIdxMap[s.charAt(i) - 'a'] = i;
            }
            int i = 0;
            while (i < s.length()) {
                int lastIdx = lastIdxMap[s.charAt(i) - 'a'];
                for (int j = i + 1; j < lastIdx; j++) {
                    lastIdx = Math.max(lastIdxMap[s.charAt(j) - 'a'], lastIdx);
                }
                res.add(lastIdx - i + 1);
                i = lastIdx + 1;
            }
            return res;
        }
    }
}
