package BinarySearch.Easy;
//Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
//
//You must write an algorithm with O(log n) runtime complexity.
//
//
//
//Example 1:
//
//Input: nums = [-1,0,3,5,9,12], target = 9
//Output: 4
//Explanation: 9 exists in nums and its index is 4
//Example 2:
//
//Input: nums = [-1,0,3,5,9,12], target = 2
//Output: -1
//Explanation: 2 does not exist in nums so return -1
//
//
//Constraints:
//
//        1 <= nums.length <= 104
//        -104 < nums[i], target < 104
//All the integers in nums are unique.
//nums is sorted in ascending order.

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch start = new BinarySearch();
        int[] nums = {
                -1,0,3,5,9,12
        };

        int target = 9;
        System.out.println(
                start.search(nums, target)
        );

    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            if (nums[middle] < target) {
                left = middle + 1;
            }
            else if (nums[middle] > target) {
                right = middle - 1;
            }
            else {
                return middle;
            }
        }

        return -1;
    }
}
