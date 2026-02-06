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


public class ReorderList {

    public static void main(String[] args) {
        CreateLinkedList input = new CreateLinkedList(new int[]{1,2,3,4,5});

        ReorderList start = new ReorderList();
        start.reorderList(input.head);
    }

    // 1 -> 2 -> 3 -> 4         reordered   1 -> 4 -> 2 -> 3
    // 1 -> 2 -> 3 -> 4 -> 5    reordered   1 -> 5 -> 2 -> 4 -> 3
    // Strategy 2:
    // Create an arraylist of nodes and simply insert the nodes in the index corresponding to its location.
    // To do this we first need to know the size of the linked list.
    public void reorderList2(ListNode head) {
        // calculate size of linked list
        int size = 0;
        ListNode tmp = new ListNode(head.val, head.next);
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }

        List<ListNode> list = new ArrayList<>();
        tmp = head;
        for (int i = 0; i < Math.ceil(size / 2.0); i++) {
            list.add(i * 2, tmp);
            tmp = tmp.next;
        }

        for (double i = Math.ceil(size / 2.0); i < size; i++) {


        }

    }

    // Strategy 1: Create a second list that starts at the tail and goes backwards
    // So there's two lists, list1 and list2 and correspondingly, node1 and node2.
    // list1: 1 -> 2 -> 3 -> 4
    // list2: 4 -> 3 -> 2 -> 1
    public void reorderList(ListNode head) {

        ListNode tail2 = new ListNode(head.val, null);
        ListNode head2 = createReverseList(tail2, head.next);


        // Create a 3rd reordered list  TODO: Can be optimized by not using a 3rd list but instead rearranging the links.
        ListNode head3 = new ListNode(head.val, null);

        ListNode node1 = head.next;
        ListNode node2 = head2;
        ListNode node3 = head3;
        int counter = 2;
        while (node1 != null) {
            if (counter % 2 == 0) {
                // even
                node3.next = node2;
                node2 = node2.next;
            }
            else {
                // odd
                node3.next = node1;
                node1 = node1.next;
            }

            node3 = node3.next;
            counter++;
        }

        node3.next = node2;
        head = head3;
    }

    public ListNode createReverseList(ListNode prevNode2, ListNode nextNode1) {
        if (nextNode1 == null) {
            return prevNode2;
        }

        ListNode nextNode2 = new ListNode(nextNode1.val, prevNode2);
        return createReverseList(nextNode2, nextNode1.next);
    }
}
