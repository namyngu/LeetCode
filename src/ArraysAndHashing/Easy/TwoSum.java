//1. Two Sum
//        Easy
//        Topics
//        premium lock icon
//        Companies
//        Hint
//        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//        You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//        You can return the answer in any order.
//
//
//
//        Example 1:
//
//        Input: nums = [2,7,11,15], target = 9
//        Output: [0,1]
//        Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
//        Example 2:
//
//        Input: nums = [3,2,4], target = 6
//        Output: [1,2]
//        Example 3:
//
//        Input: nums = [3,3], target = 6
//        Output: [0,1]
//
//
//        Constraints:
//
//        2 <= nums.length <= 104
//        -109 <= nums[i] <= 109
//        -109 <= target <= 109
//        Only one valid answer exists.
//
//
//        Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

package ArraysAndHashing.Easy;

import java.util.HashMap;

class TwoSum {

    public static void main(String[] args) {
        TwoSum start = new TwoSum();

        int[] param = {-1,-2,-3,-4,-5};

        int[] solution =  start.twoSum2(param, -8);
        System.out.println(solution[0] + "," + solution[1]);
    }

    // Strategy1 Brute force - iterate through the array and add up all the sums - try not to repeat.
    // shit strategy O(n^2)
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {};
    }

    //Strategy 2 - We can use hashmap to check if there exists a number in the nums array that will sum with our
    // current number and add up to the target.
    //Time Complexity O(n), Space complexity O(n)

    public int[] twoSum2(int[] nums, int target) {

        HashMap<Integer, Integer> valueIndex = new HashMap<>(); // num, index
        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if (valueIndex.containsKey(difference)) {
                return new int[] {i, valueIndex.get(difference)};
            }

            valueIndex.put(nums[i], i);
        }

        return new int[] {};
    }

}