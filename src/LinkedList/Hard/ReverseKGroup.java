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

	public static void main(String[] args){
		ReverseKGroup start = new ReverseKGroup();
		ListNode input = start.generateInputs(new int[]{1,2,3,4,5,6});

		ListNode res = start.reverseKGroup2(input, 3);
		while (res != null) {
			System.out.print(res.val + " -> ");
			res = res.next;
		}
	}

	// Generates inputs for debugging
	// Returns the head of the linked list.
	public ListNode generateInputs(int[] arr) {
		if (arr.length == 0) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		ListNode prev = dummy;
		for (int i = 0; i < arr.length; i++) {
			ListNode curr = new ListNode(arr[i]);
			prev.next = curr;
			prev = curr;
		}

		return dummy.next;
	}



	// Strategy 2: Reverse nodes in place
	// TODO: Finish strat 2
	// Dummy node points to the head of the linked list.
	// Need 2 more dummy nodes:
	// One points to the head of the next k group and one points to the tail of the current group.
	public ListNode reverseKGroup2(ListNode head, int k) {
		// Edge case
		if (k == 1) {
			return head;
		}

		ListNode dummy = new ListNode();
		dummy.next = head;

		ListNode curr = head;			// use this ptr to traverse the linked list.
		ListNode nxtGroup;				// points to the head of the next group.
		ListNode prevGroup = dummy;		// points to the tail of the previous group.


		while (true) {
			// Get the kth node
			ListNode kth = getKth(curr, k);
			if (kth == null) {
				// Don't reverse, end of list
				prevGroup.next = curr;

				return dummy.next;
			}

			nxtGroup = kth.next;

			// Reverse k group
			int counter = 1;
			ListNode prev = dummy;

			while (counter <= k) {
				if (counter == k) {
					prevGroup.next = curr;
				}
				else if (counter == 1) {
					prevGroup = curr;
					ListNode nxt = curr.next;
					curr.next = nxtGroup;
					prev = curr;
					curr = nxt;
					counter++;

					continue;
				}

				ListNode nxt = curr.next;
				curr.next = prev;
				prev = curr;
				curr = nxt;
				counter++;
			}
		}
	}

	// Helper function - gets the kth node
	ListNode getKth(ListNode curr, int k) {
		while (curr != null && k > 0) {
			curr = curr.next;
			k--;
		}
		return curr;
	}

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
}
