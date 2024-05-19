package Stack.Easy;
//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//        An input string is valid if:
//
//        Open brackets must be closed by the same type of brackets.
//        Open brackets must be closed in the correct order.
//        Every close bracket has a corresponding open bracket of the same type.
//
//
//
//        Example 1:
//
//        Input: s = "()"
//        Output: true
//
//        Example 2:
//
//        Input: s = "()[]{}"
//        Output: true
//
//        Example 3:
//
//        Input: s = "(]"
//        Output: false
//
//
//
//        Constraints:
//
//        1 <= s.length <= 104
//        s consists of parentheses only '()[]{}'.

import ArraysAndHashing.Medium.ValidSudoku;

import java.util.*;

public class ValidParentheses {

    // Strat 1
    // Use the stack data type to store open parenthesis and poll them when a closing parenthesis is detected
    // Last in first out - LIFO
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.add(ch);
            }
            else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char openParenthesis = stack.pop();
                if (openParenthesis != '(') {
                    return false;
                }
            }
            else if (ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char openParenthesis = stack.pop();
                if (openParenthesis != '{') {
                    return false;
                }
            }
            else if (ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char openParenthesis = stack.pop();
                if (openParenthesis != '[') {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidParentheses start = new ValidParentheses();
        String input = "([)]";
        System.out.println(start.isValid(input));
    }
}
