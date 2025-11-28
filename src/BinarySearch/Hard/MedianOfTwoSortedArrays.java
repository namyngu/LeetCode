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

        int[] nums1 = {1,2};
        int[] nums2 = {3,4};

        double ans = start.findMedianSortedArrays2(nums1, nums2);
        System.out.println("The answer is " + ans);
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int lenTotal = nums1.length + nums2.length;

        int[] mergedArray = new int[lenTotal];      // may not be complete

        // if the merged array is odd
        if (lenTotal % 2 != 0) {

            int medianIndex = (int) Math.floor(lenTotal / 2.0);

            try {
                return findMedian(nums1, nums2, medianIndex);
            } catch (Exception e) {
                try {
                    // Median not in first array.
                    return findMedian(nums2, nums1, medianIndex);
                } catch (Exception f) {
                    System.out.println("Error something went wrong!");
                }
            }
        }
        // merge array is even
        else {
            int medianIndexR = lenTotal / 2;
            int medianIndexL = medianIndexR - 1;

            double median1 = -0.222;
            double median2 = -0.222;

            try {
                median1 =  findMedian(nums1, nums2, medianIndexL, medianIndexR); // could also include both medians
            } catch (Exception e) {
                try {
                    // No median in first array second array must have both medians.
                    return findMedian(nums2, nums1, medianIndexL, medianIndexR);
                } catch (Exception f) {
                    System.out.println("Error something went wrong!");
                }
            }

            try {
                median2 =  findMedian(nums2, nums1, medianIndexL, medianIndexR); // could also include both medians
                if (median1 != -0.222) {
                    return median1 + median2 / 2.0;
                }
            } catch (Exception e) {
                // median not found - both medians must be in median1
                return median1;
            }
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

            // Checks if there are duplicates of this number.
            int[] indexAndFreq1 = checkDuplicates(middle, sourceArray);       // Array of size 2: [starting index, no. of duplicates].

            // Find where the index of the chosen number in the merge array
            int[] indexAndFreq2 = binarySearchArray(searchArray, sourceArray[middle]);

            // Compute the starting index of merge array and total no. of duplicates
            int[] mergeIndexAndFreq = {indexAndFreq1[0] + indexAndFreq2[0], indexAndFreq1[1] + indexAndFreq2[1]};

            // E.g. if merge array is like [2,3,4,8,8,8,10,12,13]
            if (medianIndex >= mergeIndexAndFreq[0] && medianIndex <= (mergeIndexAndFreq[0] + mergeIndexAndFreq[1])) {
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

            // Checks if there are duplicates of this number.
            int[] indexAndFreq1 = checkDuplicates(middle, sourceArray);       // Array of size 2: [starting index, no. of duplicates].

            // Find where the index of the chosen number in the merge array
            int[] indexAndFreq2 = binarySearchArray(searchArray, sourceArray[middle]);

            // Compute the starting index of merge array and total no. of duplicates
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

    int[] checkDuplicates(int index, int[] array) {
        int left = index - 1;
        int right = index + 1;
        int[] ans = {index,0};  // [starting index, no. of duplicates]

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
                // check for duplicates
                return checkDuplicates(middle, searchArray);
            }
        }

        // left and right are equal
        if (chosenNum > searchArray[left]) {
            int index = left + 1;
            return new int[] {index, 0};
        }
        else {
            int index = left;
            return new int[] {index, 0};
        }
    }

    // Strategy: Pick the middle number in the smaller sized array.
    // Check the index it will be located in the larger array using binary search.
    // Compare this index with the index of the median if the arrays are merged.
    // If less then pick another number in the smaller sized array (middle of left side) and repeat.

    // DOESN'T WORK can't handle duplicates
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int lenTotal = len1 + len2;

        // if the merged array is odd
        if (lenTotal % 2 != 0) {

            int medianIndex = (int) Math.floor(lenTotal / 2.0);

            int left = 0;
            int right = len1 - 1;

            // binary search array 1 for a number
            while (left <= right) {
                int middle = (int) Math.floor((right - left) / 2.0) + left;

                // binary search second array for index of chosen number.
                int foundIndex = findIndex(nums2, nums1[middle], middle);

                if (foundIndex < medianIndex) {
                    left = middle + 1;
                }
                else if (foundIndex > medianIndex) {
                    right = middle - 1;
                }
                else {
                    return nums1[middle];
                }
            }

            // the median is in the second array
            left = 0;
            right = len2 - 1;

            // binary search smaller array for a number
            while (left <= right) {
                int middle = (int) Math.floor((right - left) / 2.0) + left;

                // binary search second array for index of chosen number.
                int foundIndex = findIndex(nums1, nums2[middle], middle);

                if (foundIndex < medianIndex) {
                    left = middle + 1;
                }
                else if (foundIndex > medianIndex) {
                    right = middle - 1;
                }
                else {
                    return nums2[middle];
                }
            }
        }
        else {
            // the merged array is even
            int medianIndexR = (int) Math.floor(lenTotal / 2.0);
            int medianIndexL = medianIndexR - 1;
            int medianL = -9999;
            int medianR = -9999;

            int left = 0;
            int right = len1 - 1;

            // Pick a number from array 1
            while (left <= right) {
                int middle = (int) Math.floor((right - left) / 2.0) + left;

                // binary search second array for index of chosen number.
                int foundIndex = findIndex(nums2, nums1[middle], middle);

                if (foundIndex < medianIndexL) {
                    left = middle + 1;
                }
                else if (foundIndex > medianIndexR) {
                    right = middle - 1;
                }
                else if (foundIndex == medianIndexL) {
                    medianL = nums1[middle];
                    if (medianR != -9999) {
                        return ((double) (medianL + medianR) / 2.0);
                    }

                    left = middle + 1;
                }
                else {
                    medianR = nums1[middle];
                    if (medianL != -9999) {
                        return ((double) (medianL + medianR) / 2.0);
                    }

                    right = middle - 1;
                }
            }

            // check second array
            left = 0;
            right = len2 - 1;

            // binary search second array for a number
            while (left <= right) {
                int middle = (int) Math.floor((right - left) / 2.0) + left;

                // binary search second array for index of chosen number.
                int foundIndex = findIndex(nums1, nums2[middle], middle);

                if (foundIndex < medianIndexL) {
                    left = middle + 1;
                }
                else if (foundIndex > medianIndexR) {
                    right = middle - 1;
                }
                else if (foundIndex == medianIndexL) {
                    medianL = nums2[middle];
                    if (medianR != -9999) {
                        return ((double) (medianL + medianR) / 2.0);
                    }

                    left = middle + 1;
                }
                else {
                    medianR = nums2[middle];
                    if (medianL != -9999) {
                        return ((double) (medianL + medianR) / 2.0);
                    }

                    right = middle - 1;
                }
            }
        }

        return -9999;
    }


    // For a given chosen number find its index if the two arrays are merged.
    int findIndex(int[] arr, int chosenNum, int indexOffset) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int middle = (int) Math.floor((right - left) / 2.0) + left;

            if (arr[middle] < chosenNum) {
                left = middle + 1;
            }
            else if (arr[middle] > chosenNum) {
                right = middle - 1;
            }
            else {
                return middle + indexOffset;
            }
        }

        // left and right are equal
        if (chosenNum > arr[left]) {
            return left + 1 + indexOffset;
        }
        else {
            return left + indexOffset;
        }
    }
}



