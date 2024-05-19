package TwoPointers.Medium;

//Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
//        Notice that the solution set must not contain duplicate triplets.
//
//
//
//        Example 1:
//
//        Input: nums = [-1,0,1,2,-1,-4]
//        Output: [[-1,-1,2],[-1,0,1]]
//        Explanation:
//        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//        The distinct triplets are [-1,0,1] and [-1,-1,2].
//        Notice that the order of the output and the order of the triplets does not matter.
//
//        Example 2:
//
//        Input: nums = [0,1,1]
//        Output: []
//        Explanation: The only possible triplet does not sum up to 0.
//
//        Example 3:
//
//        Input: nums = [0,0,0]
//        Output: [[0,0,0]]
//        Explanation: The only possible triplet sums up to 0.
//
//
//
//        Constraints:
//
//        3 <= nums.length <= 3000
//        -105 <= nums[i] <= 105

import java.util.*;

// Strat 1 - combines your knowledge of TwoSum2 and TwoSum
// Doesn't work with duplicates
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum start = new ThreeSum();
        int[] input = new int[] {-1,0,1,2,-1,-4,-2,-3,3,0,4};

        start.threeSum2(input);
    }

    public List<List<Integer>> threeSum(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> solution = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int leftPointer = 0;
        int rightPointer = nums.length - 1;

        while (leftPointer < rightPointer) {

            int num3 = -nums[leftPointer] - nums[rightPointer];


            if (map.containsKey(num3) && map.get(num3) > leftPointer && map.get(num3) < rightPointer) {

                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[leftPointer]);
                triplet.add(nums[rightPointer]);
                triplet.add(num3);

                solution.add(triplet);
            }

            leftPointer++;
            rightPointer--;
        }

        return solution;
    }

    // Strat 2
    // Fix one of the numbers then it becomes a two sum problem
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    // RESULT: SLOW 509ms
    public List<List<Integer>> threeSum2(int[] nums) {
        int target = 0;
        //Used hashset here to avoid duplicates
        HashSet<List<Integer>> solution = new HashSet<>();

        Arrays.sort(nums);

        // i is your third pointer
        for (int i = 0; i < nums.length - 2; i++) {


            int leftPointer = i + 1;
            int rightPointer = nums.length - 1;

            while (leftPointer < rightPointer) {

                int sum = nums[i] + nums[leftPointer] + nums[rightPointer];

                if (sum == target) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[leftPointer]);
                    triplet.add(nums[rightPointer]);
                    solution.add(triplet);

                    leftPointer++;
                    rightPointer--;
                }
                else if (sum < target) {
                    leftPointer++;
                }
                else {
                    rightPointer--;
                }
            }
        }

        return new ArrayList<>(solution);
    }

    // Same as Strat 2 - more optimised code
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    // RESULT: 301ms
    public List<List<Integer>> threeSum3(int[] nums) {
        int target = 0;
        //Used hashset here to avoid duplicates
        HashSet<List<Integer>> solution = new HashSet<>();

        Arrays.sort(nums);

        // i is your third pointer
        for (int i = 0; i < nums.length - 2; i++) {


            int leftPointer = i + 1;
            int rightPointer = nums.length - 1;

            while (leftPointer < rightPointer) {

                int sum = nums[i] + nums[leftPointer] + nums[rightPointer];

                if (sum == target) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[leftPointer]);
                    triplet.add(nums[rightPointer]);
                    solution.add(triplet);

                    leftPointer++;
                    rightPointer--;
                }
                else if (sum < target) {
                    leftPointer++;
                }
                else {
                    rightPointer--;
                }
            }
        }
        return new ArrayList<>(solution);
    }
}

    // Strat 3
    // Fix one of the numbers then it becomes a two sum problem
    // Use hashmap to make search faster
