package com.trampcr.developerrepository.algorithm.leetcode;

/**
 * Created by trampcr on 2019/8/27.
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。说明:1 ≤ m ≤ n ≤ 链表长度。
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 1、找到要反转的第一个结点和其前驱结点
 * 2、从要反转的第一个结点开始反转，直到要反转的最后一个结点
 * 3、返回整个链表
 */

public class ReverseBetween92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }

        ListNode resultNode = new ListNode(0);
        resultNode.next = head;

        ListNode preNode = resultNode;
        // 找到要反转的第一个结点的前驱结点
        for (int i = 1; i < m; i++) {
            preNode = preNode.next;
        }

        // 找到要反转的第一个结点
        head = preNode.next;

        // 从要反转的第一个结点开始反转，直到要反转的最后一个结点
        for (int i = m; i < n; i++) {
            ListNode tempNode = head.next;
            head.next = tempNode.next;
            tempNode.next = preNode.next;
            preNode.next = tempNode;
        }

        return resultNode.next;
    }
}
