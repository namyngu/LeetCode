//4. Median of Two Sorted Arrays
//        Hard
//Topics
//premium lock icon
//        Companies
//Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//The overall run time complexity should be O(log (m+n)).
//
//
//
//Example 1:
//
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
//Example 2:
//
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
//Constraints:
//
//nums1.length == m
//nums2.length == n
//0 <= m <= 1000
//        0 <= n <= 1000
//        1 <= m + n <= 2000
//        -106 <= nums1[i], nums2[i] <= 106

package BinarySearch.Hard;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        MedianOfTwoSortedArrays start = new MedianOfTwoSortedArrays();

        int[] nums1 = {1,3};
        int[] nums2 = {2};

        double ans = start.findMedianSortedArrays3(nums1, nums2);
        System.out.println("The answer is " + ans);
    }

    //Strategy 3: Neetcode solution
    // 1. Find the left partition of the merged array by:
    //  - Initially take half of the elements of the smaller array.
    //  - Compute how many more elements we need to complete the left partition.
    //  - Take those elements from the larger array (first half).
    //  - validate if left partition is valid.
    //  - if not, use binary search to take more or less elements from the smaller array then repeat.
    // 2. Once we have the left partition compute the median.
    //
    // Side note: the edge cases here are very tricky due to out of bounds problem.
    // Resolved by using infinities

    // RESULT: 2ms - beats 46.49%
    // Time complexity O(log(min(m, n)))
    // Space complexity: O(1)
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {

        // Edge cases
        if (nums1.length == 0) {
            if (nums2.length % 2 != 0) {
                return nums2[nums2.length / 2];
            }
            else {
                return ((nums2[nums2.length / 2] + nums2[(nums2.length / 2) - 1]) / 2.0);
            }
        }
        else if (nums2.length == 0) {
            if (nums1.length % 2 != 0) {
                return nums1[nums1.length / 2];
            }
            else {
                return ((nums1[nums1.length / 2] + nums1[(nums1.length / 2) - 1]) / 2.0);
            }
        }

        int lenTotal = nums1.length + nums2.length;
        int half = lenTotal / 2;        // integer division in java truncates towards 0 by default (equivalent to Math.floor)

        // A is small array, B is large array
        int[] A = nums1, B = nums2;

        if (nums2.length < nums1.length) {
            A = nums2;
            B = nums1;
        }

        int left = 0;
        int right = A.length - 1;

        // since median is guaranteed
        while (true) {
            int i = (int) Math.floor((right - left) / 2.0) + left;   // middle index of small array

            // calculate the right ptr of the large array (no middle since we are only doing binary search on the smaller array).
            // the right ptr is the right index of the left partition in the large array.
            int j = half - (i + 1) - 1;

            // Dealing with edge cases by assigning infinities - SUPER USEFUL

            // In array A, A_left is the max value of the left partition.
            double A_left = i >= 0 ? A[i] : Double.NEGATIVE_INFINITY;
            // In array A, A_right is the min value of the right partition.
            double A_right = i + 1 <= A.length - 1 ? A[i + 1] : Double.POSITIVE_INFINITY;

            double B_left = j >= 0 ? B[j] : Double.NEGATIVE_INFINITY;
            double B_right = j + 1 <= B.length - 1 ? B[j + 1] : Double.POSITIVE_INFINITY;

            // validate if we have the left partition of the merged array.
            if (A_left <= B_right && B_left <= A_right) {
                // correct partition
                if (lenTotal % 2 != 0) {
                    return Math.min(A_right, B_right);
                }
                else {
                    return (Math.max(A_left, B_left) + Math.min(A_right, B_right)) / 2.0;
                }
            }
            else if (A_left > B_right) {
                // take less elements from A
                right = i - 1;
            }
            else {
                // take more elements from A
                left = i + 1;
            }
        }
    }

    // Strategy 2: Similar to strategy 1, pick a number from the smaller array (and check for copies).
    // Using this chosen number, check what index it is in the bigger array using binary search (and check for any copies here).
    // Once we know what index that number is in both arrays and the no. of copies, we can then compute its index in the merged array
    // Return that number if it's the median.
    // Time Complexity: O(log(n + m))
    // RESULT: 5ms - Beats 23.71% finally a success but method is slightly unoptimized
    // Not bad considering it's your first solution.
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // Edge cases - one of the array is empty
        if (nums1.length == 0) {
            if (nums2.length % 2 != 0) {
                return nums2[((int) Math.floor((nums2.length) / 2.0))];
            }
            else {
                int medianR = nums2[nums2.length / 2];
                int medianL = nums2[(nums2.length / 2) - 1];
                return (medianR + medianL) / 2.0;
            }
        }
        else if (nums2.length == 0) {
            if (nums1.length % 2 != 0) {
                return nums1[((int) Math.floor((nums1.length) / 2.0))];
            }
            else {
                int medianR = nums1[nums1.length / 2];
                int medianL = nums1[(nums1.length / 2) - 1];
                return (medianR + medianL) / 2.0;
            }
        }

        int[] smallArray;
        int[] bigArray;
        if (nums1.length <= nums2.length) {
            smallArray = nums1;
            bigArray = nums2;
        }
        else {
            smallArray = nums2;
            bigArray = nums1;
        }


        int lenTotal = nums1.length + nums2.length;

        // if the merged array is odd
        if (lenTotal % 2 != 0) {

            int medianIndex = (int) Math.floor(lenTotal / 2.0);

            try {
                return findMedian(smallArray, bigArray, medianIndex);
            } catch (Exception e) {
                try {
                    // Median not in first array.
                    return findMedian(bigArray, smallArray, medianIndex);
                } catch (Exception f) {
                    System.out.println("Error something went wrong!");
                }
            }
        }
        // merge array is even
        else {
            int medianIndexR = lenTotal / 2;
            int medianIndexL = medianIndexR - 1;

            double medianL = -0.000222;
            double medianR = -0/000333;

            // Find medianL
            try {
                medianL = findMedian(smallArray, bigArray, medianIndexL);
            } catch (Exception e) {
                try {
                    // Median not in first array.
                    medianL = findMedian(bigArray, smallArray, medianIndexL);
                } catch (Exception f) {
                    System.out.println("Error something went wrong!");
                }
            }

            // Find medianR
            try {
                medianR = findMedian(smallArray, bigArray, medianIndexR);
            } catch (Exception e) {
                try {
                    // Median not in first array.
                    medianR = findMedian(bigArray, smallArray, medianIndexR);
                } catch (Exception f) {
                    System.out.println("Error something went wrong!");
                }
            }

            return (medianR + medianL) / 2.0;
        }

        // Shouldn't get here.
        return -9999;
    }

    // Method to pick a number from the source array and compares it with the searchArray to calculate its index if the two arrays were merged.
    // Assumes the merged array is odd.
    // Returns the median.
    double findMedian(int[] sourceArray, int[] searchArray, int medianIndex) {

        // Pick a number from sourceArray using binary search
        int left = 0;
        int right = sourceArray.length - 1;

        while (left <= right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            // Computes the starting index and no. of copies of a chosen number in an array.
            int[] indexAndFreq1 = checkCopies(middle, sourceArray);       // Array of size 2: [starting index, no. of copies].

            // Find where the index of the chosen number in the merge array
            int[] indexAndFreq2 = binarySearchArray(searchArray, sourceArray[middle]);

            // Compute the starting index of merge array and total no. of copies
            int[] mergeIndexAndFreq = {indexAndFreq1[0] + indexAndFreq2[0], indexAndFreq1[1] + indexAndFreq2[1]};

            // E.g. if merge array is like [2,3,4,8,8,8,10,12,13]
            if (medianIndex >= mergeIndexAndFreq[0] && medianIndex <= (mergeIndexAndFreq[0] + mergeIndexAndFreq[1] - 1)) {
                // found median
                return sourceArray[middle];
            }
            else if (mergeIndexAndFreq[0] > medianIndex) {
                // chosen number is too high.
                right = middle - 1;

            }
            else {
                // chosen number is too low.
                left = middle + 1;
            }
        }

        throw new IllegalArgumentException("Median not found");
    }

    // Method to pick a number from the source array and compares it with the searchArray to calculate its index if the two arrays were merged.
    // Assumes the merged array is even.
    // Returns the median.


    int[] checkCopies(int index, int[] array) {
        int left = index - 1;
        int right = index + 1;
        int[] ans = {index,1};  // [starting index, no. of copies]

        // Increment left and check if it's a duplicate value, then update ans.
        while (left >= 0 && array[left] == array[index]) {
            ans[1] = ans[1] + 1;
            ans[0] = ans[0] - 1;
            left--;
        }

        // Increment right and check if it's a duplicate value, then update ans.
        while (right <= array.length - 1 && array[right] == array[index]) {
            ans[1] = ans[1] + 1;
            right++;
        }

        return ans;
    }

    // For a given chosen number find its index in the search array.
    int[] binarySearchArray(int[] searchArray, int chosenNum) {
        int left = 0;
        int right = searchArray.length - 1;

        while (left < right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            if (searchArray[middle] < chosenNum) {
                left = middle + 1;
            }
            else if (searchArray[middle] > chosenNum) {
                right = middle - 1;
            }
            else {
                // check for copies
                return checkCopies(middle, searchArray);
            }
        }

        // left and right are equal
        if (chosenNum > searchArray[left]) {
            int index = left + 1;
            return new int[] {index, 0};
        }
        else if (chosenNum < searchArray[left]) {
            int index = left;
            return new int[] {index, 0};
        }
        else {
            int index = left;
            return new int[] {index, 1};
        }
    }

    double findMedian(int[] sourceArray, int[] searchArray, int medianIndexL, int medianIndexR) {

        int left = 0;
        int right = sourceArray.length - 1;
        boolean foundMedianL = false;
        boolean foundMedianR = false;
        int medianL = 0;
        int medianR = 0;

        // Pick a number from sourceArray using binary search
        while (left <= right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            // Checks if there are copies of this number.
            int[] indexAndFreq1 = checkCopies(middle, sourceArray);       // Array of size 2: [starting index, no. of copies].

            // Find where the index of the chosen number in the merge array
            int[] indexAndFreq2 = binarySearchArray(searchArray, sourceArray[middle]);

            // Compute the starting index of merge array and total no. of copies
            int[] mergeIndexAndFreq = {indexAndFreq1[0] + indexAndFreq2[0], indexAndFreq1[1] + indexAndFreq2[1]};

            // E.g. if merge array is like [2,3,4,8,8,8,10,12,13,14]
            if (mergeIndexAndFreq[0] < medianIndexL) {
                // median is on the right
                left = middle + 1;
            }
            else if (mergeIndexAndFreq[0] > medianIndexR) {
                // median is on the left
                right = middle - 1;
            }
            else if (medianIndexL >= mergeIndexAndFreq[0] && medianIndexL <= (mergeIndexAndFreq[0] + mergeIndexAndFreq[1]) &&
                    medianIndexR >= mergeIndexAndFreq[0] && medianIndexR <= (mergeIndexAndFreq[0] + mergeIndexAndFreq[1])) {
                // found both medians
                medianL = medianR = sourceArray[middle];
                return medianL;
            }

            else if (medianIndexL >= mergeIndexAndFreq[0] && medianIndexL <= (mergeIndexAndFreq[0] + mergeIndexAndFreq[1])) {
                // found medianL
                medianL = sourceArray[middle];
                foundMedianL = true;
            }
            else if (medianIndexR >= mergeIndexAndFreq[0] && medianIndexR <= (mergeIndexAndFreq[0] + mergeIndexAndFreq[1])) {
                // found medianR
                medianR = sourceArray[middle];
                foundMedianR = true;
            }

            if ( foundMedianL && foundMedianR) {
                return (double) medianL + medianR / 2.0;
            }
        }

        if (foundMedianL) {
            return medianL;
        }
        else if (foundMedianR) {
            return medianR;
        }
        else {
            throw new IllegalArgumentException("Median not found");
        }
    }



    // Strategy 1: Pick the middle number in the smaller sized array.
    // Check the index it will be located in the larger array using binary search.
    // Compare this index with the index of the median if the arrays are merged.
    // If less then pick another number in the smaller sized array (middle of left side) and repeat.

    // DOESN'T WORK can't handle copies
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int len1 = nums1.length;
//        int len2 = nums2.length;
//
//        int lenTotal = len1 + len2;
//
//        // if the merged array is odd
//        if (lenTotal % 2 != 0) {
//
//            int medianIndex = (int) Math.floor(lenTotal / 2.0);
//
//            int left = 0;
//            int right = len1 - 1;
//
//            // binary search array 1 for a number
//            while (left <= right) {
//                int middle = (int) Math.floor((right - left) / 2.0) + left;
//
//                // binary search second array for index of chosen number.
//                int foundIndex = findIndex(nums2, nums1[middle], middle);
//
//                if (foundIndex < medianIndex) {
//                    left = middle + 1;
//                }
//                else if (foundIndex > medianIndex) {
//                    right = middle - 1;
//                }
//                else {
//                    return nums1[middle];
//                }
//            }
//
//            // the median is in the second array
//            left = 0;
//            right = len2 - 1;
//
//            // binary search smaller array for a number
//            while (left <= right) {
//                int middle = (int) Math.floor((right - left) / 2.0) + left;
//
//                // binary search second array for index of chosen number.
//                int foundIndex = findIndex(nums1, nums2[middle], middle);
//
//                if (foundIndex < medianIndex) {
//                    left = middle + 1;
//                }
//                else if (foundIndex > medianIndex) {
//                    right = middle - 1;
//                }
//                else {
//                    return nums2[middle];
//                }
//            }
//        }
//        else {
//            // the merged array is even
//            int medianIndexR = (int) Math.floor(lenTotal / 2.0);
//            int medianIndexL = medianIndexR - 1;
//            int medianL = -9999;
//            int medianR = -9999;
//
//            int left = 0;
//            int right = len1 - 1;
//
//            // Pick a number from array 1
//            while (left <= right) {
//                int middle = (int) Math.floor((right - left) / 2.0) + left;
//
//                // binary search second array for index of chosen number.
//                int foundIndex = findIndex(nums2, nums1[middle], middle);
//
//                if (foundIndex < medianIndexL) {
//                    left = middle + 1;
//                }
//                else if (foundIndex > medianIndexR) {
//                    right = middle - 1;
//                }
//                else if (foundIndex == medianIndexL) {
//                    medianL = nums1[middle];
//                    if (medianR != -9999) {
//                        return ((double) (medianL + medianR) / 2.0);
//                    }
//
//                    left = middle + 1;
//                }
//                else {
//                    medianR = nums1[middle];
//                    if (medianL != -9999) {
//                        return ((double) (medianL + medianR) / 2.0);
//                    }
//
//                    right = middle - 1;
//                }
//            }
//
//            // check second array
//            left = 0;
//            right = len2 - 1;
//
//            // binary search second array for a number
//            while (left <= right) {
//                int middle = (int) Math.floor((right - left) / 2.0) + left;
//
//                // binary search second array for index of chosen number.
//                int foundIndex = findIndex(nums1, nums2[middle], middle);
//
//                if (foundIndex < medianIndexL) {
//                    left = middle + 1;
//                }
//                else if (foundIndex > medianIndexR) {
//                    right = middle - 1;
//                }
//                else if (foundIndex == medianIndexL) {
//                    medianL = nums2[middle];
//                    if (medianR != -9999) {
//                        return ((double) (medianL + medianR) / 2.0);
//                    }
//
//                    left = middle + 1;
//                }
//                else {
//                    medianR = nums2[middle];
//                    if (medianL != -9999) {
//                        return ((double) (medianL + medianR) / 2.0);
//                    }
//
//                    right = middle - 1;
//                }
//            }
//        }
//
//        return -9999;
//    }
//
//
//    // For a given chosen number find its index if the two arrays are merged.
//    int findIndex(int[] arr, int chosenNum, int indexOffset) {
//        int left = 0;
//        int right = arr.length - 1;
//
//        while (left < right) {
//            int middle = (int) Math.floor((right - left) / 2.0) + left;
//
//            if (arr[middle] < chosenNum) {
//                left = middle + 1;
//            }
//            else if (arr[middle] > chosenNum) {
//                right = middle - 1;
//            }
//            else {
//                return middle + indexOffset;
//            }
//        }
//
//        // left and right are equal
//        if (chosenNum > arr[left]) {
//            return left + 1 + indexOffset;
//        }
//        else {
//            return left + indexOffset;
//        }
//    }
}



