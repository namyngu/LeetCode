//239. Sliding Window Maximum
//        Hard
//Topics
//premium lock icon
//        Companies
//Hint
//You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
//Return the max sliding window.
//
//
//
//        Example 1:
//
//Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//Output: [3,3,5,5,6,7]
//Explanation:
//Window position                Max
//---------------               -----
//        [1  3  -1] -3  5  3  6  7       3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7
//Example 2:
//
//Input: nums = [1], k = 1
//Output: [1]
//
//
//Constraints:
//
//        1 <= nums.length <= 105
//        -104 <= nums[i] <= 104
//        1 <= k <= nums.length

package SlidingWindow.Hard;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMax {

    public static void main(String[] args) {
        SlidingWindowMax start = new SlidingWindowMax();

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] ans = start.maxSlidingWindow(nums, k);
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            print.append(ans[i]).append(", ");
        }

        System.out.println(print.toString());
    }


    // Strategy 1:
    // Time Complexity: O(n*k) - RESULT: 1255ms Beats 5.03% (sloww)
    // Space Complexity: O(n) - RESULT: 157.34MB Beats 11.93%
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int[] windowMax = {Integer.MIN_VALUE, 1};       // value, frequency
        int left = 0, right = 0;

        // setting up initial window
        while (right < k) {

            if (nums[right] > windowMax[0]) {
                windowMax[0] = nums[right];
                windowMax[1] = 1;
            }
            else if (nums[right] == windowMax[0]) {
                windowMax[1] += 1;
            }
            right++;
        }
        ans[0] = windowMax[0];

        left++;

        // slide window across
        while (right < nums.length) {
            if (nums[left - 1] == windowMax[0]) {
                if (windowMax[1] > 1) {
                    windowMax[1]--;
                }
                else {
                    // find new windowMax
                    windowMax[0] = Integer.MIN_VALUE;
                    windowMax[1] = 0;
                    for (int i = left; i <= right; i++) {       // needs to be optimized
                        if (nums[i] > windowMax[0]) {
                            windowMax[0] = nums[i];
                            windowMax[1] = 1;
                        }
                        else if (nums[i] == windowMax[0]) {
                            windowMax[1] += 1;
                        }
                    }

                    ans[right - k + 1] = windowMax[0];
                    left++;
                    right++;
                    continue;
                }

            }

            if (nums[right] > windowMax[0]) {
                windowMax[0] = nums[right];
                windowMax[1] = 1;
            }
            else if (nums[right] == windowMax[0]) {
                windowMax[1] += 1;
            }

            ans[right - k + 1] = windowMax[0];
            left++;
            right++;
        }

        return ans;
    }
}
