package BinarySearch.Medium;
//
//Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
//
//Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
//
//Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
//
//Return the minimum integer k such that she can eat all the bananas within h hours.
//
//
//
//Example 1:
//
//Input: piles = [3,6,7,11], h = 8
//Output: 4
//Example 2:
//
//Input: piles = [30,11,23,4,20], h = 5
//Output: 30
//Example 3:
//
//Input: piles = [30,11,23,4,20], h = 6
//Output: 23
//
//
//Constraints:
//
//		1 <= piles.length <= 104
//piles.length <= h <= 109
//		1 <= piles[i] <= 109

import java.util.Arrays;

public class KokoEatingBananas {

	public static void main(String[] args){
		KokoEatingBananas start = new KokoEatingBananas();
		int[] piles = {30,11,23,4,20};
		int h = 6;

		System.out.println(start.minEatingSpeed2(piles, h));
	}


	// Idea 1:
	// 1. Sort the piles array
	// 2. Since k cannot be larger than the largest pile (makes no difference in time saved) then k <= piles[length - 1]
	// 3. Assign k to be the value of the middle pile[].
	// 4. Calculate how long it takes to eat all bananas, note all piles on left of k will take 1hr.
	// 5. If Koko can eat bananas decrease k -> pick middle value and vice versa if koko can't eat bananas increase k.
	//TODO:
	public int minEatingSpeed(int[] piles, int h) {
		Arrays.sort(piles);
		int left = 0;
		int right = piles.length - 1;


		int eatTime = 0;

		while (left <= right || eatTime == h) {
			int middle = (int) Math.floor((right - left) / 2.0) + left;
			int k = piles[middle];
			// Calculate eat time.
			eatTime = middle + 1;		// all piles on left of middle (including middle) takes 1hr to eat.

			for (int i = middle + 1; i < piles.length; i++) {
				eatTime += (int) Math.ceil(piles[i] / k);
			}

			if (eatTime < h) {
				right = middle - 1;
			}
			else if (eatTime > h) {
				left = middle + 1;
			}
			else {
				return k;
			}


		}

		return 0;
	}

	// Idea 2: If we have the upper bound for k which is the largest pile
	// And lower bound is 1 (could be possible to optimize this)
	// Then use binary search to find k.
	//RESULT: 25ms
	// Time complexity O(nlogn + n + nlogn) = O(nlogn)
	public int minEatingSpeed2(int[] piles, int h) {
		int len = piles.length;

		// upper bound for k is the largest pile
		// lower bound for k is (IGNORE THIS):
		// ceil(smallest pile / k) <= ceil(h/n)
		// k <= ((smallest pile) * n) / h. Doesn't quite work since ceil    isn't taking into account
		int upperBound = 0;

		for (int i = 0; i < len; i++) {
			upperBound = Math.max(upperBound, piles[i]);
		}


		int lowerBound = 1;

		return binarySearch(piles, lowerBound, upperBound, h);

	}

	// search for k. Middle is potentially k
	public int binarySearch(int[] piles, int left, int right, int h) {
		int k = 0;


		while (left <= right) {
			int middle = (int) Math.floor((right - left) / 2.0) + left;

			if (computeEatTime(piles, middle) < h) {
				k = middle;
				right = middle - 1;
			}
			else if (computeEatTime(piles, middle) > h) {
				left = middle + 1;
			}
			else {
				k = middle;
				right = middle - 1;
			}
		}

		return k;
	}

	// compute eat time.
	public long computeEatTime(int[] piles, int k) {
		long t = 0;

		for (int i = 0; i < piles.length; i++) {
			t += (long) Math.ceil(piles[i] / ((double) k));
		}

		return t;
	}


}
