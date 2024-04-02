package TwoPointers.Medium;

//You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//
//        Find two lines that together with the x-axis form a container, such that the container contains the most water.
//
//        Return the maximum amount of water a container can store.
//
//        Notice that you may not slant the container.
//
//        Example 1:
//        Input: height = [1,8,6,2,5,4,8,3,7]
//        Output: 49
//        Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//
//        Example 2:
//        Input: height = [1,1]
//        Output: 1
//
//        Constraints:
//        n == height.length
//        2 <= n <= 105
//        0 <= height[i] <= 104
//

public class WaterContainer {

    // Strat 1 - brute force
    // Time Complexity = O(n^2)
    // RESULT: Time limit exceeded
//    public int maxArea(int[] height) {
//
//        int areaMax = 0;
//
//        for (int leftPointer = 0; leftPointer < height.length - 1; leftPointer++) {
//
//            int rightPointer = height.length - 1;
//
//            while (rightPointer > leftPointer) {
//
//                int area = Math.min(height[leftPointer], height[rightPointer]) * (rightPointer - leftPointer);
//                areaMax = Math.max(areaMax, area);
//
//                rightPointer--;
//            }
//        }
//
//        return areaMax;
//    }

    //    Following is its summary:
    //
    //    Consider we start with i = 0 and j = height.size() - 1. That is, i points to the first column and j points to the last column.
    //    Now suppose that h(i)>h(j) (we are not loosing generality by this assumption)
    //    We calculate the water capacity for the i, j. It will be h(j)*(j-i).
    //    Now see that if we fix j at height.size() - 1 and vary i, we cannot get a greater water capacity. Why?
    //    capacity = min of both heights * width between them. Since capacity is the product of these two terms, we will look at each term individually.
    //    First about the width. It is easy to see that for all other i's (i = 1, 2,... ,height.size()-2) we will have a lesser width.
    //    Second, the height will be the minimum of the column at i and at j, i.e. min(h(i),h(j)). But this value will be always less than h(j)
    //    So both factors in the calculation of the capacity will be smaller and hence we can skip considering all the cases where i = 1, 2, 3, ..., height.size()-2 and j = height.size()-1
    //    Which basically means that we can simply move j to j-1.
    //
    //    This is how I understood it and I hope this explanation makes it easy to understand.

    // Strat 2 - only move the smaller height
    // RESULT: 5ms
    public int maxArea2(int[] height) {
        int areaMax = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;

        while (leftPointer < rightPointer) {

            int area = Math.min(height[leftPointer], height[rightPointer]) * (rightPointer - leftPointer);
            areaMax = Math.max(area, areaMax);

            if (height[leftPointer] > height[rightPointer]) {
                rightPointer--;
            }
            else {
                leftPointer++;
            }
        }

        return areaMax;
    }

    // Strat 3 - Similar to strat 2 but even faster -
    // skips computing area if new height is less than previous height
    // RESULT: 2ms
    public int maxArea3(int[] height) {
        int areaMax = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        int currHeight = 0;

        while (leftPointer < rightPointer) {

            currHeight = Math.min(height[leftPointer], height[rightPointer]);
            int area = currHeight * (rightPointer - leftPointer);
            areaMax = Math.max(area, areaMax);

            while (leftPointer < rightPointer && height[leftPointer] <= currHeight) {
                leftPointer++;
            }
            while (leftPointer < rightPointer && height[rightPointer] <= currHeight) {
                rightPointer--;
            }
        }

        return areaMax;
    }


}
