package Medium;

//Given an integer array nums and an integer k, return the k most frequent elements.
// You may return the answer in any order.
//Constraints:
//
//    1 <= nums.length <= 105
//    -104 <= nums[i] <= 104
//    k is in the range [1, the number of unique elements in the array].
//    It is guaranteed that the answer is unique.
//
//    Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size


import java.util.*;

public class TopKFrequent {

    public static void main(String[] args) {
        TopKFrequent start = new TopKFrequent();
        int[] solution = start.topkFrequent2(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);

        System.out.println(start.toString(solution));

    }

    //Strat 1 - loop thru array and count integer occurrences - map it to a hashmap.
    // Keep track of the top k (e.g. top 5) occurrences.
    // Result: SUPER SLOW 330+ ms runtime
    public int[] topkFrequent(int[] nums, int k) {

        // Integer, occurrences
        HashMap<Integer, Integer> map = new HashMap<>();

        // HashMap of most frequent integers
        HashMap<Integer, Integer> mostFrequent = new HashMap<>();

        // Loop through array and count integer occurrences - map it to hashmap.
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            if (map.containsKey(currentNum)) {
                map.put(currentNum, map.get(currentNum) + 1);

            }
            else {
                map.put(currentNum, 1);
            }

            // Check if num is frequent
            int frequency = map.get(currentNum);

            // If number is already most frequent
            // OR if there's not enough frequent integers
            if (mostFrequent.containsKey(currentNum) || mostFrequent.size() < k) {
                mostFrequent.put(currentNum, frequency);
            }

            else {
                // If currentFreq is > than current entry, then
                // We know that the current entry is the least frequent or equivalent to the least frequent
                // since currentNum can only increment by one.
                // We ignore currentNum if it's equivalent to the current entry - since mostFrequent array is full

                //Create an iterator - because removing items from a HashMap while iterating through it is a problem
                Iterator<Map.Entry<Integer, Integer>> iterator = mostFrequent.entrySet().iterator();
                boolean isFrequent = false;
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    if (frequency > entry.getValue()) {
                        isFrequent = true;
                        iterator.remove();
                        break;
                    }
                }

                if (isFrequent == true) {
                    mostFrequent.put(currentNum, frequency);
                }
            }
        }


        int[] solution = new int[k];
        int i = 0;
        for (int key : mostFrequent.keySet()) {
            solution[i] = key;
            i++;
        }
        return solution;
    }

    // Strat 2
    //1. Loop through integer array and map integer occurrences onto a hashmap
    //2. convert hashmap to arraylist
    //3. sort arraylist in order using comparator.
    // RESULT: 17ms - beats 21.86% not bad

    public int[] topkFrequent2(int[] nums, int k) {
        // Integer, occurrences
        HashMap<Integer, Integer> map = new HashMap<>();

        // Loop through array and count integer occurrences - map it to hashmap.
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            if (map.containsKey(currentNum)) {
                map.put(currentNum, map.get(currentNum) + 1);

            } else {
                map.put(currentNum, 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        ArrayList<Map.Entry<Integer, Integer>> listOfNums = new ArrayList<>(entrySet);
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<>() {
            // the Comparator will swap if compare() returns > 1, else it won't swap.
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                }
                else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
        };
        Collections.sort(listOfNums, comparator);

        int[] solution = new int[k];
        for (int i = 0; i < k; i++) {
            solution[i] = listOfNums.get(i).getKey();
        }

        return solution;
    }




    public String toString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }

        return sb.toString();
    }
}
