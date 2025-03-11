package com.wortin.leetcode;

import java.util.Arrays;

/**
 * Alice 是 n 个花园的园丁，她想通过种花，最大化她所有花园的总美丽值。
 * <p>
 * 给你一个下标从 0 开始大小为 n 的整数数组 flowers ，其中 flowers[i] 是第 i 个花园里已经种的花的数目。已经种了的花 不能 移走。同时给你 newFlowers ，表示 Alice 额外可以种花的 最大数目 。同时给你的还有整数 target ，full 和 partial 。
 * <p>
 * 如果一个花园有 至少 target 朵花，那么这个花园称为 完善的 ，花园的 总美丽值 为以下分数之 和 ：
 * <p>
 * 完善 花园数目乘以 full.
 * 剩余 不完善 花园里，花的 最少数目 乘以 partial 。如果没有不完善花园，那么这一部分的值为 0 。
 * 请你返回 Alice 种最多 newFlowers 朵花以后，能得到的 最大 总美丽值。
 * <p>
 * 提示：
 * <p>
 * 1 <= flowers.length <= 105
 * 1 <= flowers[i], target <= 105
 * 1 <= newFlowers <= 1010
 * 1 <= full, partial <= 105
 * <p>
 * 一开始确实有难度，想着往dp走了，但是数据规模似乎在暗示不能直接用dp解，那么就是二分了，二分就是最值试探
 * 然后还是算了，先来朴素分析是最靠谱的
 * <p>
 * 就是把最大的几个花园搞成完善的，然后把最小的几个花园的短板补上来
 * 数组排序升序，[i,n-1]个花园搞成完善的，需要多少花？可以前缀和求出来的
 * [0,j]个花园补短板，补足flowers[j]这么高需要多少花，可以算出来的
 * 然后就是遍历也无妨
 * p[j]表示[0...j]个花园补足高度到flowers[j]需要的花，p[j-1] + (flowers[j]-flowers[j-1]) * j
 * 这个思路是正确的，但是超出时间限制了，所以枚举可能是不行的
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02234 {
    public static void main(String[] args) {
        long l = new Solution().maximumBeauty(new int[]{80709, 2267, 22737, 40639, 70682, 54594, 16814, 30136, 72193, 40893, 10760, 52359, 75776, 48237, 73864, 67406, 17902, 2886, 5384, 67432, 57973, 49075, 86462, 70678, 26118, 85271, 80416, 76193, 65625, 69188, 43427, 80482, 92890, 44849, 84420, 90430, 31098, 13262, 72133, 54321, 17070, 21847, 90565, 20576, 30894, 97048, 54088, 87954, 93377, 53921, 96433, 20970, 50174, 80462, 13204, 84796, 18693, 56954, 26368, 43625, 70941, 34560, 69341, 67938, 92114, 9460, 931, 71774, 85278, 83463, 53213, 79472, 74683, 88455, 97928, 53973, 12449, 23266, 58418, 3535, 4997, 15201, 14038, 73431, 53892, 21066, 74271, 99665, 98972, 97840, 75527, 2283, 58964, 90770, 87534, 70213, 65846, 85786, 94690, 54703, 11359, 9231, 75959, 43712, 9604, 65489, 48988, 84743, 17106, 76213, 64376, 48756, 7857, 83911, 16783, 92209, 84899, 98989, 2315, 45149, 26832, 33814, 82058, 68180, 47896, 48127, 36204, 5440, 51230, 77968, 56512, 49214, 79327, 20994, 52696, 67436, 19006, 48497, 76711, 67134, 5691, 53731, 64472, 15124, 48042, 29033, 39288, 55577, 83158, 47774, 14264, 23298, 52679, 42767, 35197, 93236, 40782, 47082, 25498, 10003, 59730, 70152, 16846, 9556, 68601, 30174, 56077, 75595, 65317, 33828, 65768, 10682, 42146, 47542, 51853, 79036, 70890, 84566, 72976, 96685, 96827, 30131, 90520, 32722, 34831, 40489, 30992, 66290, 68783, 47641, 38486, 6087, 94315, 76558, 3049, 13871, 23550, 31297, 13666, 89646, 12575, 12496, 22303, 70507, 50182, 95532, 89679, 1246, 22554, 71976, 35570, 86434, 91976, 78067, 23436, 65172, 78692, 29762, 82372, 61932, 70842, 49198, 15534, 93411, 16292, 4022, 55737, 61439, 43607, 72496, 16693, 23022, 32744, 78949, 76522, 93194, 14145, 85137, 91749, 35045, 62367, 43704, 71073, 58266, 86979, 58244, 61662, 91075, 91893, 13512, 22772, 17747, 86186, 84999, 76871, 25785, 80081, 97547, 77070, 21561, 14776, 98038, 46406, 85084, 71346, 61781, 48366, 18535, 59904, 28869, 74320, 84506, 87630, 5975, 24601, 63573, 71807, 55548, 12493, 15871, 67006, 45451, 73734, 65702, 45433, 18505, 28249, 29833, 37532, 96130, 14985, 30350, 58303, 89107, 36012, 51410, 64008, 22605, 53523, 7550, 4072, 47176, 31258, 27576, 85742, 24517, 95802, 88403, 84774, 30102, 69169, 12949, 63002, 19520, 94706, 99812, 43955, 56067, 350, 50255, 29366, 36306, 83671, 87272, 39240, 26555, 64698, 31428, 12833, 28805, 80075, 40731, 36849, 47299, 79465, 83747, 29607, 8913, 21364, 4108, 65026, 17671, 95864, 95313, 96099, 62119, 40989, 86102, 36751, 14956, 48343, 16149, 54601, 50203, 45, 78385, 64822, 18267, 59949, 63962, 674, 31519, 61007, 79339, 91676, 45200, 17423, 70439, 57140, 8420, 69688, 9724, 43109, 1338, 18483, 55169, 41679, 8423, 41901, 20541, 19526, 63586, 42623, 60460, 6723, 24505, 59249, 816, 70887, 11625, 38498, 90843, 9498, 88485, 51521, 64277, 50512, 41912, 88770, 30666, 44095, 10316, 85726, 97947, 60230, 83892, 55048, 43634, 16662, 46421, 32145, 58264, 14578, 2992, 33942, 98257, 18756, 69316, 7830, 74724, 44241, 10060, 49727, 68920, 5430, 1557, 41563, 60201, 58123, 3176, 56264, 74836, 52929, 34139, 79166, 91622, 34957, 37388, 4708, 4475, 50164, 86354, 69164, 63942, 82114, 25201, 31843, 67050, 95605, 41326, 15449, 87231, 1959, 59316, 3988, 46369, 27872, 42729, 60060, 7480, 41686, 35074, 54545, 21798, 66367, 96938, 63538, 67575, 48582, 60546, 92651, 62962, 31635, 14232, 29508, 88788, 20226, 50986, 35980, 73634, 52683, 43475, 95105, 53369, 98717, 6850, 21103, 5475, 11484, 43612, 86558, 4045, 26050, 58750, 62708, 85084, 85489, 61993, 6609, 1175, 2060, 82815, 45758, 75917, 18654, 26802, 79991, 11537, 44191, 47024, 55872, 38653, 29382, 86513, 53704, 47789, 53932, 79779, 83903, 24023, 63967, 85391, 8198, 66995, 15253, 81474, 68751, 33298, 95711, 57807, 45053, 32777, 65672, 27152, 35276, 20579, 52710, 9451, 20700, 555, 58500, 32549, 43593, 71758, 62911, 45029, 4817, 87827, 13896, 90992, 12537, 81071, 69936, 87919, 59393, 41885, 65248, 24412, 30568, 18355, 93730, 15150, 87834, 53559, 94106, 53615, 76258, 43124, 76698, 67040, 77358, 53105, 38350, 98853, 72665, 64759, 45541, 11358, 8067, 18097, 14889, 84239, 31052, 96920, 89719, 60963, 22812, 51307, 23505, 97043, 88124, 37159, 79386, 37959, 44788, 84346, 86293, 50944, 59818, 90375, 34939, 53984, 72323, 909, 35449, 2025, 16957, 527, 63418, 25871, 30687, 8695, 91810, 18851, 71001, 22863, 73067, 85137, 37106, 99133, 19480, 94375, 93478, 17065, 25649, 84962, 83051, 87036, 31477, 78070, 2536, 90835, 89667, 20651, 26807, 36570, 34312, 56819, 44347, 46210, 43486, 79853, 41137, 26231, 40878, 62816, 59265, 31691, 19366, 40406, 12858, 10485, 29400, 16434, 26344, 90880, 80122, 10017, 75987, 28232, 39173, 17343, 7962, 53802, 73660, 23034, 79741, 807, 4702, 12600, 13544, 85865, 53093, 86668, 56982, 60794, 18605, 36381, 87640, 75702, 61416, 48755, 11713, 74383, 6057, 90157, 93387, 29577, 62008, 17610, 8940, 28992, 61916, 27384, 46596, 43400, 53324, 1779, 55203, 25197, 33014, 53264, 62389, 4619, 93418, 80035, 87326, 69580, 71351, 92407, 18000, 86461, 49808, 22351, 93552, 16690, 71017, 18756, 13551, 73798, 86099, 21535, 42547, 77423, 35499, 26072, 94015, 2187, 14544, 51926, 33383, 62980, 57048, 62883, 57583, 55360, 93126, 23366, 24017, 91524, 94320, 86939, 23272, 44742, 28147, 15394, 4370, 10540, 20784, 94245, 3414, 8888, 61094, 7726, 7270, 63283, 18599, 54366, 61819, 54784, 66301, 81708, 90378, 19570, 88034, 31580, 86317, 72312, 9317, 45695, 2941, 27437, 71965, 37381, 43134, 80377, 99322, 97856, 95602, 78532, 24355, 73171, 45576, 70876, 41875, 50665, 2256, 90329, 12357, 1371, 67819, 20856, 64567, 70362, 7699, 95877, 34475, 36052, 33096, 13136, 67476, 55555, 25199, 49681, 3500, 22022, 77413, 30489, 54193, 50447, 59093, 3118, 45993, 79142, 7235, 22986, 24099, 3577, 53673, 87953, 2004, 35065, 65275, 59248, 34762, 27712, 68782, 64292, 87936, 41949, 92985, 33052, 75240, 34741, 72376, 53165, 26312, 66444, 20403, 67038, 90732, 38759, 46569, 42172, 91086, 54646, 80696, 8098, 70079, 27513, 61444, 75537, 40661, 62210, 52507, 47844, 62918, 43793, 22698, 10988, 8913, 45377, 35967, 11312, 19085, 78345, 90710, 15506, 94418, 54558, 29287, 32437, 23595, 99527, 20057, 51008, 36466, 71796, 86936, 47108, 22622, 14715, 26429, 35995, 84616, 6970, 70957}, 32501082, 100000, 54573, 11116);
        System.out.println(l);
//        long l = new Solution().maximumBeauty(new int[]{18, 16, 10, 10, 5}, 10, 3, 15, 4);
//        System.out.println(l);
    }

    static class Solution {
        int n;
        long[] s;
        long[] p;

        public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
            n = flowers.length;
            s = new long[n + 1];
            p = new long[n];
            s[n] = 0;
            Arrays.sort(flowers);
            for (int i = n - 1; i >= 0; i--) {
                int diff = target - flowers[i];
                if (diff < 0) {
                    diff = 0;
                }
                s[i] = diff + s[i + 1];
            }
            int maxJ = n + 2;
            for (int i = 1; i < n; i++) {
                if (flowers[i] >= target) {
                    maxJ = i;
                    break;
                }
                p[i] = p[i - 1] + (long) (flowers[i] - flowers[i - 1]) * i;
            }
            if (flowers[0] >= target) {
                maxJ = 0;
            }
            long max = 0;
            long part;
            long left;
            long comp;
            for (int i = n; i >= 0; i--) {
                left = newFlowers - s[i];
                if (left < 0) {
                    break;
                }
                comp = (long) (n - i) * full;
                int lj = 0;
                int rj = i - 1;
                int j;
                int mj = -1;
                part = 0;
                while (lj <= rj) {
                    j = (lj + rj) / 2;
                    if (j >= maxJ) {
                        rj = j - 1;
                    } else if (left < p[j]) {
                        rj = j - 1;
                    } else {
                        mj = j;
                        lj = j + 1;
                    }
                }
                if (mj > -1) {
                    long h = (left - p[mj]) / (mj + 1) + flowers[mj];
                    if (h >= target) {
                        h = target - 1;
                    }
                    part = h * partial;
                }
                max = Math.max(max, comp + part);
            }
            return max;
        }
    }


}
