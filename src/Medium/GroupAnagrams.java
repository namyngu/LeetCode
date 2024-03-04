package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GroupAnagrams {

    //Strategy 1 - Create a hashmap for each anagram group and compare new strings to the hashmap of group.
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<HashMap<Character, Integer>, List<String>> groupMap = new HashMap<>();



        for (int i = 0; i < strs.length; i++) {

        }




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
