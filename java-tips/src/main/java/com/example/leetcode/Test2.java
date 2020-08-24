package com.example.leetcode;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/2/14  19:05
 */
public class Test2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int temp = 0;
        int t1 = 0;
        ListNode head = null;
        ListNode tail = null;
        ListNode temp_head = null;
        while (l1 != null || l2 != null) {
            temp = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            //找到十位数和个位数
            temp = temp + t1;
            int t2 = temp % 10;
            temp_head = new ListNode(t2);

            if (head == null) {
                head = temp_head;
                tail = head;
            } else {
                tail.next = temp_head;
                tail = temp_head;
            }
            t1 = temp / 10;
        }
        //链表都遍历完，若有进位，也会继续往上加上
        if (t1 != 0) {
            temp_head = new ListNode(t1);
            tail.next = temp_head;
        }
        return head;
    }

    //    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(6);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;


        ListNode temp = addTwoNumbers(l1, n1);

        while (temp != null) {

            System.out.println(temp.val);
            temp = temp.next;
        }

    }
}
