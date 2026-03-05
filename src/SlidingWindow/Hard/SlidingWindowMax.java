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

import java.util.*;

public class SlidingWindowMax {

    public static void main(String[] args) {
        SlidingWindowMax start = new SlidingWindowMax();

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] ans = start.maxSlidingWindow3(nums, k);
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            print.append(ans[i]).append(", ");
        }

        System.out.println(print.toString());
    }

    // Strategy 4:
    //

    // Strategy 3:
    // Similar to strat 2, but instead of storing integer in the PQ, store an array of size 2 [value, index] in the PQ.
    // This allows you to keep track of the highest int in the window without having to remove all elements at every left increment.
    // Time Complexity: O(nlogn) - RESULT: 75ms beats 14.94% much faster!
    // Space Complexity: O(n)
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];           // [value, index] sort by highest value
            }
        });

        int left = 0, right = 0;

        // setup initial window
        while (right < k) {
            pq.add(new int[]{nums[right], right});
            right++;
        }
        ans[0] = pq.peek()[0];
        left++;

        while (right < nums.length) {
            pq.add(new int[]{nums[right], right});

            while (pq.peek()[1] < left) {
                // remove old max values that are not in current window.
                pq.poll();
            }

            ans[right - k + 1] = pq.peek()[0];

            right++;
            left++;
        }
        return ans;
    }

    // Strategy 2:
    // Similar to strat 1 but optimize finding new max integer in window by using max heap (priority queue)
    // The window is the priority queue, so whenever we increment left and right ptr we insert and remove elements in the PQ accordingly.
    // RESULT: Time out - even slower than strat 1?
    // Most likely bottlenecked at pq.remove
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int left = 0, right = 0;

        // setting up initial window
        while (right < k) {
            pq.add(nums[right]);
            right++;
        }
        if (pq.peek() != null) {
            ans[0] = pq.peek();
        }

        pq.remove(nums[left]);
        left++;

        // increment windows across
        while (right < nums.length) {
            pq.add(nums[right]);
            ans[right - k + 1] = pq.peek();     // pq.peek() cannot be null

            pq.remove(nums[left]);

            left++;
            right++;
        }

        return ans;
    }


    // Strategy 1:
    // Setup a sliding window with left and right ptr.
    // Keep track of the largest number in that window by using an int[] array (value, frequency)
    // Everytime the left & right ptr increments update the largest number
    // Time Complexity: O(n*k) - RESULT: 1255ms Beats 5.03% (sloww) - where k is the size of the window
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
                    // find new windowMax - this needs to be optimized
                    windowMax[0] = Integer.MIN_VALUE;
                    windowMax[1] = 0;
                    for (int i = left; i <= right; i++) {
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
