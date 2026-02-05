//141. Linked List Cycle
//        Easy
//Topics
//premium lock icon
//        Companies
//        Given head, the head of a linked list, determine if the linked list has a cycle in it.
//
//There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
//
//Return true if there is a cycle in the linked list. Otherwise, return false.
//
//
//
//Example 1:
//
//
//Input: head = [3,2,0,-4], pos = 1
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
//Example 2:
//
//
//Input: head = [1,2], pos = 0
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
//Example 3:
//
//
//Input: head = [1], pos = -1
//Output: false
//Explanation: There is no cycle in the linked list.
//
//
//Constraints:
//
//The number of the nodes in the list is in the range [0, 104].
//        -105 <= Node.val <= 105
//pos is -1 or a valid index in the linked-list.
//
//
//Follow up: Can you solve it using O(1) (i.e. constant) memory?

package LinkedList.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedListCycle {

    // Strategy 1:
    // As you iterate through each node, check if node already exists in a set, if not add node to set.
    // If node exists in set then there is a cycle otherwise continue until end of linked list.
    // Time Complexity: O(n) - RESULT: 7ms beats 10.26% TODO: optimize further?
    // Space Complexity: O(n) - RESULT: 46.8MB Beats 68.48% TODO: Solve using only O(1) space complexity.
    public boolean hasCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        ListNode currNode = head;

        while (currNode != null) {
            if (set.contains(currNode)) {
                return true;
            }

            set.add(currNode);
            currNode = currNode.next;
        }

        return false;
    }
}
