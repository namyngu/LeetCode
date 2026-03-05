package ArraysAndHashing.Easy;

import java.util.HashSet;

class ContainsDuplicate {

    public static void main(String[] args) {
        ContainsDuplicate start = new ContainsDuplicate();
        boolean isDuplicate = start.containsDuplicate(new int[] {1,1,1,3,3,4,3,2,4,2});
        System.out.println(isDuplicate);
    }

    // Strategy 1:
    // Time Complexity: O(n) - RESULT: 10ms Beats 99.19%
    // Space Complexity: O(n) - RESULT: 62.08MB Beats 99.94%
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> isDuplicate = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            if (isDuplicate.contains(nums[i])) {
                return true;
            }

            isDuplicate.add(nums[i]);
        }
        return false;
    }
}
