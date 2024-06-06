package Stack.Medium;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//You are given an array of strings tokens that represents a valid arithmetic expression in Reverse Polish Notation.
//
//        Return the integer that represents the evaluation of the expression.
//
//        The operands may be integers or the results of other operations.
//        The operators include '+', '-', '*', and '/'.
//        Assume that division between integers always truncates toward zero.
//
//        Example 1:
//
//        Input: tokens = ["1","2","+","3","*","4","-"]
//
//        Output: 5
//
//        Explanation: ((1 + 2) * 3) - 4 = 5
//
//        Constraints:
//
//        1 <= tokens.length <= 1000.
//        tokens[i] is "+", "-", "*", or "/", or a string representing an integer in the range [-100, 100].

public class ReversePolishNotation {

    public static void main(String[] args) {
        ReversePolishNotation rpn = new ReversePolishNotation();
        String[] input = {"2","1","+","3","*"};
        System.out.println(rpn.evalRPN(input));
    }


    //NOTE: In java all integer operations automatically rounds to 0 - exception is python
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        Set<String> operations = new HashSet<>();
        operations.add("+");
        operations.add("-");
        operations.add("*");
        operations.add("/");

        for (int i = 0; i < tokens.length; i++) {

            int sum = 0;
            String str = tokens[i];

            if (!operations.contains(str)) {
                stack.push(str);
            }
            else {
                try {
                    int operand2 = Integer.parseInt(stack.pop());
                    int operand1 = Integer.parseInt(stack.pop());

                    switch (str) {
                        case "+":
                            sum = operand1 + operand2;
                            break;

                        case "-":
                            sum = operand1 - operand2;
                            break;

                        case "*":
                            sum = operand1 * operand2;
                            break;

                        case "/":
                            sum = operand1 / operand2;
                            break;

                        default:
                            System.out.println("Error: this should never happen");
                            break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Failed to parse string into integer");
                }

                String strSum = String.valueOf(sum);
                stack.push(strSum);
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
