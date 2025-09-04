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
        int len = nums.length;

        // Edge cases
        if (len == 0) {
            return -1;
        }
        else if(len == 1) {
            if (nums[0] == target) {
                return 0;
            }
            else {
                return -1;
            }
        }

        int left = 0;
        int right = len - 1;
        int middle = (int) Math.floor((right - left) / 2.0);

        while (right - left > 1) {

            if (nums[middle] == target) {
                return middle;
            }
            else if (nums[middle] < target) {
                left = middle;
                middle = (int) Math.floor((right - left) / 2.0) + left;
            }
            else {
                right = middle;
                middle = (int) Math.floor((right - left) / 2.0);
            }
        }

        if (nums[left] == target) {
            return left;
        }
        else if( nums[right] == target) {
            return right;
        }

        return -1;
    }
}
