package com.trampcr.developerrepository.algorithm.leetcode;

/**
 * Created by trampcr on 2019/8/28.
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 */

public class HasCycle141 {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slowNode = head.next;
        if (slowNode == null) {
            return false;
        }

        ListNode fastNode = slowNode.next;

        while (fastNode != null && slowNode != null) {
            if (slowNode == fastNode) {
                return true;
            }

            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if (fastNode != null) {
                fastNode = fastNode.next;
            }
        }

        return false;
    }
}
