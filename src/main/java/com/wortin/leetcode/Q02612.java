package com.wortin.leetcode;

import java.util.*;

/**
 * You are given an integer n and an integer p representing an array arr of length n where all elements are set to 0's, except position p which is set to 1. You are also given an integer array banned containing restricted positions. Perform the following operation on arr:
 * <p>
 * Reverse a subarray with size k if the single 1 is not set to a position in banned.
 * Return an integer array answer with n results where the ith result is the minimum number of operations needed to bring the single 1 to position i in arr, or -1 if it is impossible.
 * <p>
 * Consider this question
 * <p>
 * I need to simulate this reverse operation from the init position p and end when there is no new index to visit
 * If current index is i, and take 1 operation, which indices can be visited?
 * If index i is the left endpoint of k subarray, the index i can be moved to i+k-1 if i+k-1<n or cannot be moved
 * And let's move the k subarray one step to the right, then index i can be moved to
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02612 {
    public static void main(String[] args) {
        int[] ans = new Solution().minReverseOperations(5, 0, new int[]{}, 2);
        System.out.println(Arrays.toString(ans));
    }

    static class Solution {
        public int[] minReverseOperations(int n, int p, int[] banned, int k) {
            Set<Integer> bannedIndexSet = new HashSet<>();
            for (int ind : banned) bannedIndexSet.add(ind);
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            ans[p] = 0;
            int operationCount = 1;
            Set<Integer> visitedIndexSet = new HashSet<>();
            visitedIndexSet.add(p);
            List<Integer> newIndices = new ArrayList<>();
            newIndices.add(p);
            List<Integer> nextIndices = new ArrayList<>();
            while (!newIndices.isEmpty()) {
                for (int i : newIndices) {
                    for (int l = i - k + 1; l <= i; l++) {
                        if (l < 0) {
                            continue;
                        }
                        if (l + k - 1 >= n) {
                            break;
                        }
                        int j = 2 * l + k - i - 1;
                        if (bannedIndexSet.contains(j) || visitedIndexSet.contains(j)) {
                            continue;
                        }
                        ans[j] = operationCount;
                        nextIndices.add(j);
                        visitedIndexSet.add(j);
                    }
                }
                operationCount++;
                newIndices.clear();
                List<Integer> temp = nextIndices;
                nextIndices = newIndices;
                newIndices = temp;
            }

            return ans;
        }
    }
}
