package com.wortin.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 设计一个支持下述操作的食物评分系统：
 * <p>
 * 修改 系统中列出的某种食物的评分。
 * 返回系统中某一类烹饪方式下评分最高的食物。
 * 实现 FoodRatings 类：
 * <p>
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) 初始化系统。食物由 foods、cuisines 和 ratings 描述，长度均为 n 。
 * foods[i] 是第 i 种食物的名字。
 * cuisines[i] 是第 i 种食物的烹饪方式。
 * ratings[i] 是第 i 种食物的最初评分。
 * void changeRating(String food, int newRating) 修改名字为 food 的食物的评分。
 * String highestRated(String cuisine) 返回指定烹饪方式 cuisine 下评分最高的食物的名字。如果存在并列，返回 字典序较小 的名字。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2 * 104
 * n == foods.length == cuisines.length == ratings.length
 * 1 <= foods[i].length, cuisines[i].length <= 10
 * foods[i]、cuisines[i] 由小写英文字母组成
 * 1 <= ratings[i] <= 108
 * foods 中的所有字符串 互不相同
 * 在对 changeRating 的所有调用中，food 是系统中食物的名字。
 * 在对 highestRated 的所有调用中，cuisine 是系统中 至少一种 食物的烹饪方式。
 * 最多调用 changeRating 和 highestRated 总计 2 * 104 次
 * <p>
 * 本题首先想到的是用最大堆，始终能O(1)的查到评分最高的食物，然后改评分的时候时间复杂度是logN？
 * 然后这里要注意，我是根据烹饪方式分类的，这个烹饪方式下评分最高
 * 但是烹饪方式有太多个，那就是Map<String,PriorityQueue<Food>> 然后我要维护Map<String,Food>这样能更新Food.rate
 * 注意，这里有优化点，否则超时。那就是去改评分的时候，不去移除队列的元素，而是直接插入。等到查询的时候，如果top是老的，删掉直到新的
 */
public class Q02353 {

    class FoodRatings {
        Map<String, PriorityQueue<Food>> cuisine2FoodRatingQueueMap = new HashMap<>();
        Map<String, Food> foodMap = new HashMap<>();

        class Food implements Comparable<Food> {
            String name;
            String cuisine;
            int rating;

            public Food(String name, String cuisine, int rating) {
                this.name = name;
                this.cuisine = cuisine;
                this.rating = rating;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Food food = (Food) o;
                return Objects.equals(name, food.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name);
            }

            @Override
            public int compareTo(Food o) {
                int rc = Integer.compare(o.rating, rating);
                if (rc != 0) {
                    return rc;
                }
                return name.compareTo(o.name);
            }
        }

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; i++) {
                String name = foods[i];
                int rating = ratings[i];
                String cuisine = cuisines[i];
                cuisine2FoodRatingQueueMap.compute(cuisine, (k, v) -> {
                    if (v == null) {
                        v = new PriorityQueue<>();
                    }
                    v.add(new Food(name, cuisine, rating));
                    return v;
                });
                foodMap.put(name, new Food(name, cuisine, rating));
            }
        }

        public void changeRating(String food, int newRating) {
            Food f = foodMap.get(food);
            f.rating = newRating;
            PriorityQueue<Food> queue = cuisine2FoodRatingQueueMap.get(f.cuisine);
            queue.add(new Food(f.name, f.cuisine, f.rating));
        }

        public String highestRated(String cuisine) {
            PriorityQueue<Food> queue = cuisine2FoodRatingQueueMap.get(cuisine);
            Food tf = queue.peek();
            while (foodMap.get(tf.name).rating != tf.rating) {
                queue.remove(tf);
                tf = queue.peek();
            }
            return tf.name;
        }
    }

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

}
