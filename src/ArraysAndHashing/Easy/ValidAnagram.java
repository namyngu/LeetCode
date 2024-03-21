package ArraysAndHashing.Easy;

import java.util.HashMap;
import java.util.Objects;

class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram start = new ValidAnagram();
        String str1 = "anagram";
        String str2 = "nanogram";
        System.out.println(start.isAnagram2(str1, str2));
    }

    // strategy 1 - sort all the letters in alphabetical order then compare them
    // Time complexity - O(nlogn), space ~ O(n) but some interviewers think it's O(1)?
    public boolean isAnagram(String s, String t) {
        // convert strings to char arrays
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        mergeSort(sChars);
        mergeSort(tChars);

        String sSorted = new String(sChars);
        String tSorted = new String(tChars);

        if (sSorted.equals(tSorted)) {
            return true;
        }
        else {
            return false;
        }
    }

    // Strategy 2 - count the occurrences of each character using a hashmap
    // Time complexity - O(n), space complexity O(n)
    public boolean isAnagram2(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        // count occurrences of characters and map it to the hashmap
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (!sMap.containsKey(ch)) {
                sMap.put(ch, 1);
            }
            else {
                sMap.put(ch, sMap.get(ch) + 1);     // increment value by 1
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);

            if (!tMap.containsKey(ch)) {
                tMap.put(ch, 1);
            }
            else {
                tMap.put(ch, tMap.get(ch) + 1);     // increment value by 1
            }
        }

        // iterate through the hashmap of s and compare with t
        for (char ch : sMap.keySet()) {
            if (!tMap.containsKey(ch)) {
                return false;
            }

            //Cannot use != for non-primitive Integer types!!!
            // When comparing Number objects such as Integer, Double, etc., using the != operator, it compares their references, not their values.
            if (!Objects.equals(tMap.get(ch), sMap.get(ch))) {
                return false;
            }
        }

        return true;
    }

    public char[] mergeSort(char[] array) {
        int length = array.length;
        if (length <= 1) {
            return array;
        }

        char[] left;
        char[] right;

        // break array in half (accounts for both even & odd)
        int middle = (int) Math.floor(length/2.0);

        left = new char[middle];
        right = new char[length - middle];  // if array is odd right array is larger

        // populate left and right arrays
        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = array[i + middle];
        }

        // Keep breaking in half until there's 1 char left then return that string back
        left = mergeSort(left);
        right = mergeSort(right);

        // merge left and right array
        return merge(left, right, array);
    }

    // Merge arrays together
    public char[] merge(char[] leftArray, char[] rightArray, char[] mergeArray) {
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;

        int i = 0, j = 0, k = 0;  // i is left array pointer, j is right array pointer and k is the mergedArray pointer

        while (k < leftSize + rightSize) {
            // if left array is empty fill mergeArray with rest of right array
            if (i >= leftSize && j < rightSize) {
                for (; j < rightSize; j++, k++) {
                    mergeArray[k] = rightArray[j];
                }
            }

            // if right array is empty fill mergeArray with rest of left array
            else if (j >= rightSize && i < leftSize) {
                for (; i < leftSize; i++, k++) {
                    mergeArray[k] = leftArray[i];
                }
            }

            else if ((int) leftArray[i]  < (int) rightArray[j]) {
                mergeArray[k] = leftArray[i];
                i++;
                k++;
            }
            else if ((int) leftArray[i] > (int) rightArray[j]) {
                mergeArray[k] = rightArray[j];
                j++;
                k++;
            }
            else {
                mergeArray[k] = leftArray[i];
                mergeArray[k + 1] = rightArray[j];
                i++;
                j++;
                k = k + 2;
            }
        }

        return mergeArray;
    }
}
