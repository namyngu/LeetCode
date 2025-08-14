package Stack.Hard;

//Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
//
//
//
//Example 1:
//
//
//Input: heights = [2,1,5,6,2,3]
//Output: 10
//Explanation: The above is a histogram where width of each bar is 1.
//The largest rectangle is shown in the red area, which has an area = 10 units.
//        Example 2:
//
//
//Input: heights = [2,4]
//Output: 4
//
//
//Constraints:
//
//        1 <= heights.length <= 105
//        0 <= heights[i] <= 104

import java.util.Stack;

public class LargestRectHistogram {

    public static void main(String[] args) {
        LargestRectHistogram start = new LargestRectHistogram();
        int[] input = {
                12,11,10,9,8,7,6,5,4,3,2,1    // expected 42
        };
        System.out.println(
            start.largestRectangleArea(input)
        );
    }

    // Strat 1:
    // Idea 1:
    // 1. Iterate through array and push the height[i] into the stack if the currentHeight is > prevHeight.
    // 2. When currentHeight < prevHeight, we stop and pop the stack and calculate the rectangle area of the previous histogram in the stack.
    // 3. We repeat this and keep popping the stack until we encounter a histogram whose height is < the current height.
    //
    // 4. We also calculate the known partial area of the current histogram by multiplying its height by the number of histogram's we've popped from the stack (length).
    // Time Complexity: O(2n) => O(n)
    // DOESN'T WORK - passed 94/99 test cases

    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stack = new Stack<>();     // height, index, area
        int len = heights.length;
        int maxArea = 0;

        int[] histogram = {heights[0], 0, 0,};
        stack.push(histogram);

        for (int i = 1; i < len; i++) {
            int[] prevHistogram = stack.peek();
            int currHeight = heights[i];
            int[] currHistogram = {currHeight, i, 0};

            if (currHeight > prevHistogram[0]) {
                stack.push(new int[] {currHeight, i, 0});
                continue;
            }
            else if (currHeight < prevHistogram[0]) {
                int area = prevHistogram[0] * (i - prevHistogram[1]) + prevHistogram[2];
                maxArea = Integer.max(area, maxArea);

                // calculate partial area
                currHistogram[2] = currHistogram[2] + currHeight;

                stack.pop();

                while (!stack.isEmpty()) {
                    int[] tmpHistogram = stack.peek();

                    if (currHeight >  tmpHistogram[0]) {
                        currHistogram[2] = currHeight * (i - tmpHistogram[1] - 1);
                        break;
                    }
                    else if (currHeight < tmpHistogram[0]) {
                        int tmpArea = tmpHistogram[0] * (i - tmpHistogram[1]) + tmpHistogram[2];
                        maxArea = Integer.max(maxArea, tmpArea);
                        stack.pop();

                        // Calculate partial area of current histogram
                        currHistogram[2] = currHistogram[2] + currHeight;   // height * length but length is 1.
                    }
                    else {
                        currHistogram[2] = currHistogram[2] + currHeight + tmpHistogram[2];
                        stack.pop();
                    }
                }


            }
            stack.push(currHistogram);
        }

        // pop all the histogram in the stack and calculate its area
        while (!stack.isEmpty()) {
            int[] tmpHistogram = stack.pop();
            int area = tmpHistogram[0] * (len - tmpHistogram[1]) + tmpHistogram[2];
            maxArea = Integer.max(area, maxArea);
        }

        return maxArea;
    }
}
