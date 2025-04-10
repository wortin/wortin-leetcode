package com.wortin.leetcode;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02999 {
    class Solution {


        public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
            Long suffix = Long.parseLong(s);
            if (suffix.compareTo(finish) > 0) {
                return 0;
            }
            if (suffix.compareTo(start) < 0) {
                return 0;
            }
            long ans = 1;

            int sufLen = s.length();
            String f = String.valueOf(finish);
            int finLen = f.length();
            int diff = finLen - sufLen;

            for (int i = 1; i < diff; i++) {
                ans += limit * (long) Math.pow(limit + 1, i - 1);
            }

            Long finishSuffix = Long.parseLong(f.substring(finLen - sufLen));
            if (finishSuffix.compareTo(suffix) >= 0) {
                // each s[i] < Math.min(f[i],limit)
            } else {

            }


            return ans;
        }


    }
}
