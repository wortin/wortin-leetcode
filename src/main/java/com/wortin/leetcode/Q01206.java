package com.wortin.leetcode;

import java.util.Random;

/**
 * 设计跳表
 * <p>
 * 我发现有这样的数据结构：
 * <pre>
 *     Node {
 *         T val;
 *         Node[] forward = new Node[MAX_LEVEL];
 *     }
 * </pre>
 * 在Redis、leetcode官方解答中出现，它们遵从了William Pugh的论文Skip Lists:
 * <a href="https://15721.courses.cs.cmu.edu/spring2018/papers/08-oltpindexes1/pugh-skiplists-cacm1990.pdf">
 * A Probabilistic Alternative to Balanced Trees
 * </a>版本的设计
 * </p>
 * <p>
 * 我看到还有这样的数据结构：
 * <pre>
 *     Node  {
 *          T val;
 *          Node next
 *     }
 *     Index {
 *          Node node;
 *          Index down;
 *          Index right;
 *     }
 * </pre>
 * 这是 ConcurrentSkipListMap 的数据结构，来自 Doug Lea 的实现
 * 和我最初想的一致，我不希望在每个节点中固定给到MAX_LEVEL的数组，那样看起来很浪费空间
 * 而 Doug Lea在源码中解释道，采用树状的二维链接跳表（特别地，索引与存储数据的基础节点分离单独表示）而不是常见的基于数组的结构，主要有2个原因：
 * <li>数组会带来更多的复杂性和开销</li>
 * <li>对于频繁遍历的索引列表，我们可以使用比基础列表更高效的算法</li>
 * 我理解第一点解释了为什么不用常见的数组结构；第二点解释了为什么单独用一个索引结构来表示上层索引。
 * 因为他是要实现并发场景下的跳表，所以把索引链表和数据链表分开可以更高效的实现并发的改动。
 * 但是我这里就不用考虑并发，就不单独分开了，我直接都是Node的链表。
 * </p>
 *
 * <p>
 * 我这个实现，leetcdoe大概是273ms，简直太慢了，相差了别人20倍
 * </p>
 * <p>为了提高性能，我尝试把p降低为1/4，性能有了提升，200ms，但是仍然不太行，看来还是有本质的差距</p>
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01206 {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(1));
        System.out.println(skiplist.search(5));
    }

    static class Skiplist {
        private static final int MAX_LEVEL = 16;

        private final Random random = new Random();

        /**
         * 这个总是占位用的，不存储数据
         */
        Node[] heads = new HeadNode[MAX_LEVEL];

        public Skiplist() {
            for (int i = 0; i < MAX_LEVEL; i++) {
                heads[i] = new HeadNode();
            }
            for (int i = 0; i < MAX_LEVEL - 1; i++) {
                heads[i + 1].down = heads[i];
            }
        }

        public boolean search(int target) {
            Node preNode = heads[MAX_LEVEL - 1];
            Node nodeToInsertBehind;
            while (true) {
                nodeToInsertBehind = findNodeToInsertBehind(preNode, target);
                if (nodeToInsertBehind.next != null && nodeToInsertBehind.next.val == target) {
                    return true;
                }
                if (nodeToInsertBehind.down != null) {
                    preNode = nodeToInsertBehind.down;
                } else {
                    break;
                }
            }
            return false;
        }

        public void add(int num) {
            // 对这个数据，随机生成一个层高
            int randLevel = randLevel();
            // 从这个层高开始，依次往下 0层-randLevel层（最大值15）
            Node prevNode = heads[randLevel];
            Node upNode = null;
            // 从randLevel这个
            for (int i = randLevel; i >= 0; i--) {
                Node node = new Node(num);
                if (upNode != null) {
                    upNode.down = node;
                }
                // 对每层来说，都是一个单独的链表要插入
                Node nodeToInsertBehind = findNodeToInsertBehind(prevNode, num);
                Node next = nodeToInsertBehind.next;
                nodeToInsertBehind.next = node;
                node.next = next;
                // 下一层出发的节点
                prevNode = prevNode.down;
                // 记录上层节点，方便维护down
                upNode = node;
            }
        }

        /**
         * 首先入参prev肯定不是null
         * 然后返回值的next要么是null，要么它的值>=val
         *
         * @param prev 随便给一个节点，一般初始情况是head
         * @param val  目标值
         * @return nodeToInsertBehind 它一定不是null，它的next要么是null，要么值>=val；
         * 至于nodeToInsertBehind本身的值如何，它可能是head；它也可能是中间某个节点，此时根据查找逻辑，它的值一定<val的，它的next一定是>=val
         */
        private Node findNodeToInsertBehind(Node prev, int val) {
            Node current = prev.next;
            while (current != null && current.val < val) {
                prev = current;
                current = current.next;
            }
            return prev;
        }


        public boolean erase(int num) {
            Node preNode = heads[MAX_LEVEL - 1];
            Node nodeToInsertBehind;
            boolean found = false;
            // 对每一层
            while (true) {
                nodeToInsertBehind = findNodeToInsertBehind(preNode, num);
                // 在这层找到了要删除的元素，就直接删了
                if (nodeToInsertBehind.next != null && nodeToInsertBehind.next.val == num) {
                    found = true;
                    nodeToInsertBehind.next = nodeToInsertBehind.next.next;
                }
                if (nodeToInsertBehind.down != null) {
                    preNode = nodeToInsertBehind.down;
                } else {
                    break;
                }
            }
            return found;
        }

        private int randLevel() {
            int level = 0;
            // 采用 1/2 的晋升概率
            while (random.nextInt(2) == 0 && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }

        /**
         * 这本质就是二叉树的节点
         */
        private class Node {
            int val;
            Node next;
            Node down;

            Node(int val) {
                this.val = val;
            }
        }

        private class HeadNode extends Node {
            HeadNode() {
                super(0);
            }
        }
    }
}
