package com.wortin.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02080 {
    public static void main(String[] args) {
        int[] arr = {12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56};
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(arr);
        int query = rangeFreqQuery.query(1, 2, 4);
        System.out.println(query);
    }

    static class RangeFreqQuery {
        private final Map<Integer, List<Integer>> val2IndexArrMap = new HashMap<>();

        public RangeFreqQuery(int[] arr) {
            _init(arr);
        }

        private void _init(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int val = arr[i];
                List<Integer> list = val2IndexArrMap.computeIfAbsent(val, k -> new ArrayList<>());
                list.add(i);
            }
        }

        public int query(int left, int right, int value) {
            List<Integer> list = val2IndexArrMap.get(value);
            if (list == null) {
                return 0;
            }
            int vl = findFirstGteIndex(left, list);
            if (vl == -1) {
                return 0;
            }
            int vr = findFirstLteIndex(right, list);
            if (vr == -1) {
                return 0;
            }
            return vr - vl + 1;
        }

        private int findFirstGteIndex(int val, List<Integer> list) {
            int left = 0;
            int right = list.size() - 1;
            int res = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (list.get(mid) < val) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                    res = mid;
                }
            }
            return res;
        }

        private int findFirstLteIndex(int val, List<Integer> list) {
            int left = 0;
            int right = list.size() - 1;
            int res = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (list.get(mid) <= val) {
                    left = mid + 1;
                    res = mid;
                } else {
                    right = mid - 1;
                }
            }
            return res;
        }
    }
}
