package com.wortin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01728 {

    public static void main(String[] args) {
//        String[] grid = new String[]{"####F", "#C...", "M...."};
//        boolean res = new Solution().canMouseWin(grid, 1, 2);
//        System.out.println(res);

//        String[] grid = new String[]{"M.C...F"};
//        boolean res = new Solution().canMouseWin(grid, 1, 4);
//        System.out.println(res);

//        String[] grid = new String[]{"M.C...F"};
//        boolean res = new Solution().canMouseWin(grid, 1, 3);
//        System.out.println(res);

        String[] grid = new String[]{".M...", "..#..", "#..#.", "C#.#.", "...#F"};
        boolean res = new Solution().canMouseWin(grid, 3, 1);
        System.out.println(res);
    }

    static class Solution {
        static final int MT = 0; // 老鼠的回合，老鼠可以移动
        static final int CT = 1; // 猫的回合，猫可以移动
        static final int MW = 1; // 老鼠赢
        static final int CW = 2; // 猫赢
        int rows;
        int cols;
        int nodeCount;
        boolean[] wall;
        static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int catJump;
        int mouseJump;

        public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
            this.catJump = catJump;
            this.mouseJump = mouseJump;
            // 1 <= catJump, mouseJump <= 8
            // 1 <= rows, cols <= 8
            rows = grid.length;
            cols = grid[0].length();
            nodeCount = rows * cols;
            // dp[i][j][t] 老鼠在第i个方格，猫在第j个方格，当前是t的回合，判断出老鼠赢还是猫赢
            int[][][] dp = new int[nodeCount][nodeCount][2];
            // degree[i][j][t] 老鼠在第i个方格，猫在第j个方格，当前是t的回合，那么对t来说，还剩多少条路可以选择必赢
            int[][][] degree = new int[nodeCount][nodeCount][2];
            // 找到老鼠、猫、食物、墙的位置
            int mousePos = 0;
            int catPos = 0;
            int foodPos = 0;
            wall = new boolean[nodeCount];
            // 队列用于拓扑排序
            Queue<int[]> queue = new LinkedList<>();
            // 找到各个方格里面的东西的位置
            for (int i = 0; i < rows; i++) {
                String s = grid[i];
                for (int j = 0; j < cols; j++) {
                    int pos = i * cols + j;
                    char c = s.charAt(j);
                    if (c == 'C') {
                        catPos = pos;
                    } else if (c == 'M') {
                        mousePos = pos;
                    } else if (c == 'F') {
                        foodPos = pos;
                    } else if (c == '#') {
                        wall[pos] = true;
                    }
                }
            }
            // 初始化边界条件
            // 猫和老鼠在同一个位置，猫赢
            for (int pos = 0; pos < nodeCount; pos++) {
                if (wall[pos]) continue;
                dp[pos][pos][MT] = CW;
                dp[pos][pos][CT] = CW;
                queue.offer(new int[]{pos, pos, MT});
                queue.offer(new int[]{pos, pos, CT});
            }
            // 猫和食物在都一个单元格，猫赢 (老鼠不在食物这一格)
            // 老鼠和食物都一个单元格，老鼠赢（猫不在食物这一格）
            for (int pos = 0; pos < nodeCount; pos++) {
                if (wall[pos] || pos == foodPos) continue;
                // 谁在食物处，谁赢
                dp[pos][foodPos][MT] = CW;
                dp[pos][foodPos][CT] = CW;
                queue.offer(new int[]{pos, foodPos, MT});
                queue.offer(new int[]{pos, foodPos, CT});

                dp[foodPos][pos][MT] = MW;
                dp[foodPos][pos][CT] = MW;
                queue.offer(new int[]{foodPos, pos, MT});
                queue.offer(new int[]{foodPos, pos, CT});
            }
            // 初始化度
            // 它的逻辑是，对每个一个节点，如果四周的节点可以走到这个节点，那么四周的节点的度可以+1
            for (int mp = 0; mp < nodeCount; mp++) {
                if (wall[mp]) {
                    continue;
                }
                int mi = mp / cols;
                int mj = mp % cols;
                for (int cp = 0; cp < nodeCount; cp++) {
                    if (wall[cp]) {
                        continue;
                    }
                    int ci = cp / cols;
                    int cj = cp % cols;
                    degree[mp][cp][MT]++;
                    degree[mp][cp][CT]++;
                    for (int[] dir : dirs) {
                        for (int mid = mi + dir[0], mjd = mj + dir[1], jumpCount = 0; mid < rows && mid >= 0 && mjd < cols && mjd >= 0 && !wall[mid * cols + mjd] && jumpCount < mouseJump; mid += dir[0], mjd += dir[1], jumpCount++) {
                            degree[mid * cols + mjd][cp][MT]++;
                        }
                        for (int cid = ci + dir[0], cjd = cj + dir[1], jumpCount = 0; cid < rows && cid >= 0 && cjd < cols && cjd >= 0 && !wall[cid * cols + cjd] && jumpCount < catJump; cid += dir[0], cjd += dir[1], jumpCount++) {
                            degree[mp][cid * cols + cjd][CT]++;
                        }
                    }
                }
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curMousePos = cur[0];
                int curCatPos = cur[1];
                int curTurn = cur[2];
                // 往上推上一个回合的情况，能够走到当前这个情况的上一个回合的点位有哪些
                List<int[]> prevStateList = getPrevStateList(curMousePos, curCatPos, curTurn);
                for (int[] prevState : prevStateList) {
                    // 现在站在上一个点位来考虑
                    int preMousePos = prevState[0];
                    int preCatPos = prevState[1];
                    int preTurn = prevState[2];
                    if (dp[preMousePos][preCatPos][preTurn] != 0) {
                        continue;
                    }
                    boolean found = false;
                    if (preTurn == MT) {
                        // 如果当前是老鼠赢的，所以老鼠移动后能赢
                        if (dp[curMousePos][curCatPos][curTurn] == MW) {
                            dp[preMousePos][preCatPos][preTurn] = MW;
                            queue.offer(new int[]{preMousePos, preCatPos, preTurn});
                            found = true;
                        }
                    }
                    // 如果上一个点位是猫的回合，并且移动后当前就是猫赢，那么很好，上一个点位就是猫赢的
                    if (preTurn == CT && dp[curMousePos][curCatPos][curTurn] == CW) {
                        dp[preMousePos][preCatPos][preTurn] = CW;
                        queue.offer(new int[]{preMousePos, preCatPos, preTurn});
                        found = true;
                    }
                    if (!found) {
                        degree[preMousePos][preCatPos][preTurn]--;
                        // 如果找不到所有路能赢了，那就输了
                        if (degree[preMousePos][preCatPos][preTurn] <= 0) {
                            dp[preMousePos][preCatPos][preTurn] = dp[curMousePos][curCatPos][curTurn];
                            queue.offer(new int[]{preMousePos, preCatPos, preTurn});
                        }
                    }
                }
            }
            return dp[mousePos][catPos][MT] == MW;
        }

        public List<int[]> getPrevStateList(int mp, int cp, int t) {
            List<int[]> prevStateList = new ArrayList<>();
            int maxJumpCount;
            int pos;
            // 如果是猫，那么上回合是鼠，要看鼠的
            if (t == CT) {
                maxJumpCount = mouseJump;
                pos = mp;
            } else {
                maxJumpCount = catJump;
                pos = cp;
            }
            int si = pos / cols;
            int sj = pos % cols;
            prevStateList.add(new int[]{mp, cp, t == CT ? MT : CT});
            for (int[] dir : dirs) {
                for (int i = si + dir[0], j = sj + dir[1], jump = 0; i >= 0 && i < rows && j >= 0 && j < cols && !wall[i * cols + j] && jump < maxJumpCount; i += dir[0], j += dir[1], jump++) {
                    if (t == CT) {
                        prevStateList.add(new int[]{i * cols + j, cp, MT});
                    } else {
                        prevStateList.add(new int[]{mp, i * cols + j, CT});
                    }
                }
            }
            return prevStateList;
        }
    }
}
