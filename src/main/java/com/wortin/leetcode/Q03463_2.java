package com.wortin.leetcode;

/**
 * 这里有一个专门求组合数取模下的值的办法，是卢卡斯定理：
 * 质数p，有 mCn%p= ( (m/p) C (n/p) * m%p C n%p ) %p
 * <p>
 * 注意到 m%pCn%p,可以用一般求组合数的方法直接求的
 * m/pCn/p可以继续通过卢卡斯定理继续变小继续求
 * <p>
 * 当模数不是质数，需要使用拓展卢卡斯定理。
 * 记 m = p1^k1*p2^k2*....
 * mi = pi^ki
 * mCn % p1^k1 = r1
 * mCn % p2^k2 = r2
 * ...
 * 根据中国剩余定理，一个数模mi余ri，模mi余ri，（mi之间互质）求这个数是多少，求法是 M = mi累乘，Mi=M/mi, Mi'是Mi模mi的逆元，然后 riMiMi'累加即可
 * 所以简单来看，只要把r1、r2求出来，就可以求mCn了
 * 这里pi^ki不是质数，无法直接用卢卡斯定理，有更多复杂的求解定理，我今天是看不懂看不下去了。
 * 但是对于本题来说 m = 10 = 2 * 5 都是一次幂，可以简单来
 * mCn % 2 = r1 可以通过卢卡斯定理，直接来算 (m/2Cn/2*m%2Cm%n)%2 = r1
 * mCn % 5 = r2
 * 这样根据剩余定理，m1=2,m2=5,M=10,M1=5,M2=2, 求M1'，即(M1'*M1)%m1=1，(M1'*5)%2=1,M1'=1;同理(M2'*2)%5=1,M2'=3;
 * 所以mCn=(5r1+6r2)%10
 */
public class Q03463_2 {
    public static void main(String[] args) {
        boolean b = new Solution().hasSameDigits("3902");
        System.out.println(b);
    }

    static class Solution {
        public boolean hasSameDigits(String s) {
            // s0 s1 s2 s3 len=4
            int len = s.length();
            // s0+2s2+s2
            // s1+2s3+s3
            int[][] pc2 = combinationOfP(2);
            int[][] pc5 = combinationOfP(5);
            int res1 = 0;
            for (int i = 0; i <= len - 2; i++) {
                res1 += ((s.charAt(i) - '0') * combinationByLucas(len - 2, i, 2, pc2)) % 10;
            }
            int res2 = 0;
            for (int i = 1; i <= len - 1; i++) {
                res2 += ((s.charAt(i) - '0') * combinationByLucas(len - 2, i, 5, pc5)) % 10;
            }
            return res1 == res2;
        }

        private int[][] combinationOfP(int p) {
            // mCn = pc[n][m]
            int[][] pc = new int[p][p];
            int[] fc = new int[p];
            fc[0] = 1;
            fc[1] = 1;
            for (int i = 2; i < p; i++) {
                fc[i] = (fc[i - 1] * i);
            }
            pc[0][0] = 1;
            pc[1][0] = 1;
            pc[1][1] = 1;
            for (int n = 2; n < p; n++) {
                for (int m = 0; m <= n; m++) {
                    pc[n][m] = (fc[n] / (fc[m] * fc[n - m]));
                }
            }
            return pc;
        }

        private int combinationByLucas(int n, int m, int p, int[][] pc) {
            if (n < p) {
                return pc[n][m];
            }
            int n1 = n / p;
            int m1 = m / p;
            return (combinationByLucas(n1, m1, p, pc) * pc[n % p][m % p]) % p;
        }
    }
}
