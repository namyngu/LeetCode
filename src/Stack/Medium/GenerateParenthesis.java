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

    List<String> ans = new ArrayList<>();

    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        List<String> ans = gp.generateParenthesis(10);
        for (String parenthesis : ans) {
            System.out.println(parenthesis);
        }
    }

    public GenerateParenthesis() {


    }

    // Strat: Think about the condition for possible moves - FYI DON'T HAVE TO USE STACK FOR THIS PROBLEM!!!
    // Two conditions:
    // 1. next move can be open parenthesis if openParenthesis < n
    // 2. next move can be close parenthesis if closeParenthesis < openParenthesis

    // Pseudocode
    // 1. If openParenthesis < n - add open parenthesis to stack and go to next move
    // 2. If closeParenthesis < add close parenthesis to stack and go to next move
    // 3. If condition 1 or 2 is not met end move and add stack to String list.

    //RESULT: 3ms runtime - beats 16.65% of users
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

    // Strat 2 - revisited
    // RESULT: 1ms - beats 82.71%!
    public List<String> generateParenthesis2(int n) {

        // Pseudocode:
        // If open bracket < n: can add open bracket
        // If close bracket < open bracket: can add close bracket.
        // stop iff open == close == n

        int openBracket = 0;
        int closeBracket = 0;
        String parentheses = "";
        nextMove2(parentheses, openBracket, closeBracket, n);

        return ans;
    }

    public void nextMove2(String parentheses, int openBracket, int closeBracket, int n) {

        if (openBracket == n && closeBracket == n) {
            // no more possible moves.
            ans.add(parentheses);
            return;
        }

        if (openBracket == closeBracket && openBracket < n ) {
            // next move has to be open bracket
            parentheses = parentheses + "(";
            openBracket++;
            nextMove2(parentheses, openBracket, closeBracket, n);
        }
        else if (openBracket == n && closeBracket < openBracket) {
            // next move has to be close bracket
            parentheses = parentheses + ")";
            closeBracket++;
            nextMove2(parentheses, openBracket, closeBracket, n);
        }
        else if(closeBracket < openBracket && openBracket < n) {
            // next move can be open or close bracket
            nextMove2(parentheses + "(", openBracket + 1, closeBracket, n);
            nextMove2(parentheses + ")", openBracket, closeBracket+ 1, n);
        }
    }
}
