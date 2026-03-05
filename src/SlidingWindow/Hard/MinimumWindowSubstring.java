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
        System.out.println(start.minWindow3(s, t));
    }

    // Strategy 3:
    // Similar to strat 2 but this time we only care about chars in string t.
    // RESULT: 121ms - same as strat 2 :(
    public String minWindow3(String s, String t) {

        // Edge case
        if (t.length() > s.length()) {
            return "";
        }

        String ans = "";

        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> sMap = new HashMap<>();
        int matches = 0;        // counter to track if substring is valid
        int left = 0, right = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);

            if (!tMap.containsKey(ch)) {
                right++;
                continue;
            }

            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            if (sMap.get(ch).equals(tMap.get(ch))) {
                matches++;
            }

            // increment left ptr until substring is no longer valid
            while (matches == tMap.size()) {
                // valid substring

                if (right - left + 1 < ans.length() || ans.isEmpty()) {
                    ans = s.substring(left, right + 1);
                }

                if (!tMap.containsKey(s.charAt(left))) {
                    left++;
                    continue;
                }

                // Decrement char count, update matches then increment left ptr
                sMap.put(s.charAt(left), sMap.getOrDefault(s.charAt(left), 0) - 1);
                if (sMap.get(s.charAt(left)) + 1 == tMap.get(s.charAt(left))) {
                    matches--;
                }
                left++;
            }
            right++;
        }
        return ans;
    }


    // Strategy 2:
    // Similar to strat 1 but optimize method for checking a valid substring.
    // Achieve this by using a counter (matches) to keep track of number of chars that match.
    // Time Complexity(m + n) - RESULT: 121ms - Beats 10.54% (much faster but still kinda slow?)
    // Space Complexity: O(n + m) - 48.58MB - beats 5.92%
    public String minWindow2(String s, String t) {
        // Edge case
        if (t.length() > s.length()) {
            return "";
        }

        String ans = "";

        // Setting up hashmap that counts the chars in the strings
        Map<Character, Integer> tCharCount = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            tCharCount.put(c, 0);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            tCharCount.put(c, 0);
        }

        Map<Character, Integer> windowCharCount = new HashMap<>(tCharCount);
        int matches = 52;       // keep track of number of chars that match between tCharCount and windowCharCount

        // Count chars in t
        for (int i = 0; i < t.length(); i++) {
            if (tCharCount.getOrDefault(t.charAt(i), 0) == 0) {
                matches--;
            }
            tCharCount.put(t.charAt(i), tCharCount.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);

            windowCharCount.put(ch, windowCharCount.getOrDefault(ch, 0) + 1);
            if (windowCharCount.get(ch).equals(tCharCount.get(ch))) {
                matches++;
            }

            if (matches == 52) {    // Much faster check than before
                // valid substring
                if (right - left + 1 < ans.length() || ans.isEmpty()) {
                    // update answer
                    ans = s.substring(left, right + 1);
                }

                // Increment left until substring is no longer valid
                while (left <= right) {
                    windowCharCount.put(s.charAt(left), windowCharCount.get(s.charAt(left)) - 1);
                    if (windowCharCount.get(s.charAt(left)) + 1 == tCharCount.get(s.charAt(left))) {
                        matches--;
                    }
                    left++;

                    if (matches == 52) {
                        // valid substring
                        if (right - left + 1 < ans.length() || ans.isEmpty()) {
                            // update answer
                            ans = s.substring(left, right + 1);
                        }
                    }
                    else {
                        break;
                    }
                }
            }

            right++;
        }
        return ans;
    }

    // Strategy 1:
    // Have left and right ptr starting at 0.
    // Move right ptr until a valid substring (string b/w left and right ptr) is found.
    // Once a valid substring is found, move left ptr until substring is no longer valid.
    // Repeat until right ptr reaches the last character, and left ptr is incremented until substring is no longer valid.
    // Return the shortest valid substring.

    // Time Complexity: O(m + n) but unoptimized
    // RESULT: 337ms Beats 5.03% - Slow

    public String minWindow(String s, String t) {

        // Edge case
        if (s.length() < t.length()) {
            return "";
        }

        // Count all chars in t
        Map<Character, Integer> tCharCount = new HashMap<>();
        Map<Character, Integer> windowCharCount = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tCharCount.put(t.charAt(i), tCharCount.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int right;

        for (right = 0; right < t.length(); right++) {

            char ch = s.charAt(right);
            windowCharCount.put(ch, windowCharCount.getOrDefault(ch, 0) + 1);
        }

        if (tCharCount.equals(windowCharCount)) {
            return s.substring(left, right);
        }

        String ans = "";
        Set<Map.Entry<Character,Integer>> tSet = tCharCount.entrySet();

        while (right < s.length()) {
            windowCharCount.put(s.charAt(right), windowCharCount.getOrDefault(s.charAt(right), 0) + 1);

            // check if substring is valid  - this check is O(52) could be optimized.
            boolean valid = true;
            for (Map.Entry entry : tSet) {
                Character c = (Character) entry.getKey();
                Integer val = (Integer) entry.getValue();

                if (windowCharCount.getOrDefault(c, 0) < val) {
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

                        windowCharCount.put(s.charAt(left), windowCharCount.getOrDefault(s.charAt(left), 0) - 1);
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
