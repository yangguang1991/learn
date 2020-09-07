package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/6 9:46
 */
public class Test19 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        List<ListNode> list = new ArrayList<>();
        ListNode index=head;
        while (index != null) {
            list.add(index);
            index = index.next;
        }
        if (n == list.size()) {//如果删除的是头结点
            head = head.next;
            return head;
        } else {
            ListNode pre = list.get(list.size() - n - 1);//被删除的前一个节点
            ListNode temp = pre.next.next;
            pre.next = temp;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode head = listNode1;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        head = removeNthFromEnd(listNode1, 1);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }
}
