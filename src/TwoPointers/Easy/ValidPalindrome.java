package TwoPointers.Easy;

//A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//        Given a string s, return true if it is a palindrome, or false otherwise.
//
//
//
//        Example 1:
//
//        Input: s = "A man, a plan, a canal: Panama"
//        Output: true
//        Explanation: "amanaplanacanalpanama" is a palindrome.
//
//        Example 2:
//
//        Input: s = "race a car"
//        Output: false
//        Explanation: "raceacar" is not a palindrome.
//
//        Example 3:
//
//        Input: s = " "
//        Output: true
//        Explanation: s is an empty string "" after removing non-alphanumeric characters.
//        Since an empty string reads the same forward and backward, it is a palindrome.

import java.util.ArrayDeque;
import java.util.Deque;


// Strat 1
// 1. Use Deque to store all alphanumeric characters
// 2. Compare first and last element of Deque
// Time Complexity: O(n)
// RESULT: 4ms
public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome start = new ValidPalindrome();
        String input = "A man, a plan, a canal: Panama";
        start.isPalindrome(input);
    }

    public boolean isPalindrome(String s) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                deque.add(Character.toLowerCase(ch));
            }
        }

        while (deque.size() > 1) {
            if (deque.pollFirst() != deque.pollLast()) {
                return false;
            }
        }

        return true;
    }
}
