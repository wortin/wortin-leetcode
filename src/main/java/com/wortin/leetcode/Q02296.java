package com.wortin.leetcode;

/**
 * 请你设计一个带光标的文本编辑器，它可以实现以下功能：
 * <p>
 * 添加：在光标所在处添加文本。
 * 删除：在光标所在处删除文本（模拟键盘的删除键）。
 * 移动：将光标往左或者往右移动。
 * 当删除文本时，只有光标左边的字符会被删除。光标会留在文本内，也就是说任意时候 0 <= cursor.position <= currentText.length 都成立。
 * <p>
 * 请你实现 TextEditor 类：
 * <p>
 * TextEditor() 用空文本初始化对象。
 * void addText(string text) 将 text 添加到光标所在位置。添加完后光标在 text 的右边。
 * int deleteText(int k) 删除光标左边 k 个字符。返回实际删除的字符数目。
 * string cursorLeft(int k) 将光标向左移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的字符数目。
 * string cursorRight(int k) 将光标向右移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的字符数目。
 * <p>
 * 提示：
 * <p>
 * 1 <= text.length, k <= 40
 * text 只含有小写英文字母。
 * 调用 addText ，deleteText ，cursorLeft 和 cursorRight 的 总 次数不超过 2 * 104 次。
 * <p>
 * <p>
 * 进阶：你能设计并实现一个每次调用时间复杂度为 O(k) 的解决方案吗？
 * <p>
 * 题目说时间复杂度O(k)
 * 那么用char[]或String[]来存储是不行的，因为中间插入后，后面的元素要往后移
 * 为了快速在中间插入，直接的想法是链表,LinkedList<>，然后是双向链表，因为要左右移
 * 可以先试一下
 * 我先直接用字符串拼接来一下行吗？
 * 直接字符串不行，超时了
 * 然后用StringBuilder，过了，用时320ms
 * 看到StringBuilder源码实现，本质是char[]，插入的时候，要扩容, System.arraycopy
 * 然后看官方解答：确实用 Node { next prev char}
 * 然后还有一种更妙的，用两个栈，Deque<Char> 代表光标左边的字符，光标右边的字符；实际代码用两个StringBuilder更快
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q02296 {
    public static void main(String[] args) {

    }

    class TextEditor {

        StringBuilder t = new StringBuilder();
        int cur;

        public TextEditor() {
            cur = 0; // 表示光标在-1和0之间的位置，如果插入放0，如果删除删-1
        }

        public void addText(String text) {
            t.insert(cur, text);
            cur += text.length();
        }

        public int deleteText(int k) {
            if (cur < k) {
                k = cur;
            }
            t.delete(cur - k, cur);
            cur -= k;
            return k;
        }

        public String cursorLeft(int k) {
            if (cur < k) {
                cur = 0;
                return "";
            }
            cur -= k;
            if (cur < 10) {
                return t.substring(0, cur);
            }
            return t.substring(cur - 10, cur);
        }

        public String cursorRight(int k) {
            if (cur + k > t.length()) {
                cur = t.length();
            } else {
                cur += k;
            }
            if (cur < 10) {
                return t.substring(0, cur);
            }
            return t.substring(cur - 10, cur);
        }
    }


//    static class TextEditor {
//
//        String t = "";
//        int cur;
//
//        public TextEditor() {
//            cur = 0; // 表示光标在-1和0之间的位置，如果插入放0，如果删除删-1
//        }
//
//        public void addText(String text) {
//            t = (t.substring(0, cur) + text + t.substring(cur));
//            cur += text.length();
//        }
//
//        public int deleteText(int k) {
//            if (cur < k) {
//                k = cur;
//            }
//            t = (t.substring(0, cur - k) + t.substring(cur));
//            cur -= k;
//            return k;
//        }
//
//        public String cursorLeft(int k) {
//            if (cur < k) {
//                cur = 0;
//                return "";
//            }
//            cur -= k;
//            if (cur < 10) {
//                return t.substring(0, cur);
//            }
//            return t.substring(cur - 10, cur);
//        }
//
//        public String cursorRight(int k) {
//            if (cur + k > t.length()) {
//                cur = t.length();
//            } else {
//                cur += k;
//            }
//            if (cur < 10) {
//                return t.substring(0, cur);
//            }
//            return t.substring(cur - 10, cur);
//        }
//    }

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */
}
