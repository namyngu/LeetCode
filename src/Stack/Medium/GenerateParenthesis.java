package Stack.Medium;
//You are given an integer n. Return all well-formed parentheses strings that you can generate with n pairs of parentheses.
//
//        Example 1:
//
//        Input: n = 1
//
//        Output: ["()"]
//
//        Example 2:
//
//        Input: n = 3
//
//        Output: ["((()))","(()())","(())()","()(())","()()()"]
//
//        Constraints:
//
//        1 <= n <= 7


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        List<String> ans = gp.generateParenthesis(3);
    }

    public GenerateParenthesis() {


    }

    // Strat: Think about the condition for possible moves:
    // Two conditions:
    // 1. next move can be open parenthesis if openParenthesis < n
    // 2. next move can be close parenthesis if closeParenthesis < openParenthesis

    // Pseudocode
    // 1. If openParenthesis < n - add open parenthesis to stack and go to next move
    // 2. If closeParenthesis < add close parenthesis to stack and go to next move
    // 3. If condition 1 or 2 is not met end move and add stack to String list.
    public List<String> generateParenthesis(int n) {
        Stack<String> stack = new Stack<>();
        int openParenthCount = 0;
        int closeParenthCount = 0;
        List<String> solution = new ArrayList<>();

        nextMove(stack, openParenthCount, closeParenthCount, n, solution);

        return solution;
    }

    // Method to determine the next possible moves and execute it
    public void nextMove(Stack<String> stack, int openParenthCount, int closeParenthCount, int n, List<String> solution) {

        // 3. No more possible moves - add well-formed parenthesis to solution list
        if (openParenthCount >= n && closeParenthCount >= openParenthCount) {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb.reverse();
            solution.add(sb.toString());
        }

        // If you can add open and close parenthesis
        else if ((openParenthCount < n) && (closeParenthCount < openParenthCount) ) {
            Stack<String> addOpen = new Stack<>();
            addOpen.addAll(stack);
            Stack<String> addClose = new Stack<>();
            addClose.addAll(stack);


            // add open parenthesis and go to next move
            addOpen.push("(");
            nextMove(addOpen, openParenthCount + 1, closeParenthCount, n, solution);

            // add close parenthesis and go to next move
            addClose.push(")");
            nextMove(addClose, openParenthCount, closeParenthCount + 1, n, solution);
        }

        // If you can add only open parenthesis
        else if (openParenthCount < n) {
            stack.push("(");

            // Go to next move - recursive function
            nextMove(stack, openParenthCount + 1, closeParenthCount, n, solution);
        }

        // Can only add close parenthesis
        else {
            stack.push(")");

            // Go to next move - recursive function
            nextMove(stack, openParenthCount, closeParenthCount + 1, n, solution);
        }
    }
}
