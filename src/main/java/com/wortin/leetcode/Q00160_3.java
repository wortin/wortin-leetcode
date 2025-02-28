package com.wortin.leetcode;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 提示：
 * <p>
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
 * <p>
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
 */
public class Q00160_3 {
    public static void main(String[] args) {

    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode ca = headA, cb = headB;
            while (ca != cb) {
                if (ca == null) {
                    ca = headB;
                } else {
                    ca = ca.next;
                }
                if (cb == null) {
                    cb = headA;
                } else {
                    cb = cb.next;
                }
            }
            return ca;
        }
    }

}
