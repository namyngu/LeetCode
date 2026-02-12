//138. Copy List with Random Pointer
//Medium
//Topics
//premium lock icon
//Companies
//Hint
//A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
//
//Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
//
//For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
//
//Return the head of the copied linked list.
//
//The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
//
//val: an integer representing Node.val
//random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
//Your code will only be given the head of the original linked list.
//
//
//
//Example 1:
//
//
//Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
//Example 2:
//
//
//Input: head = [[1,1],[2,1]]
//Output: [[1,1],[2,1]]
//Example 3:
//
//
//
//Input: head = [[3,null],[3,0],[3,null]]
//Output: [[3,null],[3,0],[3,null]]
//
//
//Constraints:
//
//0 <= n <= 1000
//-104 <= Node.val <= 104
//Node.random is null or is pointing to some node in the linked list.

package LinkedList.Medium;

import LinkedList.Node;

import java.util.HashMap;
import java.util.Map;

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
public class CopyListwithRandomPointer {

    // Strategy 0:
    // Use a two past approach. First past we create a new list keeping all random ptr = null.
    // Second past, we assign random ptr to the copied list
    // This will NOT WORK, why? Since we cannot use any of the old nodes, we cannot copy the random ptr from the first list and directly paste it into the copied list.
    // If we do create a new node with the same values for our random ptr then we run into the problem of not knowing where it is in the list (index).

    // Strategy 2:
    // Let's call the ptr to the original list listPtr and the ptr to the copy list, copyPtr.
    // Create a hashmap<Node, List<Node>>, with the key be nodes from the original list and value be a list of nodes from the copied list.
    // The hashmap contains entries of listPtr we have passed through and all the nodes from the cop
    // Traverse the listPtr and copyPtr simultaneously.
    // At every increment:
    // 2. Check if hashmap.contains(listPtr)
    //      if true, copiedNodes = hashmap.get(listPtr)
    //      create a new node with same values as listPtr and make every node.random in the copiedNodes point to it.
    //      empty copiedNodes.

    //      else add listPtr to hashmap with an empty array as value.
    // 3. check if listPtr.random exists in the set (if we have seen this random node before),
    //      if not, add <listPtr.random, copyPtr> to hashmap.
    public Node copyRandomList(Node head) {

        Node headCopy = new Node(head.val);
        Map<Node,Node> map = new HashMap<>();

        Node listPtr = head;
        Node copyListPtr = headCopy;

        while (listPtr.next != null) {
            if (map.containsKey(listPtr)) {
                // assign random ptr from list of copiedNodes

            }
        }




        return headCopy;
    }

    // Strategy 1:
    // Using two past approach.
    // First past, store every node and its index in a hashmap<Node, Index> from original list and do the same in another hashmap for the copyList.
    // The hashmap pairings are: hashmap1 - Node : Index and hashmap2 - Index : Node.
    // Second past, at each incrememt:
    // Get index of random node by: hashmap1.get(listPtr.random)
    // Find corresponding node in the copied list by using hashmap2.
    // assign node.random for copied list.

    public Node copyRandomList2(Node head) {

        Map<Node, Integer> map1 = new HashMap<>();
        Map<Integer, Node> map2 = new HashMap<>();
        Node headCopy = new Node(head.val);
        Node list1 = head;
        Node list2 = headCopy;

        // First node
        // Edge case (size <= 1)
        if (list1 == null) return list1;
        if (list1.next == null) return list1;

        int index = 0;
        map1.put(list1, index);
        map2.put(index, list2);

        list2.next = new Node(list1.next.val);

        // increment
        list1 = list1.next;
        list2 = list2.next;
        index++;

        // First past
        while (list1.next != null) {
            map1.put(list1, index);
            map2.put(index, list2);
        }

        // Second past



        return headCopy;
    }
}
