// 146. LRU Cache
//Medium
//Topics
//premium lock icon
//Companies
//Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
//Implement the LRUCache class:
//
//LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//int get(int key) Return the value of the key if the key exists, otherwise return -1.
//void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//The functions get and put must each run in O(1) average time complexity.
//
//
//
//Example 1:
//
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2],       [1, 1], [2, 2], [1], [3, 3],  [2],  [4, 4], [1],   [3],   [4]]
//Output
//[null,        null,   null,   1,  null,   -1,    null,  -1,     3,     4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
//
//
//Constraints:
//
//1 <= capacity <= 3000
//0 <= key <= 104
//0 <= value <= 105
//At most 2 * 105 calls will be made to get and put.

package LinkedList.Medium;

import java.util.*;


// Strategy:
// Use a hashmap to store key-value pairs.
// To evict least recently used key when number of keys exceeds the capacity in O(1) time,
// we link the entries of the hashmap with a doubly linked list.
// NOTE: Practically you'd use the built-in LinkedHashMap data structure,
// but for the leetcode example we have to create our own class for a doubly linked list.
// The least recently used (eldest) key is at the head and the youngest key is at the tail of the linked list.
// To make code cleaner and much easier to implement we have an empty node at the head and tail of the doubly
// linked list to point to the LRU and MRU respectively.

// Time Complexity: O(1) for get and put operations
// RESULT: 43ms - beats 93.21%!
// Space Complexity: O(n) where n is the number of unique put operations.
// RESULT: 130.84MB - beats 80.88%
public class LRUCache {
    public class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {}

        public Node(int value) {
            this.value = value;
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int CAPACITY;
    private int size = 0;
    Map<Integer,Node> lruMap;
    Node head = new Node();
    Node tail = new Node();

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        lruMap = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = lruMap.getOrDefault(key, new Node(-1));

        if (node.value != -1) {
            // move node to tail.
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
        }
        return node.value;
    }

    public void put(int key, int value) {
        if (lruMap.containsKey(key)) {
            // update value
            Node node = lruMap.get(key);
            node.value = value;

            // move node to tail.
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
        }
        else if (size < CAPACITY) {
            // key doesn't exist and size < capacity
            Node node = new Node(key, value);
            // insert node at tail
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
            lruMap.put(key, node);

            size++;
        }
        else {
            // key doesn't exist and size exceeds capacity.
            // Evict LRU - remove from hashmap then remove from linked list.
            lruMap.remove(head.next.key);       // this is why we need the node to store the key.
            head.next.next.prev = head;
            head.next = head.next.next;

            // Create new node.
            Node node = new Node(key, value);
            lruMap.put(key, node);

            // insert node at tail
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
            lruMap.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
