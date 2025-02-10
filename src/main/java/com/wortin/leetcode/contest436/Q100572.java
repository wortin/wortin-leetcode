package com.wortin.leetcode.contest436;

import java.util.Arrays;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q100572 {

    public static void main(String[] args) {
//        int[] groups = new int[]{10, 21, 30, 41};
//        int[] elements = new int[]{2, 1};
//        int[] ints = new Solution().assignElements(groups, elements);
//        System.out.println(Arrays.toString(ints));

        int size = 100000;
        int[] groups = new int[size];
        int[] elements = new int[size];
        for (int i = 0; i < size; i++) {
            groups[i] = i + 1;
            elements[i] = size - i;
        }
        int[] ints = new Solution().assignElements(groups, elements);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] assignElements(int[] groups, int[] elements) {
            int[] result = new int[groups.length];
            for (int i = 0; i < groups.length; i++) {
                boolean found = false;
                for (int j = 0; j < elements.length; j++) {
                    int g = groups[i];
                    int e = elements[j];
                    if (g < e) {
                        continue;
                    }
                    if (g % e == 0) {
                        result[i] = j;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result[i] = -1;
                }
            }
            return result;
        }
    }

}
