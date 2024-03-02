package Easy;

class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram start = new ValidAnagram();
        start.isAnagram("anagram", "nagaram");
    }


    // strategy - sort all the letters in alphabetical order then compare them
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
