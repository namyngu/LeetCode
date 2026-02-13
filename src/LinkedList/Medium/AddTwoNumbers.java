//Add Two Numbers
//Medium
//Topics
//Company Tags
//Hints
//You are given two non-empty linked lists, l1 and l2, where each represents a non-negative integer.
//
//The digits are stored in reverse order, e.g. the number 321 is represented as 1 -> 2 -> 3 -> in the linked list.
//
//Each of the nodes contains a single digit. You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//Return the sum of the two numbers as a linked list.
//
//Example 1:
//
//
//
//Input: l1 = [1,2,3], l2 = [4,5,6]
//
//Output: [5,7,9]
//
//Explanation: 321 + 654 = 975.
//Example 2:
//
//Input: l1 = [9], l2 = [9]
//
//Output: [8,1]
//Constraints:
//
//1 <= l1.length, l2.length <= 100.
//0 <= Node.val <= 9

package LinkedList.Medium;

import LinkedList.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class AddTwoNumbers {

    // Strategy 1
    // Traverse both linked list and add the values, if value >= 10 then carry the remainder to the next node.
    // Time Complexity: O(n)
    // RESULT: 2ms
    // Space Complexity: O(n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode lsum = head;

        int remainder = 0;

        int sum = l1.val + l2.val + remainder;
        lsum.val = sum % 10;

        remainder = (int)Math.floor(sum / 10.0);
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null && l2 != null) {
            lsum.next = new ListNode(0);
            lsum = lsum.next;

            sum = l1.val + l2.val + remainder;
            lsum.val = sum % 10;

            // new remainder
            remainder = (int) Math.floor(sum / 10.0);

            // Increment
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 == null) {
            while (l2 != null) {
                lsum.next = new ListNode(0);
                lsum = lsum.next;

                sum = l2.val + remainder;
                lsum.val = sum % 10;

                // new remainder
                remainder = (int) Math.floor(sum / 10.0);

                l2 = l2.next;
            }
        } else if (l2 == null) {
            while (l1 != null) {
                lsum.next = new ListNode(0);
                lsum = lsum.next;

                sum = l1.val + remainder;
                lsum.val = sum % 10;

                // new remainder
                remainder = (int) Math.floor(sum / 10.0);

                l1 = l1.next;
            }
        }

        if (remainder > 0) {
            lsum.next = new ListNode(remainder);
        }

        return head;
    }
}
