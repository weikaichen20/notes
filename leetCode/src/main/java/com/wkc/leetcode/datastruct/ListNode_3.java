package com.wkc.leetcode.datastruct;


/**
 * Created on 2022/3/24.
 *
 * @author Weikaichen
 */
@SuppressWarnings("all")
public class ListNode_3 {
    /* 将单向链表按某值划分为左边小，中间相等，右边大的形式
     *
     * 【题目】给定一个头节点head，节点的值类型是整数，在给定一个整数pivot，实现
     *  一个调整链表的函数，将链表调整为左部份都是值小于pivot的节点，中间部分的值等于
     * pivot的节点，有部分的值大于pivot的节点
     *
     *
     * 【进阶】
     * 【要求】调整后相对顺序一致  类似排序稳定性
     * 【要求】时间复杂度达到O(N),额外空间复杂度达到O(1)
     * */


    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node ListPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];

    }

    private static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    private static void swap(Node[] nodeArr, int a, int b) {
        Node temp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = temp;
    }

    public static Node ListPartition2(Node head, int pivot) {
        Node sH = null;//small head
        Node sT = null;//small tail
        Node eH = null;//equal head
        Node eT = null;//equal tail
        Node bH = null;//big head
        Node bT = null;//big tail
        Node next = null;//save next node
        //every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }
}
