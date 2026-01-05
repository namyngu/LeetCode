//567. Permutation in String
//        Medium
//Topics
//premium lock icon
//        Companies
//Hint
//Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
//
//In other words, return true if one of s1's permutations is the substring of s2.
//
//
//
//Example 1:
//
//Input: s1 = "ab", s2 = "eidbaooo"
//Output: true
//Explanation: s2 contains one permutation of s1 ("ba").
//Example 2:
//
//Input: s1 = "ab", s2 = "eidboaoo"
//Output: false
//
//
//Constraints:
//
//        1 <= s1.length, s2.length <= 104
// s1 and s2 consist of lowercase English letters.


package SlidingWindow.Medium;

import java.util.HashMap;

public class PermutationInString {

    public static void main(String[] args) {

        PermutationInString start = new PermutationInString();
        String s1 = "trinitrophenylmethylnitramine";
        String s2 = "dinitrophenylhydrazinetrinitrophenylmethylnitramine";

        // Expected true
        System.out.println(start.checkInclusion3(s1, s2));
//        start.test(s1, s2);
    }

//    public void test(String s1, String s2) {
//        HashMap<Character, Integer> s1Map = new HashMap<>();
//        HashMap<Character, Integer> s2Map = new HashMap<>();
//
//        for (int i = 0; i < s1.length(); i++) {
//            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
//        }
//
//        for (int i = 0; i < s2.length(); i++) {
//            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0) + 1);
//        }
//
//        System.out.println("S1 length: " + s1.length());
//        System.out.println("S2 length: " + s2.length());
//    }

    // Strategy 3
    // Neetcode optimized solution
    // Time Complexity - O(n) + O(m) + O(26)
    // RESULT: 9ms beats 45.98% - MUCH better
    // Space Complexity: O(1) - RESULT: 43.65MB beats 96.85% - very good
    public boolean checkInclusion3(String s1, String s2) {

        // Edge case
        if (s1.length() > s2.length()) {
            return false;
        }

        // Count frequency of each char in s1 and windowCharsCount
        int[] s1CharsCount = new int[26];        // a-z are the only possible chars
        int[] windowCharsCount = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1CharsCount[s1.charAt(i) - 'a']++;
            windowCharsCount[s2.charAt(i) - 'a']++;
        }

        // Setup initial window
        int left = 0;
        int right = s1.length() - 1;

        int matches = 0;        // checks the number of matches between the two arrays
        for (int i = 0; i < 26; i++) {
            if (s1CharsCount[i] == windowCharsCount[i]) {
                matches++;
            }
        }

        if (matches == 26) {
            return true;
        }

        left++;
        right++;

        while (right < s2.length()) {
            int prevLeftCharIndex = s2.charAt(left - 1) - 'a';
            int rightCharIndex = s2.charAt(right) - 'a';

            // Update array
            windowCharsCount[prevLeftCharIndex]--;
            windowCharsCount[rightCharIndex]++;

            // Update matches

            if (prevLeftCharIndex == rightCharIndex) {
                // Edge case - if the right index is same as prev left index (no changes go next).
                left++;
                right++;
                continue;
            }

            if (windowCharsCount[prevLeftCharIndex] == s1CharsCount[prevLeftCharIndex]) {
                matches++;
            }
            else if (windowCharsCount[prevLeftCharIndex] + 1 == s1CharsCount[prevLeftCharIndex]) {
                // They matched before
                matches--;
            }

            if (windowCharsCount[rightCharIndex] == s1CharsCount[rightCharIndex]) {
                matches++;
            }
            else if (windowCharsCount[rightCharIndex] - 1 == s1CharsCount[rightCharIndex]) {
                // They matched before
                matches--;
            }

            if (matches == 26) {
                return true;
            }

            left++;
            right++;
        }

        return false;
    }

    // Strategy 2
    // Time Complexity: O(n) + O(m)
    // RESULT: 1703ms - beats 5.01% even slower than before??
    public boolean checkInclusion2(String s1, String s2) {

        // Count frequency of each char in s1
        HashMap<Character, Integer> s1Chars = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Chars.put(s1.charAt(i), s1Chars.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;
        HashMap<Character, Integer> s2Chars = new HashMap<>();

        while (left <= s2.length() - s1.length()) {

            if (!s1Chars.containsKey(s2.charAt(left))) {
                left++;
                continue;
            }

            // found initial char
            s2Chars.clear();
            right = left;

            while (right <= s2.length() - 1) {

                if (s1Chars.containsKey(s2.charAt(right))) {
                    s2Chars.put(s2.charAt(right), s2Chars.getOrDefault(s2.charAt(right), 0) + 1);

                    // Check if permutation is valid
                    if (s2Chars.get(s2.charAt(right)).intValue() == s1Chars.get(s2.charAt(right)).intValue()) {     // Have to unbox numeric objects before comparing.
                        // permutation is valid

                        // check if permutation is found
                        if (right - left + 1 == s1.length()) {
                            return true;
                        }

                        right++;
                    }
                    else if (s2Chars.get(s2.charAt(right)) > s1Chars.get(s2.charAt(right))) {
                        // permutation not valid
                        left++;
                        break;
                    }
                    else {
                        right++;
                    }
                }
                else {
                    // permutation not valid
                    left = right + 1;
                    break;
                }
            }
        }
        return false;
    }

    // Strategy 1
    // Time Complexity: O(n * m)
    // RESULT: 1178ms beats 5.06% passed but slow
    // Space complexity: O(n) BEATS 9.08%
    public boolean checkInclusion(String s1, String s2) {

        // Map out the char frequencies of s1
        HashMap<Character, Integer> s1Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Character ch = s1.charAt(i);

            s1Map.put(ch, s1Map.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> s2Map = new HashMap<>();
        for (int i = 0; i <= (s2.length() - s1.length()); i++) {
            Character ch = s2.charAt(i);

            if (s1Map.containsKey(ch)) {
                s2Map.clear();

                int left = i;
                int right = i;

                boolean skip = false;

                while (right - left + 1 <= s1.length()) {

                    Character tmpChar = s2.charAt(right);
                    if (s1Map.containsKey(tmpChar)) {
                        if (s1Map.get(tmpChar) > s2Map.getOrDefault(tmpChar, 0)){
                            s2Map.put(tmpChar, s2Map.getOrDefault(tmpChar, 0) + 1);
                            right++;
                        }
                        else {
                            skip = true;
                            break;
                        }
                    }
                    else {
                        skip = true;
                        break;
                    }
                }

                if (skip){
                    continue;
                }

                // right index is 1 larger than the last index.
                if (right - left == s1.length()) {
                    return true;
                }
            }
        }
        return false;
    }
}
