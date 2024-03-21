package ArraysAndHashing.Medium;

import java.util.*;

// Strat 2 is the answer!!!!
//Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//
//        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
public class GroupAnagrams {

    //Strategy 1 - Count the number of occurrences of each character in a string, do this for all strings in array.
    //Keep the counted number of char occurrences in an array and use that as a key in a hashmap
    //Array has to be size 26 since there are 26 chars in the alphabet.

    //Time complexity - O(m*n*26) where m is strs array length, n is average char length of each string and 26 is number of chars in the alphabet.
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<int[], List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            int[] countArray = new int[26];
            String str = strs[i];

            // loop through each char in string and count char occurrences in countArray
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                int index = ch - 'a';

                // countArray[0] is a ... countArray[25] is z
                countArray[index]++;
            }

            // if anagram group exist add string to list, else make new anagram group
            // NOTE: need to override hashdcode and equals since key is not primitive type!!!!
            // Seems to complex to do this for a primitive array - actually easier to do for an object
            if (map.containsKey(countArray)) {
                List<String> anagramList = map.get(countArray);
                anagramList.add(str);
            }
            else {
                List<String> anagramList = new ArrayList<>();
                anagramList.add(str);
                map.put(countArray, anagramList);
            }
        }

        return new ArrayList<>(map.values());
    }



    // Strategy 2 - We can group the anagrams by using the sorted string (signature) as a key in a hashmap
    //RESULT: 6ms very fast!
    public List<List<String>> groupAnagrams2(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            char[] word = strs[i].toCharArray();
            Arrays.sort(word);
            String signature = String.valueOf(word);

            // if anagram does not exist create new anagramGroup and add to hashmap.
            // else add string to anagramGroup
            if (!map.containsKey(signature)) {
                List<String> anagramGroup = new ArrayList<>();
                anagramGroup.add(strs[i]);

                map.put(signature, anagramGroup);
            }
            else {
                map.get(signature).add(strs[i]);
            }
        }

        // create an arrayList of all values in the hashmap then return it.
        return new ArrayList<>(map.values());
    }

    // Strat 3 - brute force :(
    public List<List<String>> groupAnagrams3(String[] strs) {

        List<List<String>> solution = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            boolean anagramExist = false;

            for (List<String> strList : solution) {
                String tmp = strList.get(0);
                if (isAnagram(tmp, strs[i])) {
                    strList.add(strs[i]);
                    anagramExist = true;
                }
            }

            if (!anagramExist) {
                List<String> newAnagram = new ArrayList<>();
                newAnagram.add(strs[i]);
                solution.add(newAnagram);
            }
        }
        return solution;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!sMap.containsKey(ch)) {
                sMap.put(ch, 1);
            }
            else {
                sMap.put(ch, sMap.get(ch) + 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (!tMap.containsKey(ch)) {
                tMap.put(ch, 1);
            }
            else {
                tMap.put(ch, tMap.get(ch) + 1);
            }
        }

        for (char key : sMap.keySet()) {
            if (!tMap.containsKey(key)) {
                return false;
            }
            if (!Objects.equals(sMap.get(key), tMap.get(key))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram(String s, HashMap<Character, Integer> groupMap) {
        HashMap<Character, Integer> sMap = new HashMap<>();

        // Fill in Hashmap for s
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!sMap.containsKey(ch)) {
                sMap.put(ch, 1);
            }
            else {
                sMap.put(ch, sMap.get(ch) + 1);
            }
        }

        for (char key : sMap.keySet()) {
            if (!groupMap.containsKey(key)) {
                return false;
            }
            if (!Objects.equals(sMap.get(key), groupMap.get(key))) {
                return false;
            }
        }
        return true;
    }

}
