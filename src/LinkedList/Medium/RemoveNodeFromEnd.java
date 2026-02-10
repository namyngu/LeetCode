//Remove Node From End of Linked List
//        Medium
//Topics
//Company Tags
//Hints
//You are given the beginning of a linked list head, and an integer n.
//
//Remove the nth node from the end of the list and return the beginning of the list.
//
//        Example 1:
//
//Input: head = [1,2,3,4], n = 2
//
//Output: [1,2,4]
//Example 2:
//
//Input: head = [5], n = 1
//
//Output: []
//Example 3:
//
//Input: head = [1,2], n = 2
//
//Output: [2]
//Constraints:
//
//The number of nodes in the list is sz.
//1 <= sz <= 30
//        0 <= Node.val <= 100
//        1 <= n <= sz

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
public class RemoveNodeFromEnd {

    // Strategy 1:
    // Use a two past approach. First past to figure out length of linked list, second past will rearrange the links at L - n.
    // Time Complexity: O(2n)
    // RESULT: 0ms Beats 100%
    // Space Complexity: O(1)
    // RESULT: 43.49MB beats 74.78%
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;

        // First past, find length of linked list
        int len = 1;
        ListNode node1 = head;
        while (node1.next != null) {
            node1 = node1.next;
            len++;
        }

        // Edge case
        if (n == len) return head.next;


        // Second past, rearrange links at len - n
        int counter = 1;
        ListNode prev = head;
        while (counter < len - n) {
            prev = prev.next;
            counter++;
        }

        // Edge case
        if (n == 1) {
            prev.next = null;
            return head;
        }

        ListNode next = prev.next.next;     // should exist since **all edge cases** are handled
        prev.next = next;

        return head;
    }
}
