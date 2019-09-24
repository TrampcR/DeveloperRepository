package com.trampcr.developerrepository.algorithm.leetcode;

/**
 * Created by trampcr on 2019/8/29.
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。说明：不允许修改给定的链表。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 */

public class DetectCycle142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode meetNode = findMeetNode(head);
        if (meetNode == null) {
            return null;
        }

        int cycleLength = 1;
        ListNode preNode = meetNode;

        while (preNode.next != meetNode) {
            preNode = preNode.next;
            cycleLength++;
        }

        preNode = head;
        ListNode postNode = head;
        for (int i = 0; i < cycleLength; i++) {
            postNode = postNode.next;
        }

        while (preNode != postNode) {
            preNode = preNode.next;
            postNode = postNode.next;
        }

        return preNode;
    }

    private ListNode findMeetNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slowNode = head.next;
        if (slowNode == null) {
            return null;
        }

        ListNode fastNode = slowNode.next;

        while (slowNode != null && fastNode != null) {
            if (slowNode == fastNode) {
                return slowNode;
            }

            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode != null) {
                fastNode = fastNode.next;
            }
        }

        return null;
    }
}
