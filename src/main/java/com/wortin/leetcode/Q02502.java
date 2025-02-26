package com.wortin.leetcode;

/**
 * 给你一个整数 n ，表示下标从 0 开始的内存数组的大小。所有内存单元开始都是空闲的。
 * <p>
 * 请你设计一个具备以下功能的内存分配器：
 * <p>
 * 分配 一块大小为 size 的连续空闲内存单元并赋 id mID 。
 * 释放 给定 id mID 对应的所有内存单元。
 * 注意：
 * <p>
 * 多个块可以被分配到同一个 mID 。
 * 你必须释放 mID 对应的所有内存单元，即便这些内存单元被分配在不同的块中。
 * 实现 Allocator 类：
 * <p>
 * Allocator(int n) 使用一个大小为 n 的内存数组初始化 Allocator 对象。
 * int allocate(int size, int mID) 找出大小为 size 个连续空闲内存单元且位于  最左侧 的块，分配并赋 id mID 。
 * 返回块的第一个下标。如果不存在这样的块，返回 -1 。
 * int freeMemory(int mID) 释放 id mID 对应的所有内存单元。返回释放的内存单元数目。
 * <p>
 * 题目要求了，构造函数使用数组初始化
 * 然后allocate方法的能否快速找出size大小的空间，这个allocate是关键，我能想到快一点的方式是跳着找，跨越长度
 * 就是数组上记录你数据长度就是我结束的地方在哪里
 * freeMemory清空间是否有高效的方法，O(n)是可以的，能否O(1)?似乎不能，因为你至少是一个数组啊，或者我们不用数组
 *
 * <p>
 * 官解就是老老实实模拟的，不过灵神给出一个Lazy线段树+线段树二分的思路，我要趁这题来了解学习这个知识点
 * 我们先用模拟的方式实现一个版本再说
 * </p>
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02502 {
    public static void main(String[] args) {

    }

    static class Allocator {
        int[] mem;
        int n;

        public Allocator(int n) {
            this.mem = new int[n];
            this.n = n;
        }

        public int allocate(int size, int mID) {
            // 申请长度为size，赋值mID
            int len = 0;
            for (int i = 0; i < n; i++) {
                if (mem[i] == 0) {
                    len++;
                } else {
                    len = 0;
                }
                if (len == size) {
                    for (int j = i; j > i - len; j--) {
                        mem[j] = mID;
                    }
                    return i - len + 1;
                }
            }
            return -1;
        }

        public int freeMemory(int mID) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (mem[i] == mID) {
                    mem[i] = 0;
                    count++;
                }
            }
            return count;
        }
    }
}
