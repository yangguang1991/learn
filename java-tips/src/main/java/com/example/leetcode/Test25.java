package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/7 17:05
 */
public class Test25 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode secondHead = null;

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        //查看到底需要更新几次
        int length = 0;
        ListNode temp1 = head;
        while (temp1 != null) {
            length++;
            temp1 = temp1.next;
        }
        int num = length / k;
        //吧所有的头结点全部保留下来放在一个list里面，然后穿插起来
        secondHead = head;
        List<ListNode> listNodeList = new ArrayList<>();
        while (num > 0) {
            num--;
            ListNode temp = reverse(secondHead, k);
            listNodeList.add(temp);
        }
        ListNode listNode = null;
        ListNode temp = null;
        listNode = listNodeList.get(0);
        for (int i = 0; i < listNodeList.size(); ) {
            temp = listNodeList.get(i);
            while (temp.next != null) {
                temp = temp.next;
            }
            if (++i < listNodeList.size()) {
                temp.next = listNodeList.get(i);
            } else {
                break;
            }
        }
        temp.next = secondHead;
        return listNode;
    }

    public static ListNode reverse(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        ListNode temp1 = head;
        ListNode temp2 = null;
        ListNode newHead = null;
        int temp = 1;
        while (temp1 != null && temp <= k) {
            temp2 = temp1.next;
            temp1.next = newHead;
            newHead = temp1;
            temp1 = temp2;
            temp++;
        }
        secondHead = temp1;//后面一段的
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode6 = new ListNode(6);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode temp = reverseKGroup(listNode1, 2);
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}















