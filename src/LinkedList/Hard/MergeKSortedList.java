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
import java.util.PriorityQueue;

// Not clear if we can create a new merged linked list and return that or merge existing list into one list, then return that.
public class MergeKSortedList {

    // Strategy 3:
    // 1. Add all the head nodes of the linked lists into a priority queue that is ordered from smallest to largest value.
    // 2. Retrieve the smallest node (and remove it) from the priority queue and add it to the new merged list.
    // 3. If that smallest node has a next node, add that to the priority queue.
    // Repeat steps 2-3.
    // Analogy: Imagine lines of people, each sorted by height.
    // To form one single line sorted by height, you look at the person at the front of every line.
    // You pick the shortest one and put them in the new line.
    // Then, the person who was standing behind them moves to the front of their respective line, and you repeat the process.

    // Time Complexity: O(k*n) where k is the number of lists and n is the average linked list length
    // RESULT: 4ms - beats 80.63%
    // Space Complexity: O(k*n)
    // RESULT: 46.97MB - beats 43.11%
    public ListNode mergeKLists3(ListNode[] lists) {

        PriorityQueue<ListNode> que = new PriorityQueue<>((a,b) -> a.val - b.val);

        for (ListNode node: lists) {
            if (node != null) {
                que.add(node);
            }
        }

        // new merged list
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while(!que.isEmpty()) {
            ListNode smallest = que.poll();
            tail.next = smallest;
            tail = tail.next;

            // add next node in line to the que
            if (smallest.next != null) {
                que.add(smallest.next);
            }
        }

        return dummy.next;
    }

    // Strategy 2:
    // As we iterate through the lists we merge the current linked list with the previous one and sort it.
    // Then on the next iteration, we merge the new linked list with the previous merged one!.
    // Repeat this until list is complete.

    // Time Complexity: O(n*k)
    // RESULT: 120ms beat 5.07% slower than strat1 somehow :(
    // Space Complexity: O(n)
    // RESULT: 46.86MB beats 62.69% still worse than strat1
    public ListNode mergeKLists2(ListNode[] lists) {
        // Edge case
        if (lists.length == 0) {
            return null;
        }
        else if (lists.length == 1) {
            return lists[0];
        }

        ListNode mergeList = null;
        for (int i = 0; i < lists.length; i++) {
            // merge current list with previous list
            mergeList = mergeLists(mergeList, lists[i]);
        }

        return mergeList;
    }

    // merge two sorted lists and return a merge sorted list.
    public ListNode mergeLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        else if (list2 == null) {
            return list1;
        }


        ListNode node;
        ListNode dummyHead = new ListNode();
        if (list1.val <= list2.val) {
            node = list1;
            list1 = list1.next;
        } else {
            node = list2;
            list2 = list2.next;
        }

        dummyHead.next = node;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                node = node.next;
                list1 = list1.next;
            }
            else {
                node.next = new ListNode(list2.val);
                node = node.next;
                list2 = list2.next;
            }
        }

        if (list1 == null) {
            node.next = list2;
        }
        else {
            node.next = list1;
        }


        return dummyHead.next;
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
        if (sortedArray.isEmpty()) {
            return null;
        }

        // sort the array
        sortedArray.sort((a, b) -> a - b);

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
