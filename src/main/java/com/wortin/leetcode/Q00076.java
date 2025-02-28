package com.wortin.leetcode;

/**
 * 给你一个字符串 s 、一个字符串 t 。
 * 返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00076 {
    public static void main(String[] args) {
//        String s = new Solution().minWindow("ADOBECODEBANC", "ABC");
//        System.out.println(s);
//        String s = new Solution().minWindow("a", "a");
//        System.out.println(s);
        String s = new Solution().minWindow("a", "b");
        System.out.println(s);

    }

    static class Solution {
        public String minWindow(String s, String t) {
            // 本题用O(m+n) 使用快慢指针
            // 左指针定住，右指针找下去找到包含t的字母的，记录长度
            // 然后左指针移一个，符合吗？不符合的话，右指针再找下去，符合话左指针还能移一个
            // 现在就是如何判断左、右指针确定的范围是否包含t，就是统计字母的数量
            if (s.length() < t.length()) return "";

            int[] ts = new int[58];
            for (char c : t.toCharArray()) {
                ts[c - 'A']++;
            }
            int[] ss = new int[58];
            int i = -1, j = 0;
            int minI = -1;
            int minJ = 0;
            int minLen = Integer.MAX_VALUE;
            while (true) {
                // j去扩大
                while (j < s.length()) {
                    int c = s.charAt(j) - 'A';
                    // 如果我包含了t要的字符，我要记下来
                    if (ts[c] > 0) {
                        ss[c]++;
                        // 我检查我是否包含你好了
                        if (contains(ss, ts)) {
                            if (j - i < minLen) {
                                minLen = j - i;
                                minI = i;
                                minJ = j;
                            }
                            break;
                        }
                    }
                    j++;
                }
                if (j == s.length()) break;
                // 现在j已经停止遍历，可能是j到终点，或者(i,j]已经是包含t的了
                // 我去扩大i
                i++;
                while (i <= j) {
                    int c = s.charAt(i) - 'A';
                    if (ts[c] > 0) {
                        ss[c]--;
                    }
                    // 我检查我是否包含你好了
                    // 如果我还包含你，那么记录一下长度，可以继续扩
                    if (contains(ss, ts)) {
                        if (j - i < minLen) {
                            minLen = j - i;
                            minI = i;
                            minJ = j;
                        }
                        i++;
                    }
                    // 我已经不包含你了，请j扩大一下吧
                    else {
                        j++;
                        break;
                    }
                }
            }
            return minLen != Integer.MAX_VALUE ? s.substring(minI + 1, minJ + 1) : "";
        }

        private boolean contains(int[] ss, int[] ts) {
            for (int i = 0; i < 58; i++) {
                if (ss[i] < ts[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
