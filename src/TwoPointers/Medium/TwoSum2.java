package TwoPointers.Medium;

//Given a 1-indexed array of integers numbers that is already sorted in increasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
//
//        Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
//
//        The tests are generated such that there is exactly one solution. You may not use the same element twice.
//
//        Your solution must use only constant extra space.
//
//
//
//        Example 1:
//
//        Input: numbers = [2,7,11,15], target = 9
//        Output: [1,2]
//        Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
//
//        Example 2:
//
//        Input: numbers = [2,3,4], target = 6
//        Output: [1,3]
//        Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
//
//        Example 3:
//
//        Input: numbers = [-1,0], target = -1
//        Output: [1,2]
//        Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].



public class TwoSum2 {

    public static void main(String[] args) {
        TwoSum2 start = new TwoSum2();
    }

    // Strat 1
    // From Solutions:
    //    Logic:
    //    The approach to this question differs to that of the classic Two Sum problem in that we have some direction with how we want to search for our target.
    //    Since the array is sorted, we can make some general observations:
    //
    //    Smaller sums would come from the left half of the array
    //    Larger sums would come from the right half of the array
    //
    //    Therefore, using two pointers starting at the end points of the array, we can choose to increase or decrease our current sum however we like. Pay attention to the example below:
    //    The basic idea is that:
    //
    //    If our current sum is too small, move closer to the right.
    //    If our current sum is too large, move closer to the left.
    //
    //    That's really all there is to it! Since the array is sorted and we're guarranteed that there exists an answer, we have everything we need to start coding :)

    public int[] twoSum(int[] numbers, int target) {

        int leftPointer = 0;
        int rightPointer = numbers.length - 1;

        while (leftPointer < rightPointer) {

            if (numbers[leftPointer] + numbers[rightPointer] == target) {
                break;
            }

            // move left pointer to right if sum < target
            else if (numbers[leftPointer] + numbers[rightPointer] < target) {
                leftPointer++;
            }

            // move right pointer to left if sum > target
            else {
                rightPointer--;
            }
        }

        return new int[] {leftPointer + 1, rightPointer + 1};
    }


}
