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
        String s1 = "adc";
        String s2 = "dcda";

        System.out.println(start.checkInclusion(s1, s2));
    }


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

                // s2Map.put(ch, s2Map.getOrDefault(ch, 0) + 1);
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
