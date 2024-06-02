package Stack.Medium;

import java.util.Stack;

//Design a stack class that supports the push, pop, top, and getMin operations.
//
//        MinStack() initializes the stack object.
//        void push(int val) pushes the element val onto the stack.
//        void pop() removes the element on the top of the stack.
//        int top() gets the top element of the stack.
//        int getMin() retrieves the minimum element in the stack.
//
//        Each function should run in O(1) time.


/* Strat 1:
    1. The hardest part is to implement getMin(), especially after the pop() method since that could remove the minimum element.
    2. The strategy is to create a second stack that keeps track of the minimum value
    3. Another key is to use this second track to keep track of the minimum value at EACH position of the original stack.
 */
public class MinStack {
    private int min;
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        if (minStack.isEmpty()) {

            minStack.push(val);
        }
        else if (val < minStack.peek()) {
            minStack.add(val);
        }
        else {
            minStack.add(minStack.peek());
        }

        stack.push(val);
    }

    // removes the element on the top of the stack.
    public void pop() {
        stack.pop();
        minStack.pop();
    }

    // gets the top element of the stack.
    public int top() {
        return stack.peek();
    }


    public int getMin() {
        return minStack.peek();
    }

    // TODO: Implement the stack without using the built-in stack function (use linkedlist or arraylist)
}
