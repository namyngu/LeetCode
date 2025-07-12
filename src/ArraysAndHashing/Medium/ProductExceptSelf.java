package ArraysAndHashing.Medium;


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

    // My Solution to space complexity of O(1) revisited
//    Idea:
//    We first iterate through the nums array multiplying all the values at each index and inputting the product into
//    the ans[], such that the product is the product of all previous values EXCLUDING the value at the current index.
//    Similarly, we then iterate through the nums array again but in reverse and multiplying the values at each index excluding the value at the current index.
//    We then multiply that product with the current value at the ans[] array, remembering that the current value at the ans[] array is the product of all values (excluding the current one) in ascending order.
    public int[] productExceptSelf3(int[] nums) {

        int[] ans = new int[nums.length];

        // space complexity O(1)
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                ans[i] = 1;
                continue;
            }

            ans[i] = nums[i - 1] *ans[i - 1];
        }

        int tmp = 1;
        for (int j = nums.length - 2; j >= 0; j--) {

            ans[j] = ans[j] * tmp * nums[j + 1];
            tmp = tmp * nums[j + 1];
        }

        return ans;
    }
}

