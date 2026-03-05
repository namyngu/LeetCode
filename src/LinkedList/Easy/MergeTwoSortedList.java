//21. Merge Two Sorted Lists
//Easy
//        Topics
//premium lock icon
//        Companies
//You are given the heads of two sorted linked lists list1 and list2.
//
//Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
//
//Return the head of the merged linked list.
//
//
//
//Example 1:
//
//
//Input: list1 = [1,2,4], list2 = [1,3,4]
//Output: [1,1,2,3,4,4]
//Example 2:
//
//Input: list1 = [], list2 = []
//Output: []
//Example 3:
//
//Input: list1 = [], list2 = [0]
//Output: [0]
//
//
//Constraints:
//
//The number of nodes in both lists is in the range [0, 50].
//        -100 <= Node.val <= 100
//Both list1 and list2 are sorted in non-decreasing order.

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
package LinkedList.Easy;

import LinkedList.ListNode;

public class MergeTwoSortedList {

    // Strategy 1:
    // Time Complexity: O(n + m) where n and m is the length of list1 and list2
    // RESULT: 0ms Beats 100%
    // Space Complexity: O(1) - 44.3MB beats 71.47%
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Edge case
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }


        ListNode head;
        ListNode currNode1 = list1;
        ListNode currNode2 = list2;

        if (currNode1.val < currNode2.val) {
            head = list1;
            currNode1 = currNode1.next;
        } else {
            head = list2;
            currNode2 = currNode2.next;
        }

        mergeNode(head, currNode1, currNode2);

        return head;
    }

    public void mergeNode(ListNode prevNode, ListNode currNode1, ListNode currNode2) {
        // End of line
        if (currNode1 == null) {
            prevNode.next = currNode2;
            return;
        } else if (currNode2 == null) {
            prevNode.next = currNode1;
            return;
        }

        if (currNode1.val < currNode2.val) {
            prevNode.next = currNode1;
            mergeNode(currNode1, currNode1.next, currNode2);
        } else {
            prevNode.next = currNode2;
            mergeNode(currNode2, currNode1, currNode2.next);
        }
    }
}
