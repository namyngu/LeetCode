//424. Longest Repeating Character Replacement
//Medium
//        Topics
//premium lock icon
//        Companies
//You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
//
//Return the length of the longest substring containing the same letter you can get after performing the above operations.
//
//
//
//        Example 1:
//
//Input: s = "ABAB", k = 2
//Output: 4
//Explanation: Replace the two 'A's with two 'B's or vice versa.
//Example 2:
//
//Input: s = "AABABBA", k = 1
//Output: 4
//Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//The substring "BBBB" has the longest repeating letters, which is 4.
//There may exists other ways to achieve this answer too.
//
//
//Constraints:
//
//        1 <= s.length <= 105
//s consists of only uppercase English letters.
//0 <= k <= s.length

package SlidingWindow.Medium;

import java.util.HashMap;
import java.util.Set;

public class LongestRepeatingChar {

    public static void main(String[] args) {
        LongestRepeatingChar start = new LongestRepeatingChar();
        int ans = start.characterReplacement2(
                "AABABBA",
                1
        );

        System.out.println("The answer is: " + ans);
    }

    // Strategy 2
    // Strategy is same as strat 1 but the idea is we don't have to decrement the maxFreq when we move the left ptr.
    // Time Complexity O(n)
    // RESULT: 61ms beats 7.26%
    public int characterReplacement2(String s, int k) {

        // Edge case
        if (s.length() < 2) {
            return s.length();
        }

        int left = 0;
        int right = 0;
        int maxL = 0;

        int maxFreq = 0;    // Tracks the count of most frequent character.

        HashMap<Character, Integer> charFreq = new HashMap<>();     // Store frequency of each char between left and right ptr

        while (right <= s.length() - 1) {

            charFreq.put(s.charAt(right), charFreq.getOrDefault(s.charAt(right), 0) + 1);

            if (charFreq.get(s.charAt(right)) > maxFreq) {
                // new character is the most frequent char.
                maxFreq = charFreq.getOrDefault(s.charAt(right), 0);
                charFreq.put(s.charAt(right), maxFreq);

                maxL = Math.max(maxL, right - left + 1);

                right++;
            }
            else {
                // new char is <= to the most frequent char.

                // Check if our longest substring is still valid
                if (right - left + 1 - maxFreq <= k) {
                    // substring is valid
                    maxL = Math.max(maxL, right - left + 1);
                    right++;
                }
                else {
                    // substring not valid
                    // increment left ptr

                    charFreq.put(s.charAt(left), charFreq.get(s.charAt(left)) - 1);

                    if (charFreq.get(s.charAt(left)) <= 0) {
                        charFreq.remove(s.charAt(left));
                    }

                    left++;

                    while (true) {
                        // keep incrementing left ptr until substring is valid

                        // Get max freq
                        // Time complexity for this is O(26)
                        // TODO: Optimize this part in later strategies
                        maxFreq = 0;
                        Set<Character> charSet = charFreq.keySet();
                        for (char ch : charSet) {
                            maxFreq = Math.max(maxFreq, charFreq.get(ch));
                        }

                        if (right - left + 1 - maxFreq <= k) {
                            // valid substring
                            maxL = Math.max(maxL, right - left + 1);
                            right++;
                            break;
                        }
                        else {
                            // substring not valid
                            charFreq.put(s.charAt(left), charFreq.get(s.charAt(left)) - 1);

                            if (charFreq.get(s.charAt(left)) <= 0) {
                                charFreq.remove(s.charAt(left));
                            }

                            left++;
                        }
                    }
                }
            }
        }

        return maxL;
    }

    // Strategy 1
    // Time Complexity: O(n) - RESULT: 80ms - beats 5.06% slow :(
    // Works but not optimized.
    public int characterReplacement(String s, int k) {

        int len = s.length();
        // Edge case
        if (len < 2) {
            return len;
        }

        int left = 0;
        int right = 1;
        int maxL = 1;

        Character c = s.charAt(0);      // character currently "counting"

        // stores frequency of each char between the left and right ptr
        // E.g. (A,2)
        HashMap<Character, Integer> charFreq = new HashMap<>();
        charFreq.put(c, 1);

        while (right < len) {

            if (s.charAt(right) == c) {
                maxL = Integer.max(maxL, right - left + 1);
                charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);


            }
            else {
                Character tmpC = s.charAt(right);
                charFreq.put(tmpC, charFreq.getOrDefault(tmpC, 0) + 1);

                // calculate how many chars have been replaced included this one
                // length - char frequency
                int kUsed = right - left + 1 - charFreq.get(c);

                if (kUsed > k) {
                    // First check all characters in charFreq to see if there are any plausible substring, before deciding to increment left ptr.
                    // If non exists, increment left ptr.
                    boolean incrementLeft = true;

                    Set<Character> charSet = charFreq.keySet();
                    for (Character ch : charSet) {
                        if (ch == c) {
                            continue;
                        }

                        int tmpKUsed = right - left + 1 - charFreq.get(ch);
                        if (tmpKUsed <= k) {
                            // no need to increment left ptr, new char is a plausible substring.
                            c = tmpC;
                            maxL = Integer.max(maxL, right - left + 1);
                            incrementLeft = false;
                        }
                    }

                    if (incrementLeft) {
                        // Everytime we increment left ptr, check all chars in charFreq to see if there's a plausible substring.
                        // If not, we increment left ptr and repeat.
                        while (left < right && incrementLeft) {
                            Set<Character> tmpSet = charFreq.keySet();
                            for (Character ch : tmpSet) {
                                int tmpKUsed = right - left + 1 - charFreq.get(ch);

                                if (tmpKUsed <= k) {
                                    c = ch;
                                    maxL = Integer.max(right - left + 1, maxL);
                                    incrementLeft = false;
                                    break;
                                }
                            }

                            if (incrementLeft) {
                                // increment left.
                                charFreq.put(s.charAt(left), charFreq.get(s.charAt(left)) - 1);
                                left++;
                                c = s.charAt(left);
                            }
                        }
                    }
                }
                else {
                    maxL = Integer.max(right - left + 1, maxL);
                }
            }

            right++;
        }

        return maxL;
    }


}
