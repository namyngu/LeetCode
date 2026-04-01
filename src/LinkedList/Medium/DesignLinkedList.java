// 707. Design Linked List
// Solved
// Medium
// Topics
// premium lock icon
// Companies
// Design your implementation of the linked list. You can choose to use a singly or doubly linked
// list.
// A node in a singly linked list should have two attributes: val and next. val is the value of the
// current node, and next is a pointer/reference to the next node.
// If you want to use the doubly linked list, you will need one more attribute prev to indicate the
// previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
//
// Implement the MyLinkedList class:
//
// MyLinkedList() Initializes the MyLinkedList object.
// int get(int index) Get the value of the indexth node in the linked list. If the index is invalid,
// return -1.
// void addAtHead(int val) Add a node of value val before the first element of the linked list.
// After the insertion, the new node will be the first node of the linked list.
// void addAtTail(int val) Append a node of value val as the last element of the linked list.
// void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked
// list. If index equals the length of the linked list, the node will be appended to the end of the
// linked list. If index is greater than the length, the node will not be inserted.
// void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
//
//
// Example 1:
//
// Input
// ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
// [[], [1], [3], [1, 2], [1], [1], [1]]
// Output
// [null, null, null, null, 2, null, 3]
//
// Explanation
// MyLinkedList myLinkedList = new MyLinkedList();
// myLinkedList.addAtHead(1);
// myLinkedList.addAtTail(3);
// myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
// myLinkedList.get(1);              // return 2
// myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
// myLinkedList.get(1);              // return 3
//
//
// Constraints:
//
// 0 <= index, val <= 1000
// Please do not use the built-in LinkedList library.
// At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.

package LinkedList.Medium;

// Time Complexity:
// get() is O(n)
// addAtHead is O(1)
// addAtTail is O(1)
// addAtIndex is O(n)
// deleteAtIndex is O(n)
// RESULT: 7ms - beats 95.51%
// Space Complexity: All methods are O(n) at most.
// RESULT: 46.8MB beats 82.23%
public class DesignLinkedList {
	int length;
	ListNode head;      // dummy node that points to the head.
	ListNode tail;      // dummy node that points to the tail.

	public DesignLinkedList() {
		this.length = 0;
		head = new ListNode(0, tail);
		tail = new ListNode(0);
		tail.prev = head;
		head.next = tail;
	}

	public int get(int index) {
		if (index > length - 1 || index < 0) {
			return -1;
		}

		int n = 0;
		ListNode cur = head.next;
		while (cur != null) {
			if (n == index) {
				return cur.val;
			}

			cur = cur.next;
			n++;
		}

		return -1;
	}

	public void addAtHead(int val) {
		ListNode tmp = head.next;
		head.next = new ListNode(val);
		head.next.next = tmp;
		head.next.prev = head;
		tmp.prev = head.next;
		length++;
	}

	public void addAtTail(int val) {
		ListNode tmp = tail.prev;
		tail.prev = new ListNode(val);
		tail.prev.prev = tmp;
		tail.prev.next = tail;
		tmp.next = tail.prev;
		length++;
	}

	public void addAtIndex(int index, int val) {
		if (index > length || index < 0) {
			return;
		}

		int n = 0;
		ListNode cur = head.next;
		while (cur != null) {
			if (n == index) {
				ListNode prev = cur.prev;       // this works because the head and tail node are linked to the dummy node.
				cur.prev = new ListNode(val);
				prev.next = cur.prev;
				cur.prev.prev = prev;
				cur.prev.next = cur;
				length++;

				return;
			}

			cur = cur.next;
			n++;
		}
	}

	public void deleteAtIndex(int index) {
		if (index > length - 1 || index < 0) {
			return;
		}

		int n = 0;
		ListNode cur = head.next;
		while (cur != null) {
			if (n == index) {
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				length--;

				return;
			}

			cur = cur.next;
			n++;
		}
	}

	private class ListNode {
		public int val;
		public ListNode next;
		public ListNode prev;

		public ListNode() {}

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}

/**
 * Your DesignLinkedList object will be instantiated and called as such:
 * DesignLinkedList obj = new DesignLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
