package com.harley.io.oj;

public class Solution {
    public ListNode reverseList(ListNode head) {
        //return null;
        if(head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
