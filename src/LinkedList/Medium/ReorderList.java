//You are given the head of a singly linked-list. The list can be represented as:
//
//L0 → L1 → … → Ln - 1 → Ln
//Reorder the list to be on the following form:
//
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//You may not modify the values in the list's nodes. Only nodes themselves may be changed.
//
//
//
//Example 1:
//
//
//Input: head = [1,2,3,4]
//Output: [1,4,2,3]
//Example 2:
//
//
//Input: head = [1,2,3,4,5]
//Output: [1,5,2,4,3]
//
//
//Constraints:
//
//The number of nodes in the list is in the range [1, 5 * 104].
//        1 <= Node.val <= 1000

package LinkedList.Medium;

import LinkedList.CreateLinkedList;
import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

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

// Good question to revisit/review your understanding of pointers!
public class ReorderList {
    int size = 1;

    public static void main(String[] args) {
        CreateLinkedList input = new CreateLinkedList(new int[]{1,2,3,4,5});

        ReorderList start = new ReorderList();
        start.reorderList2(input.head);
    }

    // Strategy 3:
    // split the list in half, find middle by using fast & slow pointer.
    // reverse the second half of the list.
    // merge the two halves
    public void reorderList3(ListNode head) {

    }



    // Strategy 2
    // Similar to strat 1 except there's no 3rd list, we create a reverse list and traverse the main list, creating new links everytime
    // Time Complexity: O(n)
    // RESULT: 3ms - Beats 17.27%
    // Space Complexity: O(n)
    // RESULT: 51.35Mb beats 6.5%
    public void reorderList2(ListNode head) {

        //Create reverse list
        ListNode tail2 = new ListNode(head.val, null);
        ListNode head2 = createReverseList(tail2, head.next);

        ListNode currNode1 = head.next;      // current node in list 1
        ListNode currNode2 = head2;          // current node in list 2
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                // even
                head.next = currNode2;
                currNode2 = currNode2.next;
            }
            else {
                // odd
                head.next = currNode1;
                currNode1 = currNode1.next;
            }

            head = head.next;
        }

        head.next = null;
    }

    // Strategy 1: Create a second list that starts at the tail and goes backwards
    // So there's two lists, list1 and list2 and correspondingly, node1 and node2.
    // list1: 1 -> 2 -> 3 -> 4 -> 5
    // list2: 5 -> 4 -> 3 -> 2 -> 1
    // list3: 1 -> 5 -> 2 -> 4 -> 3
    // WRONG ANSWER - have to edit the links in the existing list! (Basically list3 is illegal)
    public void reorderList(ListNode head) {

        ListNode tail2 = new ListNode(head.val, null);
        ListNode head2 = createReverseList(tail2, head.next);


        // Create a 3rd reordered list
        ListNode head3 = new ListNode(head.val, null);

        ListNode node1 = head.next; // current node in 1st list
        ListNode node2 = head2;     // current node in 2nd list
        ListNode node3 = head3;     // current node in 3rd list
        for (int i = 0; i < this.size; i++) {
            if (i % 2 == 0) {
                // even
                node3.next = new ListNode(node2.val, null);
                node2 = node2.next;
            }
            else {
                // odd
                node3.next = new ListNode(node1.val, null);
                node1 = node1.next;
            }

            node3 = node3.next;
        }
        head = head3;
    }

    // Creates a reverse linked list
    public ListNode createReverseList(ListNode prevNode2, ListNode nextNode1) {
        if (nextNode1 == null) {
            return prevNode2;
        }

        ListNode nextNode2 = new ListNode(nextNode1.val, prevNode2);
        this.size++;
        return createReverseList(nextNode2, nextNode1.next);
    }
}
