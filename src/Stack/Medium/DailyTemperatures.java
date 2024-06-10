package Stack.Medium;

//Daily Temperatures
//
//        You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.
//
//        Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day. If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.
//
//        Example 1:
//
//        Input: temperatures = [30,38,30,36,35,40,28]
//
//        Output: [1,4,1,2,1,0,0]
//
//        Example 2:
//
//        Input: temperatures = [22,21,20]
//
//        Output: [0,0,0]
//
//        Constraints:
//
//        1 <= temperatures.length <= 1000.
//        1 <= temperatures[i] <= 100

import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {
       DailyTemperatures start = new DailyTemperatures();
       int[] input = {30,38,30,36,35,40,28};
       int[] ans = start.dailyTemperatures(input);

       for (int i = 0; i < ans.length; i++) {
           System.out.print(ans[i] + ", ");
       }
    }

//    We know:
//      • Starting at index i, when we go to the next step i+1 two things can happen:
//      ○ Temperature is colder temp[i+1] < temp[i]
//      ○ Temperature is warmer temp
//	    . If temp is warmer, that's easy we stop and calculate the number of steps taken (take the difference in index)
//      • If temp is colder, that means that we can kind of ignore the previous temp because if the coming temp is hotter than temp[i] then it will defnitely be hotter than temp[i+1]. So we now look at the first instance temp[i+1] gets warmer.
//      • This means we need a way to store both the index location and temperature at each index.
//    We can do this using two stacks.
    // RESULT: 112ms runtime (passes in neetcode) - beats 5.37% of users slow af
    public int[] dailyTemperatures(int[] temperatures) {

        // Edge case
        if (temperatures.length == 0) {
            return new int[]{0};
        }

        int[] solution = new int[temperatures.length];
        Stack<Integer> index = new Stack<>();
        Stack<Integer> temp = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {

            // if stack is empty
            if (temp.isEmpty()) {
                index.push(i);
                temp.push(temperatures[i]);
                continue;
            }

            // if next temp is colder add temp to both stack.
            if (temperatures[i] <= temp.peek()) {
                index.push(i);
                temp.push(temperatures[i]);
                continue;
            }

            // if next temp is warmer
            // Check all previous temps - remove them as you go
            // Add new temp into stack once checking is done.
            boolean pass = false;
            while (!pass) {
                // If stack is no empty
                if (temp.isEmpty()) {
                    index.push(i);
                    temp.push(temperatures[i]);
                    pass = true;
                }
                else if (temperatures[i] > temp.peek()) {
                    solution[index.peek()] = i - index.peek();     // add data to solution array
                    index.pop();        // remove element from stack
                    temp.pop();         // remove element from stack
                }
                else {
                    index.push(i);
                    temp.push(temperatures[i]);
                    pass = true;
                }
            }
        }

        // Any temps remaining in the stack means there's no warmer days - set all values to zero
        // DONT NEED TO DO THIS - DEFAULT VALUE IS ALREADY 0
//        while (!temp.isEmpty()) {
//            solution[index.peek()] = 0;
//            index.pop();
//            temp.pop();
//        }

        return solution;
    }

    //TODO: Possible optimisations - reduce the number of stacks by getting rid of the temp stack and comparing the temp from the temperatures array (we know index)
}
