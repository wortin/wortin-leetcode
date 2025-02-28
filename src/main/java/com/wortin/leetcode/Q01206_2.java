package com.wortin.leetcode;

import java.util.Random;

/**
 * 这种横竖双指针的跳表，leetcode上执行大概是14ms
 *
 * @author wortin zhangdingwen.zdw@alibaba-inc.com
 */
public class Q01206_2 {
    public static void main(String[] args) {

    }


    class Skiplist {
        class Node {
            int value;
            Node horizonPre;
            Node horizonNext;
            Node vertPre;
            Node vertNext;

            public Node() {
                this.value = -1;
            }

            public Node(int value) {
                this.value = value;
            }
        }

        Node dummyHead;
        float P_FACTOR;
        Random RANDOM;
        int MAX_LEVEL;

        public Skiplist() {
            dummyHead = new Node();
            P_FACTOR = 0.25f;
            RANDOM = new Random();
            MAX_LEVEL = 32;
        }

        public boolean search(int target) {
            Node curNode = dummyHead;
            do {
                if (curNode.horizonNext == null || curNode.horizonNext.value > target) {
                    // 已经到了当前层尾部，或者碰到了更大的值，需要下潜
                    curNode = curNode.vertNext;
                } else if (curNode.horizonNext.value == target) {
                    // 到了当前层第一个等于目标值的节点，已经找到了目标值，返回
                    return true;
                } else {
                    curNode = curNode.horizonNext;
                }
            } while (curNode != null && curNode.value < target);
            // 此时出了循环，说明已经下潜到null层，没有找到目标值
            return false;
        }

        public void add(int num) {
            Node preNode = findPreNodeToInsert(num);
            Node newNode = new Node(num);
            int lv = getRandomLevel();
            growNodeInVertDir(newNode, lv);
            insertNodeInHorizonDir(newNode, preNode);
        }

        public boolean erase(int num) {
            Node preNode = findPreNodeToInsert(num);
            if (preNode.horizonNext == null || preNode.horizonNext.value != num) {
                return false;
            }
            deleteNodeInHorizonDir(preNode.horizonNext);
            return true;
        }

        private int getRandomLevel() {
            int res = 1;
            while (RANDOM.nextDouble() < P_FACTOR && res < MAX_LEVEL) {
                res++;
            }
            return res;
        }

        private void growNodeInVertDir(Node node, int lv) {
            int value = node.value;
            Node preVertNode = node;
            for (int i = 1; i < lv; i++) {
                Node newNode = new Node(value);
                preVertNode.vertPre = newNode;
                newNode.vertNext = preVertNode;
                preVertNode = newNode;
            }
        }

        private void insertNodeInHorizonDir(Node newNode, Node preNode) {
            while (newNode != null) {
                newNode.horizonPre = preNode;
                newNode.horizonNext = preNode.horizonNext;
                if (preNode.horizonNext != null) {
                    preNode.horizonNext.horizonPre = newNode;
                }
                preNode.horizonNext = newNode;
                newNode = newNode.vertPre;
                if (newNode != null) {
                    preNode = getVertPreNodeForOldNode(preNode);
                }
            }
        }

        private void deleteNodeInHorizonDir(Node deleteNode) {
            while (deleteNode != null) {
                if (deleteNode.horizonNext != null) {
                    deleteNode.horizonNext.horizonPre = deleteNode.horizonPre;
                }
                deleteNode.horizonPre.horizonNext = deleteNode.horizonNext;
                deleteNode.horizonNext = null;
                deleteNode.horizonPre = null;
                deleteNode = deleteNode.vertPre;
            }
        }

        private Node getVertPreNodeForOldNode(Node node) {
            do {
                if (node.vertPre != null) {
                    return node.vertPre;
                } else {
                    node = node.horizonPre;
                }
            } while (node != null);

            // 到这里说明一直前溯到dummyHead还是没找到上一层的node,说明需要grow dummyHead和dummyTail了
            Node newDummyHeadNode = new Node();
            dummyHead.vertPre = newDummyHeadNode;
            newDummyHeadNode.vertNext = dummyHead;
            dummyHead = newDummyHeadNode;
            return dummyHead;
        }

        // 找到第一层的最后一个小于target的node
        private Node findPreNodeToInsert(int target) {
            Node curNode = dummyHead;
            Node preNode;
            do {
                preNode = curNode;
                if (curNode.horizonNext == null || curNode.horizonNext.value >= target) {
                    // 已经到了当前层尾部，或者碰到了第一个不小于目标值的值，需要下潜
                    curNode = curNode.vertNext;
                } else {
                    curNode = curNode.horizonNext;
                }
            } while (curNode != null && curNode.value < target);
            // 此时出了循环，说明已经下潜到null层，没有找到目标值
            return preNode;
        }

        private void printLinkList() {
            Node curNode = dummyHead;
            System.out.println("--------------------------------");
            while (curNode != null) {
                printSingleLevel(curNode);
                curNode = curNode.vertNext;
            }
            System.out.println("--------------------------------");
        }

        private void printSingleLevel(Node node) {
            while (node != null) {
                System.out.printf(Integer.toString(node.value) + "->");
                node = node.horizonNext;
            }
            System.out.println("null");
        }
    }
}
