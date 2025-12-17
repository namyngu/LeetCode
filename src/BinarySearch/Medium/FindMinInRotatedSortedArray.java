package BinarySearch.Medium;

public class FindMinInRotatedSortedArray {


	// Time Complexity: O(logn)
	// RESULT: 0ms beats 100% - everybody got this result
	public int findMin(int[] nums) {
		// find the index in the array where the "cut" from the minimum to max value.
		// write all possible rotations for a given array to get a clear picture.

		int len = nums.length;
		// Edge case
		if (nums[0] < nums[len - 1]) {
			return nums[0];
		}

		int left = 0;
		int right = len - 1;
		int min = nums[0];      // pick an initial value

		while (left <= right) {
			int middle = (int) Math.floor((right - left) / 2.0) + left;

			if (nums[middle] > nums[left]) {
				if (nums[middle] > nums[right]) {
					//the cut is in the right side.
					left = middle + 1;
					min = Math.min(min, nums[middle]);
				}
				else {
					// the cut is in the left side.
					right = middle - 1;
					min = Math.min(min, nums[middle]);
				}

			}
			else if (nums[middle] < nums[left]) {
				// the cut is in the left side.
				right = middle - 1;
				min = Math.min(min, nums[middle]);
			}
			else {
				left = middle + 1;
				min = Math.min(min, nums[middle]);
			}
		}
		return min;
	}


}
