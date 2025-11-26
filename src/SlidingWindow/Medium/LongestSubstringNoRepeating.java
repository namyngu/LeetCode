//3. Longest Substring Without Repeating Characters
//        Solved
//Medium
//        Topics
//premium lock icon
//        Companies
//Hint
//Given a string s, find the length of the longest substring without duplicate characters.
//
//
//
//        Example 1:
//
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
//Example 2:
//
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//Example 3:
//
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
//
//
//        Constraints:
//
//        0 <= s.length <= 5 * 104
//s consists of English letters, digits, symbols and spaces.

package SlidingWindow.Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringNoRepeating {

    // Time Complexity: O(n)
    // RESULT: 6ms - Beats 68.77%
    public int lengthOfLongestSubstring(String s) {

        int len = s.length();
        Set<Character> set = new HashSet<>();
        Deque<Character> deque = new ArrayDeque<>();
        int maxL = 0;

        for (int i = 0; i < len; i++) {
            Character c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                deque.addLast(c);
                maxL = Integer.max(maxL, set.size());
            }
            else {
                while (deque.peekFirst() != c) {
                    Character tmp = deque.pollFirst();
                    set.remove(tmp);
                }

                Character tmp = deque.pollFirst();
                set.remove(tmp);

                set.add(c);
                deque.addLast(c);
                maxL = Integer.max(maxL, set.size());
            }
        }

        return maxL;
    }
}
