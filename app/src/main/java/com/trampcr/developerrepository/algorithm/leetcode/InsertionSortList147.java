package com.trampcr.developerrepository.algorithm.leetcode;

/**
 * Created by trampcr on 2019/9/3.
 *
 * 对链表进行插入排序。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 */

public class InsertionSortList147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode markNode = new ListNode(0);
        markNode.next = head;

        while (head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
                continue;
            }

            ListNode preNode = markNode;
            while (preNode.next.val <= head.next.val) {
                preNode = preNode.next;
            }

            ListNode currNode = head.next;
            head.next = currNode.next;
            currNode.next = preNode.next;
            preNode.next = currNode;
        }

        return markNode.next;
    }
}
