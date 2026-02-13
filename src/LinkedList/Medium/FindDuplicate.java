//287. Find the Duplicate Number
//Medium
//Topics
//premium lock icon
//Companies
//Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//
//There is only one repeated number in nums, return this repeated number.
//
//You must solve the problem without modifying the array nums and using only constant extra space.
//
//
//
//Example 1:
//
//Input: nums = [1,3,4,2,2]
//Output: 2
//Example 2:
//
//Input: nums = [3,1,3,4,2]
//Output: 3
//Example 3:
//
//Input: nums = [3,3,3,3,3]
//Output: 3
//
//
//Constraints:
//
//1 <= n <= 105
//nums.length == n + 1
//1 <= nums[i] <= n
//All the integers in nums appear only once except for precisely one integer which appears two or more times.
//
//
//Follow up:
//
//How can we prove that at least one duplicate number must exist in nums?
//Can you solve the problem in linear runtime complexity?

package LinkedList.Medium;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicate {

    // Strategy 2:
    // Using fast and slow pointers
    // Treat the array like a linked list, where each index points to the next index given by its value.
    // Because one number is duplicated, two indices will point into the same chain, creating a cycle — exactly like a linked list with a loop.
    // Using Floyd’s Fast & Slow Pointer technique:
    //
    // The slow pointer moves one step at a time.
    // The fast pointer moves two steps at a time.
    // If there’s a cycle, they will eventually meet.
    // Once they meet, we start a new pointer from the beginning:
    //
    // Move both pointers one step at a time.
    // The point where they meet again is the duplicate number (the entry point of the cycle)
    public int findDuplicate2(int[] nums) {

    }

    // Strategy 1:
    // Simply use a hashset to check for duplicates.
    // Time Complexity: O(n)
    // RESULT: 23ms beats 22.95%
    // Space Complexity: O(n)
    // RESULT: 92.08MB Beats 11.03%
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }

            set.add(nums[i]);
        }

        return - 1;     // should not be possible.
    }
}
