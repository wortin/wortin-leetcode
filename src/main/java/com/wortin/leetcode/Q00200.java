package com.wortin.leetcode;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * 第一个感觉想并查集，可以查出我们彼此是否相连
 * 但是好像不是这么直接能用到，然后就是求相连的联通图的个数。
 * 我的想法就是搜索，先随便找个1开始，上下左右找开去，1都变成0，找完就处理了一个联通子图
 * 然后再找下一个1，然后再找开去，直到找不到1
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00200 {
    public static void main(String[] args) {
        int i = new Solution().numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}});
        System.out.println(i);
    }

    static class Solution {
        int m;
        int n;
        char[][] grid;
        int si;
        int sj;
        final int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int numIslands(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            int res = 0;
            findOne();
            while (si != -1) {
                res++;
                searchOne(si, sj);
                findOne();
            }
            return res;
        }

        private void searchOne(int i, int j) {
            grid[i][j] = '0';
            for (int k = 0; k < 4; k++) {
                int ci = i + dir[k][0];
                int cj = j + dir[k][1];
                if (ci >= 0 && ci < m && cj >= 0 && cj < n && grid[ci][cj] == '1') {
                    searchOne(ci, cj);
                }
            }
        }


        private void findOne() {
            si = -1;
            sj = -1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        si = i;
                        sj = j;
                        return;
                    }
                }
            }
        }
    }
}
