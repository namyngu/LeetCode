package TwoPointers.Hard;

//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//
//
//        Example 1:
//
//        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//        Output: 6
//        Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//
//        Example 2:
//
//        Input: height = [4,2,0,3,2,5]
//        Output: 9
//
//
//
//        Constraints:
//
//        n == height.length
//        1 <= n <= 2 * 104
//        0 <= height[i] <= 105

public class TrappingRainWater {

    // Strat 1
    // LOGIC: We will sum up the water one row (height) at a time.
    // 1. First find the first occurring elevation from left and right
    // 2. Then assume all spaces in between the elevation are water
    // 3. Then decrease amount of water by 1 for every elevation in between the two starting elevations.
    // 4. Repeat until all rows (heights) are accounted for - and sum up the number of water.

    // Time complexity: O(n * height)
    // RESULT: Time limit exceeded

    public int trap(int[] height) {
        int water = 0;
        boolean cont = false;       // stop when there's no more elevation.

        do {
            cont = false;
            int leftPtr = 0;
            int rightPtr = height.length - 1;

            boolean pass1 = false;
            boolean pass2 = false;

            // Find the first elevation from left and right if it exists
            while (leftPtr < rightPtr) {
                if (height[leftPtr] <= 0) {
                    leftPtr++;
                }
                else {
                    pass1 = true;
                }

                if (height[rightPtr] <= 0) {
                    rightPtr--;
                }
                else {
                    pass2 = true;
                }

                // Both elevations found
                if (pass1 && pass2) {
                    cont = true;

                    // 2. Assume all spaces in-between elevations are water
                    water = water + rightPtr - leftPtr - 1;

                    // 3. Decrease water amount by 1 for every elevation in between the first two elevations.
                    while (leftPtr < rightPtr) {
                        if (height[leftPtr] > 0) {
                            water--;
                        }
                        leftPtr++;
                    }
                    break;
                }
            }

            // 4. Decrease all height by 1 (move to next height)
            for (int i = 0; i < height.length; i++) {
                height[i]--;
            }

        } while (cont);

        return water;
    }

    // Strat 2
    // Same as Strat 1 except every new height we search for the first and last elevation
    // starting from where we left off from the previous elevation

    // RESULT: Time limit exceeded - STILL TOO SLOW
    public int trap2(int[] height) {
        int water = 0;
        boolean cont = false;       // stop when there's no more elevation.

        int startElevation = 0;
        int lastElevation = height.length - 1;

        do {
            cont = false;
            int leftPtr = startElevation;
            int rightPtr = lastElevation;

            boolean pass1 = false;
            boolean pass2 = false;

            // Find the first elevation from left and right if it exists
            while (leftPtr < rightPtr) {
                if (height[leftPtr] <= 0) {
                    leftPtr++;
                }
                else {
                    pass1 = true;
                }

                if (height[rightPtr] <= 0) {
                    rightPtr--;
                }
                else {
                    pass2 = true;
                }

                // Both elevations found
                if (pass1 && pass2) {
                    cont = true;
                    startElevation = leftPtr;
                    lastElevation = rightPtr;

                    // 2. Assume all spaces in-between elevations are water
                    water = water + rightPtr - leftPtr - 1;

                    // 3. Decrease water amount by 1 for every elevation in between the first two elevations.
                    leftPtr++;
                    while (leftPtr < rightPtr) {
                        if (height[leftPtr] > 0) {
                            water--;
                        }
                        leftPtr++;
                    }
                    break;
                }
            }

            // 4. Decrease all height by 1 (move to next height)
            for (int i = 0; i < height.length; i++) {
                height[i]--;
            }

        } while (cont);

        return water;
    }

    //Strat 3
    // LOGIC: calculate amount of water possible to be trapped at each height[i]
    // Do this by looking at the max elevation on the left and right side - then take its minimum value.
    // Using formula min(L,R) - h[i] - watch neetcode vid for explanation: https://youtu.be/ZI2z5pq0TqA
    // Time Complexity: O(n)
    // Space Complexity: O(1) (when optimised - by using 2 ptr)
    // RESULT: 1ms
    public int trap3(int[] height) {

        // edge case
        if (height.length == 0) {
            return 0;
        }

        int waterSum = 0;
        int lMax = height[0];
        int rMax = height[height.length - 1];
        int leftPtr = 0;
        int rightPtr = height.length - 1;

        while (leftPtr < rightPtr) {

            if (lMax <= rMax) {
                int water = Math.min(lMax, rMax) - height[leftPtr];
                if (water > 0) {
                    waterSum += water;
                }

                lMax = Math.max(height[leftPtr], lMax);
                leftPtr++;
            }
            else {
                int water = Math.min(lMax, rMax) - height[rightPtr];
                if (water > 0) {
                    waterSum += water;
                }

                rMax = Math.max(height[rightPtr], rMax);
                rightPtr--;
            }
        }

        // Last calculation
        if (leftPtr == rightPtr) {
            int water = Math.min(lMax, rMax) - height[rightPtr];
            if (water > 0) {
                waterSum += water;
            }
        }
        return waterSum;
    }

    // Strat 4 (revisisted without looking at previous solutions)
    // Nearly same as strat 3 but

    // LOGIC: Calculate amount of water trapped at each height[i]
    // by figuring out the max height on the left and right of current index.
    // Do this by creating two arrays of length n that tells you the max height of left and right at each point in index.

    // Space Complexity: O(n) not as optimized as strat 3
    // Time Complexity: 0(n)
    // RESULT: 1ms
    public int trap4(int[] height) {
        int len = height.length;

        int[] lMax = new int[len];  // array of maximum height on the left
        int[] rMax = new int[len];  // array of max height on the right
        int maxLHeight = 0;
        int maxRHeight = 0;

        int ans = 0;

        lMax[0] = 0;
        rMax[len - 1] = 0;

        for (int i = 1, j = len - 2 ; i < len - 1; i++, j--) {

            maxLHeight = Math.max(height[i - 1], maxLHeight);
            maxRHeight = Math.max(height[j + 1], maxRHeight);

            lMax[i] = maxLHeight;
            rMax[j] = maxRHeight;
        }

        for (int i = 0; i < len; i++) {

            int water = Math.max(
                    Math.min(lMax[i], rMax[i]) - height[i], 0   // water trapped at this index (can't be less than 0)
            );

            ans += water;
        }

        return ans;
    }


    public static void main(String[] args) {
        TrappingRainWater start = new TrappingRainWater();
        int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.print(start.trap3(input));
    }
}
