package com.wortin.leetcode.contest150_d;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q2 {
    public static void main(String[] args) {
//        int[][] squares = new int[][]{{522261215, 954313664, 461744743}, {628661372, 718610752, 21844764}, {619734768, 941310679, 91724451}, {352367502, 656774918, 591943726}, {860247066, 905800565, 853111524}, {817098516, 868361139, 817623995}, {580894327, 654069233, 691552059}, {182377086, 256660052, 911357}, {151104008, 908768329, 890809906}, {983970552, 992192635, 462847045}};
//        double v = new Solution().separateSquares(squares);
//        System.out.println(v);
//        int[][] squares = new int[][]{{0, 0, 1}, {2, 2, 1}};
//        double v = new Solution().separateSquares(squares);
//        System.out.println(v);
//        int[][] squares = new int[][]{{0, 0, 2}, {1, 1, 1}};
//        double v = new Solution().separateSquares(squares);
//        System.out.println(v);
        int[][] squares = new int[][]{{23,29,3}, {28,29,4}};
        double v = new Solution().separateSquares(squares);
        System.out.println(v);
    }

    static class Solution {
        public double separateSquares(int[][] squares) {
            int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
            for (int[] square : squares) {
                if (minY > square[1]) minY = square[1];
                if (maxY < square[1] + square[2]) maxY = square[1] + square[2];
            }
            long left = minY;
            long right = maxY;
            long res = left;
            while (left <= right) {
                long mid = (left + right) / 2;
                // 如果下面的面积大，那么往小的照去
                if (valid(squares, mid)) {
                    right = mid - 1;
                    res = mid;
                } else {
                    left = mid + 1;
                }
            }
            double l = res - 1;
            double r = res + 1;
            double rd = l;
            while (l <= r) {
                double mid = (l + r) / 2.0;
                if (validDouble(squares, mid)) {
                    r = mid - 0.0001;
                    rd = mid;
                } else {
                    l = mid + 0.0001;
                }
            }
            l = rd - 0.001;
            r = rd + 0.001;
            rd = l;
            while (l <= r) {
                double mid = (l + r) / 2.0;
                if (validDouble(squares, mid)) {
                    r = mid - 0.000001;
                    rd = mid;
                } else {
                    l = mid + 0.000001;
                }
            }
            return rd;
        }

        private boolean validDouble(int[][] squares, double y0) {
            BigDecimal downS = new BigDecimal("0");
            BigDecimal upS = new BigDecimal("0");
            for (int[] square : squares) {
                int y = square[1];
                int length = square[2];
                if ((double) y + length <= y0) {
                    downS = downS.add(BigDecimal.valueOf(length).multiply(BigDecimal.valueOf(length)));
                } else if ((double) y >= y0) {
                    upS = upS.add(BigDecimal.valueOf(length).multiply(BigDecimal.valueOf(length)));
                } else {
                    downS = downS.add(BigDecimal.valueOf(length).multiply(BigDecimal.valueOf(y0 - y)));
                    upS = upS.add(BigDecimal.valueOf(length).multiply(BigDecimal.valueOf(y + length - y0)));
                }
            }
            return upS.compareTo(downS) <= 0;
        }

        private boolean valid(int[][] squares, long y0) {
            BigInteger downS = new BigInteger("0");
            BigInteger upS = new BigInteger("0");
            for (int[] square : squares) {
                long y = square[1];
                long length = square[2];
                if (y + length <= y0) {
                    downS = downS.add(BigInteger.valueOf(length).multiply(BigInteger.valueOf(length)));
                } else if (y >= y0) {
                    upS = upS.add(BigInteger.valueOf(length).multiply(BigInteger.valueOf(length)));
                } else {
                    downS = downS.add(BigInteger.valueOf(length).multiply(BigInteger.valueOf(y0 - y)));
                    upS = upS.add(BigInteger.valueOf(length).multiply(BigInteger.valueOf(y + length - y0)));
                }
            }
            return upS.compareTo(downS) <= 0;
        }
    }
}
