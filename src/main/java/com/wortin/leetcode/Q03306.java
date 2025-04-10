package com.wortin.leetcode;

/**
 * 5个元音字母至少都出现1次，辅音恰好出现k次的最短子数组
 *
 * 恰好k次转化为f(k)至少k次，f(k+1)至少k+1次，那么恰好k次就是f(k)-f(k+1)
 *
 * 元音至少和辅音至少，考虑用滑动窗口
 * 以j为右端点，如果s[i...j]符合要求，那么所有t<=i都符合要求
 * 滑动窗口现在是计数，j右移时，因为i是上一次停下来的地方，所以j可能不会在每个下标都停下来，会不会漏掉可能？
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q03306 {
}
