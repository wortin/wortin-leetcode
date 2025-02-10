package com.wortin.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q00913 {
    public static void main(String[] args) {
        int[][] ints = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        int i = new Solution().catMouseGame(ints);
        System.out.println(i);

//        int[][] ints = {{1, 3}, {0}, {3}, {0, 2}};
//        int i = new Solution().catMouseGame(ints);
//        System.out.println(i);

    }

    static class Solution {
        /**
         * 老鼠赢
         */
        static final int RW = 1;
        /**
         * 猫赢
         */
        static final int CW = 2;
        /**
         * 老鼠的回合
         */
        static final int RT = 0;
        /**
         * 猫的回合
         */
        static final int CT = 1;

        public int catMouseGame(int[][] graph) {
            int n = graph.length;
            // 老鼠在i，猫在j，轮到谁的回合，此时谁赢
            int[][][] dp = new int[n][n][2];
            // 老鼠在i，猫在j，轮到谁的回合，此时它还有几条路可以选择
            int[][][] degree = new int[n][n][2];
            // 永远记录已经确定了谁赢的点位
            Queue<int[]> queue = new LinkedList<>();
            for (int j = 1; j < n; j++) {
                // 只要老鼠到洞，不管猫在哪里，不管谁的回合，都是老鼠赢了
                dp[0][j][RT] = RW;
                dp[0][j][CT] = RW;
                queue.offer(new int[]{0, j, RT});
                queue.offer(new int[]{0, j, CT});
            }
            for (int i = 1; i < n; i++) {
                // 只要两人相遇，不管谁的回合，都是猫赢了（排除在洞）
                dp[i][i][RT] = CW;
                dp[i][i][CT] = CW;
                queue.offer(new int[]{i, i, RT});
                queue.offer(new int[]{i, i, CT});
            }
            // 初始的路就是这个这个节点相连的节点个数
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    degree[i][j][RT] = graph[i].length;
                    degree[i][j][CT] = graph[j].length;
                }
            }
            // 对于猫来说，猫不能走0，所以和0相连的节点上，猫能走的路要减少1条
            for (int i = 0; i < n; i++) {
                for (int k : graph[0]) {
                    degree[i][k][CT]--;
                }
            }
            while (!queue.isEmpty()) {
                // 我每次都是从已经确定谁赢的点位开始往上推
                int[] cur = queue.poll();
                int ci = cur[0];
                int cj = cur[1];
                int ct = cur[2];
                // 拿到可以走到当前这个点位的上个回合的点位列表
                List<int[]> preNodes = getPreNodes(cur, graph);
                for (int[] preNode : preNodes) {
                    int pi = preNode[0];
                    int pj = preNode[1];
                    int pt = preNode[2];
                    // 现在站在上一个回合的节点上来看
                    if (dp[pi][pj][pt] == 0) {
                        // 如果上一个回合是老鼠的回合，那么当前是老鼠赢，上一个回合老鼠就能赢
                        if (pt == RT && dp[ci][cj][ct] == RW) {
                            dp[pi][pj][pt] = RW;
                            queue.offer(new int[]{pi, pj, pt});
                        }
                        // 如果上一个回合是猫的回合，那么当前是猫赢，上一个回合猫就能赢
                        else if (pt == CT && dp[ci][cj][ct] == CW) {
                            dp[pi][pj][pt] = CW;
                            queue.offer(new int[]{pi, pj, pt});
                        } else {
                            // 否则就不能判断出当前这个点位，对上一个回合来说，能否必赢
                            // 从上一个回合角度看，ci,cj,ct 这个点位这条路已经不需要考虑了
                            degree[pi][pj][pt]--;
                            // 对上个回合来说，已经没有可以赢的路了，那么它一定是输（或者和了，但因为是从边界往上推的，已经是某人赢的结论），就只能按照当前状态给了
                            // 具体来说
                            // 如果上个回合是老鼠回合，看下来不存在一条路让老鼠能赢，那么老鼠就要输了，而当前回合此时dp[ci][cj][ct]就是猫赢的值
                            // 如果上个回合是猫回合，不存在一条路让猫赢，那么猫就要输了，而当前回合此时dp[ci][cj][ct]就是老鼠赢的值
                            // 因为队列里面的值，要么老鼠赢要么猫赢，dp=0 是不会入队的
                            // 那么 dp=0 表示什么意思？就是从既定边界自下而上往上推，它无法推到的点位
                            // 首先，从边界点位往上推，有可能不能推边所有点位
                            // 而不能推到的点位，就是无法确定到最终谁赢的点位
                            // 这里还有一个问题，如何保证degree一定能降到0？是不是说也有点位的度不能降到0的，所以这个点位最后也会成为平局
                            if (degree[pi][pj][pt] == 0) {
                                dp[pi][pj][pt] = dp[ci][cj][ct];
                                queue.offer(new int[]{pi, pj, pt});
                            }
                        }
                    }
                }
            }
            return dp[1][2][RT];
        }

        private List<int[]> getPreNodes(int[] cur, int[][] graph) {
            List<int[]> preNodes = new LinkedList<>();
            int i = cur[0];
            int j = cur[1];
            int t = cur[2];
            // 如果当前是老鼠的回合，那么上一个回合是猫的，猫从哪些点位可以走过来
            if (t == RT) {
                for (int n : graph[j]) {
                    // 注意，猫不能从洞走过来
                    if (n != 0) {
                        preNodes.add(new int[]{i, n, CT});
                    }
                }
            }
            // 当前是猫的，上个回合是老鼠的，老鼠从哪些点位可以走过来
            else {
                for (int n : graph[i]) {
                    preNodes.add(new int[]{n, j, RT});
                }
            }
            return preNodes;
        }
    }
}
