package BinarySearch.Medium;

//33. Search in Rotated Sorted Array
//        Solved
//Medium
//        Topics
//premium lock icon
//        Companies
//There is an integer array nums sorted in ascending order (with distinct values).
//
//Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
//
//Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
//
//You must write an algorithm with O(log n) runtime complexity.
//
//
//
//Example 1:
//
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
//Example 2:
//
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1
//Example 3:
//
//Input: nums = [1], target = 0
//Output: -1
//
//
//Constraints:
//
//        1 <= nums.length <= 5000
//        -104 <= nums[i] <= 104
//All values of nums are unique.
//nums is an ascending array that is possibly rotated.
//-104 <= target <= 104

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        SearchInRotatedSortedArray start = new SearchInRotatedSortedArray();
        int[] input = {1,3};
        start.search(input, 0);
    }


    // Time Complexity: O(logn) + O(logn) + O(logn) = O(logn)
    // Space Complexity: O(1)
    // Result: 0ms beats 100%! (although everyone got same score).
    public int search(int[] nums, int target) {

        // 1. find the minimum value or the "cut" similar to the find minimum in rotated sorted array.
        int minIndex = findMin(nums);

        // 2. use two binary searches, one on the left side of the cut, the other on the right side of the cut to find  target.
        int rightAns = binarySearch(minIndex, nums.length - 1, target, nums);
        if (rightAns != -1) {
            return rightAns;
        }

        // Edge case, no rotations.
        if (minIndex == 0) {
            return-1;
        }

        int leftAns = binarySearch(0, minIndex - 1, target, nums);
        return leftAns;
    }

    // Returns the index of the minimum value.
    int findMin(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            if (arr[middle] <= arr[right]) {
                // the cut must be on the left side or on the middle
                right = middle;
            }
            else {
                // cut is on the right and NOT in the middle
                left = middle + 1;
            }
        }

        return left;
    }

    int binarySearch(int left, int right, int target, int[] arr) {

        while (left <= right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            if (arr[middle] > target) {
                right = middle - 1;
            }
            else if (arr[middle] < target) {
                left = middle + 1;
            }
            else {
                return middle;
            }

        }

        // target not found
        return -1;
    }
}
