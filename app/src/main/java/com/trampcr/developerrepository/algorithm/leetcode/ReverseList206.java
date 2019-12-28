package com.trampcr.developerrepository.algorithm.leetcode;

/**
 * Created by trampcr on 2019/8/26.
 *
 * 反转一个单链表
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */

public class ReverseList206 {
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preNode = head;
        ListNode currNode = head.next;
        ListNode postNode = currNode.next;

        while (postNode != null) {
            currNode.next = preNode;
            preNode = currNode;
            currNode = postNode;
            postNode = postNode.next;
        }

        currNode.next = preNode;
        head.next = null;

        return currNode;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preNode = null;
        ListNode currNode = head;

        while (currNode.next != null) {
            ListNode tempNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = tempNode;
        }

        currNode.next = preNode;
        return currNode;
    }
}
