//76. Minimum Window Substring
//        Hard
//Topics
//premium lock icon
//        Companies
//Hint
//Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//The testcases will be generated such that the answer is unique.
//
//
//
//        Example 1:
//
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
//        Example 2:
//
//Input: s = "a", t = "a"
//Output: "a"
//Explanation: The entire string s is the minimum window.
//Example 3:
//
//Input: s = "a", t = "aa"
//Output: ""
//Explanation: Both 'a's from t must be included in the window.
//Since the largest window of s only has one 'a', return empty string.
//
//
//Constraints:
//
//m == s.length
//n == t.length
//1 <= m, n <= 105
//s and t consist of uppercase and lowercase English letters.

package SlidingWindow.Hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MinimumWindowSubstring {
    public static void main(String[] args) {

        MinimumWindowSubstring start = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(start.minWindow(s, t));
    }

    // Strategy 1:
    // Have left and right ptr starting at 0.
    // Move right ptr until a valid substring (string b/w left and right ptr) is found.
    // Once a valid substring is found, move left ptr until substring is no longer valid.
    // Repeat until right ptr reaches the last character, and left ptr is incremented until substring is no longer valid.
    // Return the shortest valid substring.

    // Time Complexity: O(m + n) but unoptimized
    // RESULT: 554ms Beats 5.03% - Slow

    public String minWindow(String s, String t) {

        // Edge case
        if (s.length() < t.length()) {
            return "";
        }

        // Count all chars in t
        Map<Character, Integer> tCharCount = new HashMap<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            tCharCount.put(ch, 0);
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            tCharCount.put(ch, 0);
        }

        Map<Character, Integer> windowCharCount = new HashMap<>(tCharCount);

        for (int i = 0; i < t.length(); i++) {
            tCharCount.put(t.charAt(i), tCharCount.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int right;

        for (right = 0; right < t.length(); right++) {

            char ch = s.charAt(right);
            windowCharCount.put(ch, windowCharCount.get(ch) + 1);
        }

        if (tCharCount.equals(windowCharCount)) {
            return s.substring(left, right);
        }

        String ans = "";
        Set<Map.Entry<Character,Integer>> tSet = tCharCount.entrySet();

        while (right < s.length()) {
            windowCharCount.put(s.charAt(right), windowCharCount.get(s.charAt(right)) + 1);

            // check if substring is valid  - this check is O(52) could be optimized.
            boolean valid = true;
            for (Map.Entry entry : tSet) {
                Character c = (Character) entry.getKey();
                Integer val = (Integer) entry.getValue();

                if (windowCharCount.get(c) < val) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                // valid substring - tCharCount is a subset of windowCharCount
                if (right - left + 1 < ans.length() || ans.isEmpty()) {
                    ans = s.substring(left, right + 1);
                }

                while (left <= right) {
                    // check if substring is valid  - this check is O(52) could be optimized.
                    // Feels redundant checking twice.
                    boolean isValid = true;
                    for (Map.Entry entry : tSet) {
                        Character c = (Character) entry.getKey();
                        Integer val = (Integer) entry.getValue();

                        if (windowCharCount.get(c) < val) {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        // valid substring - tCharCount is a subset of windowCharCount
                        if (right - left + 1 < ans.length() || ans.isEmpty()) {
                            ans = s.substring(left, right + 1);
                        }

                        windowCharCount.put(s.charAt(left), windowCharCount.get(s.charAt(left)) - 1);
                        left++;
                    }
                    else {
                        break;
                    }
                }
                right++;
            }
            else {

                right++;
            }
        }
        return ans;
    }
}
