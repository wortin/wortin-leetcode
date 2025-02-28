package com.wortin.leetcode;

/**
 * 你有一个只支持单个标签页的 浏览器 ，最开始你浏览的网页是 homepage ，你可以访问其他的网站 url ，也可以在浏览历史中后退 steps 步或前进 steps 步。
 * <p>
 * 请你实现 BrowserHistory 类：
 * <p>
 * BrowserHistory(string homepage) ，用 homepage 初始化浏览器类。
 * void visit(string url) 从当前页跳转访问 url 对应的页面  。执行此操作会把浏览历史前进的记录全部删除。
 * string back(int steps) 在浏览历史中后退 steps 步。如果你只能在浏览历史中后退至多 x 步且 steps > x ，那么你只后退 x 步。请返回后退 至多 steps 步以后的 url 。
 * string forward(int steps) 在浏览历史中前进 steps 步。如果你只能在浏览历史中前进至多 x 步且 steps > x ，那么你只前进 x 步。请返回前进 至多 steps步以后的 url 。
 * <p>
 * 提示：
 * <p>
 * 1 <= homepage.length <= 20
 * 1 <= url.length <= 20
 * 1 <= steps <= 100
 * homepage 和 url 都只包含 '.' 或者小写英文字母。
 * 最多调用 5000 次 visit， back 和 forward 函数。
 */
public class Q01472 {
    public static void main(String[] args) {

    }

    static class BrowserHistory {
        String[] his;
        int cur;
        int tail;

        public BrowserHistory(String homepage) {
            his = new String[5000];
            his[0] = homepage;
            cur = 0;
            tail = 0;
        }

        public void visit(String url) {
            cur++;
            his[cur] = url;
            tail = cur;
        }

        public String back(int steps) {
            cur -= steps;
            if (cur < 0) {
                cur = 0;
            }
            return his[cur];
        }

        public String forward(int steps) {
            cur += steps;
            if (cur > tail) {
                cur = tail;
            }
            return his[cur];
        }
    }

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
}
