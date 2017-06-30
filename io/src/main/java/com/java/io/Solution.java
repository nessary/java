package com.java.io;

import java.util.Stack;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Solution {
    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode last = null;
        while (head != null) {
            nodeStack.push(head);
            last = head;
            head = head.next;
            last.next = null;
        }

        //System.out.println(nodeStack.size());
        ListNode res = new ListNode(0);

        ListNode listNode = res;

        while (!nodeStack.isEmpty()){
            ListNode tmp = nodeStack.pop();

            res.next = tmp;
            res = tmp;

        }

        return listNode.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);

        listNode.next = listNode1;
        listNode1.next = listNode2;

        ListNode res = solution.ReverseList(listNode);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}