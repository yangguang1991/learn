package com.example.leetcode;

/**
 * @Author: kevin yang
 * @Description:
 * @Date: create in 2020/9/3 14:47
 */
public class Test23 {


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode listNode = new ListNode();
        if (lists.length == 0) {
            return listNode;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        int currentNum = Integer.MIN_VALUE;
        int index = 0;//当前最小的
        int num = lists.length;

        for (int i = 0; i < lists.length; i++) {
//            for () {
//
//            }
        }

        return listNode;
    }

    public static void main(String[] args) {

    }

}
