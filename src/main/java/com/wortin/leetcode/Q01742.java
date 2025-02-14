package com.wortin.leetcode;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01742 {

    public static void main(String[] args) {
        int i = new Solution().countBalls(1, 10);
        System.out.println(i);
    }

    static class Solution {
        public int countBalls(int lowLimit, int highLimit) {
            ConcurrentHashMap<Integer, AtomicInteger> countMap = new ConcurrentHashMap<>();
            IntStream.rangeClosed(lowLimit, highLimit).parallel().forEach(i -> {
                int n = cal(i);
                countMap.compute(n, (k, v) -> {
                    if (v == null) return new AtomicInteger(1);
                    v.incrementAndGet();
                    return v;
                });
            });
            return countMap.values().stream().map(AtomicInteger::get).max(Integer::compareTo).orElse(0);
        }

        private int cal(int n) {
            int res = 0;
            while (n > 0) {
                res += n % 10;
                n /= 10;
            }
            return res;
        }
    }


}
