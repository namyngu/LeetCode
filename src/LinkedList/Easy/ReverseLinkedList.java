package LinkedList.Easy;
//206. Reverse Linked List
//        Easy
//Topics
//premium lock icon
//        Companies
//Given the head of a singly linked list, reverse the list, and return the reversed list.
//
//
//
//        Example 1:
//
//
//Input: head = [1,2,3,4,5]
//Output: [5,4,3,2,1]
//Example 2:
//
//
//Input: head = [1,2]
//Output: [2,1]
//Example 3:
//
//Input: head = []
//Output: []
//
//
//Constraints:
//
//The number of nodes in the list is the range [0, 5000].
//        -5000 <= Node.val <= 5000
//
//
//Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

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


public class ReverseLinkedList {

    // Strategy 1:
    // Using recursion
    // Time Complexity: O(n) where n is length of linked list.
    // RESULT: 0ms - Beats 100% (most ppl got this).
    public ListNode reverseList(ListNode head) {

        // Edge case
        if (head == null) {
            return head;
        }
        else if (head.next == null) {
            return head;
        }
        else if (head.next.next == null) {
            head.next.next = head;
            ListNode tmpNode = head.next;
            head.next = null;
            return tmpNode;
        }

        // 1 -> 2 -> 3
        ListNode tmpThirdNode = head.next.next;

        head.next.next = head;      // 2nd node points to 1st

        ListNode tmpSecondNode = head.next;
        head.next = null;           // 1st node points to null

        return reverseNode(tmpSecondNode, tmpThirdNode);
    }

    // prevNode <- currNode     nextNode -> ...
    public ListNode reverseNode (ListNode currNode, ListNode nextNode) {
        if (nextNode == null) {
            return currNode;
        }

        ListNode tmpNextNextNode = nextNode.next;
        nextNode.next = currNode;

        return reverseNode(nextNode, tmpNextNextNode);
    }
}
