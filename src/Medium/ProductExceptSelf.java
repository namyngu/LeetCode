package Medium;


//Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
//
//        The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
//        You must write an algorithm that runs in O(n) time and without using the division operation.


import java.util.Arrays;

public class ProductExceptSelf {

    public static void main(String[] args) {
        ProductExceptSelf start = new ProductExceptSelf();
        start.productExceptSelf2(new int[] {1,2,3,4});
    }



    // Strat 1 - Find the prefix product array and suffix product array. Then multiply them together.
    // Prefix Product array - the product of all numbers before the current number.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        // Prefix Product Array - [1,1,2,6]
        int[] prefix = new int[length];
        prefix[0] = 1;
        for (int i = 1; i < length; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        // Suffic Product Array - [24,12,4,1]
        int[] suffix = new int[length];
        suffix[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        int[] solution = new int[length];
        for (int i = 0; i < length; i++) {
            solution[i] = prefix[i] * suffix[i];
        }

        return solution;
    }

    // Strat 2 - neetcode solution
    // Directly store the product of prefix and suffix into the final answer array
    //
    // The logic is, we don't actually need seperate array to store prefix product and suffix products, we can do all the approach discussed in method 3 directly onto our final answer array.
    //
    // The Time Complexity would be O(n) but now, the Auxilary Space is O(1) (excluding the final answer array).

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        Arrays.fill(ans, 1);
        int curr = 1;
        for(int i = 0; i < n; i++) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for(int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        return ans;
    }
}
