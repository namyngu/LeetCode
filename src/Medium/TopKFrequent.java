package Medium;

//Given an integer array nums and an integer k, return the k most frequent elements.
// You may return the answer in any order.
//Constraints:
//
//    1 <= nums.length <= 105
//    -104 <= nums[i] <= 104
//    k is in the range [1, the number of unique elements in the array].
//    It is guaranteed that the answer is unique.
//
//    Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size

import java.util.HashMap;

public class TopKFrequent {

    //Strat 1 - loop thru array and count integer occurrences - map it to a hashmap.
    // Keep track of the top k (e.g. top 5) occurrences.
    public int[] topkFrequent(int[] nums, int k) {

        // Integer, occurrences
        HashMap<Integer, Integer> map = new HashMap<>();

        // col1  = number
        // col2 = frequency - most to least frequent
        int[][] frequentNums = new int[k][2];


        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);

            }
            else {
                map.put(num, 1);
            }

            // Check if num is frequent
            int frequency = map.get(num);
            if (num)
        }

    }

}
