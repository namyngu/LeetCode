package ArraysAndHashing.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

//Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//        You must write an algorithm that runs in O(n) time.
public class LongestConsecutive {

    public static void main(String[] args) {
        LongestConsecutive start = new LongestConsecutive();
        System.out.println(start.longestConsecutive3(new int[] {100,4,200,1,3,2}));

    }


    // Strat 1
    // 1. Sort Array, 2. count consecutive numbers - doesn't work coz duplicates.
    // Time Complexity: O(nlogn + n)
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);

        if (nums.length == 0) {
            return 0;
        }

        int longest = 1;
        int current = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1] + 1) {
                current++;
                if (current > longest) {
                    longest = current;
                }
            }
            else {
                current = 1;
            }
        }

        return longest;
    }

    // Strat 2
    // 1. Loop through array then map all elements to priorityqueue
    // 2. take each element out of PQ and check if it's still consecutive to previous.
    // Time limit exceeded! :(
    public int longestConsecutive2(int[] nums) {

        // Corner case - empty array
        if (nums.length == 0) {
            return 0;
        }

        int longest = 1;
        int current = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // map all elements in array to a PQ (no duplicates
        for (int i = 0; i < nums.length; i++) {
            if (!pq.contains(nums[i])) {
                pq.add(nums[i]);
            }
        }

        int i = 0;
        int prevNum = 0;
        // count consecutive numbers
        while (pq.peek() != null) {
            int num = pq.poll();

            if (i == 0) {
                prevNum = num;
                i++;
                continue;
            }

            if (num == prevNum + 1) {
                current++;
                prevNum = num;

                if (current > longest) {
                    longest = current;
                }
            }
            else {
                current = 1;
                prevNum = num;
            }

            i++;
        }
        return longest;
    }

    // Strat 3
    // 1. Add elements of array to hashset
    // 2. iterate through hashset and count consecutive
    // Time Complexity: O(n + n)
    // RESULT: 26ms very good!!
    public int longestConsecutive3(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        // step 2 - iterate thru set and count consecutive nums
        int longest = 1;
        int current = 1;

        for (int num : set) {
            // if num is not start of sequence - skip
            if (set.contains(num - 1)) {
                continue;
            }

            // count consecutive if it exists
            while (set.contains(num + 1)) {
                current++;
                num++;
            }

            longest = Math.max(current, longest);
            current = 1;

        }

        return longest;
    }
}
