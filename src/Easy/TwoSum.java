package Easy;

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

        HashMap<Integer, Integer> valueIndex = new HashMap<>();
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