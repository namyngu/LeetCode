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

        int[] nums1 = {0,0};
        int[] nums2 = {0,0};

        double ans = start.findMedianSortedArrays(nums1, nums2);
        System.out.println("The answer is " + ans);
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int lenTotal = nums1.length + nums2.length;

        List<Integer> mergedArray = new ArrayList<>();

        // if the merged array is odd
        if (lenTotal % 2 != 0) {

            int medianIndex = (int) Math.floor(lenTotal / 2.0);

            // Pick a number from array1 using binary search
            int left = 0;
            int right = nums1.length - 1;

            while (left <= right) {
                int middle = (int) Math.floor((right - left) / 2.0) + left;


            }
        }
    }


    // Strategy: Pick the middle number in the smaller sized array.
    // Check the index it will be located in the larger array using binary search.
    // Compare this index with the index of the median if the arrays are merged.
    // If less then pick another number in the smaller sized array (middle of left side) and repeat.
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



