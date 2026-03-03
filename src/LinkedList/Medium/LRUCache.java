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
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
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
// To evict least recently used key when number of keys exceeds the capacity, we'll use a doubly linked list.
// In Java, LinkedList<>() implements deque is a built-in deque that's already a doubly linked list.
// LRU key is at the head and MRU (most recently used) key is at the tail of the linked list.
// To remove a key at O(1) time we have a another hashmap that stores the key and index in the linked list.
public class LRUCache {
    private int capacity;
    private int size = 0;
    HashMap<Integer,Integer> keyValue;
    Deque<Integer> lruList;
    HashMap<Integer, Integer> keyIndex;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyValue = new HashMap<>(capacity);
        lruList = new LinkedList<>();       // LinkedList<>() not ArrayDeque<>()
        keyIndex = new HashMap<>();
    }

    public int get(int key) {
        return keyValue.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (keyValue.containsKey(key)) {
            // key already exists, update its value and update its priority.
            keyValue.put(key, value);
            lruList.remove(keyIndex.get(key));
            lruList.addLast(key);
            keyIndex.put(key, lruList.size() - 1);
        }
        else if (size < capacity) {
            // if key doesn't exist and no. of keys doesn't exceed capacity
            lruList.addLast(key);
            keyIndex.put(key, lruList.size() - 1);
            size++;
        }
        else {
            // if key doesn't exist and no. of keys exceed capacity


        }

        // TODO: EEEP can't use index? As you will have to update the index of all keys which takes O(n) time.


    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
