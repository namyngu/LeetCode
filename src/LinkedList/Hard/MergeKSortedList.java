//23. Merge k Sorted Lists
//Hard
//Topics
//premium lock icon
//Companies
//You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//Merge all the linked-lists into one sorted linked-list and return it.
//
//
//
//Example 1:
//
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted linked list:
//1->1->2->3->4->4->5->6
//Example 2:
//
//Input: lists = []
//Output: []
//Example 3:
//
//Input: lists = [[]]
//Output: []
//
//
//Constraints:
//
//k == lists.length
//0 <= k <= 104
//0 <= lists[i].length <= 500
//-104 <= lists[i][j] <= 104
//lists[i] is sorted in ascending order.
//The sum of lists[i].length will not exceed 104.

package LinkedList.Hard;

import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Not clear if we can create a new merged linked list and return that or merge existing list into one list, then return that.
public class MergeKSortedList {

    // Strategy 2:
    // As we iterate through the lists we merge the current linked list with the previous one and sort it.
    // Then on the next iteration, we merge the new linked list with the previous merged one!.
    // Repeat this until list is complete.
    public ListNode mergeKLists2(ListNode[] lists) {
        // Edge case
        if (lists.length == 0) {
            return null;
        }


    }

    // Strategy 1:
    // Brute force, store all nodes in an array, sort the array then convert it back to a linked list.
    // Time Complexity: O(nlogn)
    // RESULT: 9ms beats 21.5%
    // Space Complexity: O(n)
    // RESULT: 46.56MB Beats 91.03%
    public ListNode mergeKLists(ListNode[] lists) {
        // Edge case
        if (lists.length == 0) {
            return null;
        }

        List<Integer> sortedArray = new ArrayList<>();

        // add all nodes in an array
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            while (node != null) {
                sortedArray.add(node.val);
                node = node.next;
            }
        }

        // Edge case - no nodes
        if (sortedArray.size() == 0) {
            return null;
        }

        // sort the array
        Collections.sort(sortedArray, (a,b) -> a - b);

        // convert back to linked list and return it.
        ListNode head = new ListNode(sortedArray.get(0));
        ListNode node = head;
        for (int i = 1; i < sortedArray.size(); i++) {
            node.next = new ListNode(sortedArray.get(i));
            node = node.next;
        }

        return head;
    }
}
