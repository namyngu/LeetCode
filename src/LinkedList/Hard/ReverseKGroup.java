// Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
//
//k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
//
//You may not alter the values in the list's nodes, only nodes themselves may be changed.
//
//
//
//Example 1:
//
//
//Input: head = [1,2,3,4,5], k = 2
//Output: [2,1,4,3,5]
//Example 2:
//
//
//Input: head = [1,2,3,4,5], k = 3
//Output: [3,2,1,4,5]
//
//
//Constraints:
//
//The number of nodes in the list is n.
//1 <= k <= n <= 5000
//0 <= Node.val <= 1000
//
//
//Follow-up: Can you solve the problem in O(1) extra memory space?

package LinkedList.Hard;

import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ReverseKGroup {

    // Strategy 1: Brute Force
    // Traverse and store k nodes in a list
    // Create another list and store the k nodes in reverse
    // Convert list into a linked list
    // Time Complexity: O(n)
    // RESULT: 4ms - beats 2.18%
    // Space Complexity: O(n)
    // RESULT: 46.20MB - beats 93.19%
    public ListNode reverseKGroup(ListNode head, int k) {
        // Edge case
        if (k == 1) {
            return head;
        }

        ListNode currNode = head;

        // store all nodes in arraylist
        List<ListNode> linkedList = new ArrayList<>();
        while (currNode != null) {
            linkedList.add(currNode);
            currNode = currNode.next;
        }

        // Reverse k nodes in another list
        List<ListNode> reverseKGroup = new ArrayList<>();

        for (int i = 0; i < linkedList.size() - (linkedList.size() % k); i += k) {
            for (int j = k - 1; j >= 0; j--) {
                reverseKGroup.add(linkedList.get(i + j));
            }
        }

        for (int i = linkedList.size() - (linkedList.size() % k); i < linkedList.size(); i++) {
            reverseKGroup.add(linkedList.get(i));
        }

        // Convert arraylist back to linked list.
        int index = 0;
        for (ListNode node : reverseKGroup) {
            if (index + 1 < reverseKGroup.size()) {
                node.next = reverseKGroup.get(index + 1);
            }
            else if (index + 1 == reverseKGroup.size()) {
                node.next = null;
            }

            index++;
        }

        return reverseKGroup.get(0);
    }

    // Helper function - reverses the linked list
    // Returns an array of size 2 (head and tail of new linked list)
//    ListNode[] reverseKNodes(ListNode head, int k) {
//        List<ListNode> subList = new ArrayList<>();
//
//         ListNode currNode = head;
//
//        int counter = 1;
//        while (counter <= k && currNode != null) {
//            subList.add(currNode);
//
//        }
//    }
}
