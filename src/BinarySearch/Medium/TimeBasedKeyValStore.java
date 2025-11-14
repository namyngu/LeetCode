package BinarySearch.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//981. Time Based Key-Value Store
//Solved
//        Medium
//Topics
//premium lock icon
//        Companies
//Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
//
//Implement the TimeMap class:
//
//TimeMap() Initializes the object of the data structure.
//void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
//String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
//
//
//Example 1:
//
//Input
//["TimeMap", "set", "get", "get", "set", "get", "get"]
//        [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
//Output
//[null, null, "bar", "bar", null, "bar2", "bar2"]
//
//Explanation
//TimeMap timeMap = new TimeMap();
//timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
//timeMap.get("foo", 1);         // return "bar"
//timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
//timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
//timeMap.get("foo", 4);         // return "bar2"
//timeMap.get("foo", 5);         // return "bar2"
//
//
//Constraints:
//
//        1 <= key.length, value.length <= 100
//key and value consist of lowercase English letters and digits.
//        1 <= timestamp <= 107
//All the timestamps timestamp of set are strictly increasing.
//At most 2 * 105 calls will be made to set and get.


// Time Complexity:
// RESULT: 137ms Beats 64.7%
// Space Complexity:
// RESULT: 108.33MB beats 90.97%!

public class TimeBasedKeyValStore {
    protected TimeMap timeMap;

    public static void main(String[] args) {
        TimeBasedKeyValStore start = new TimeBasedKeyValStore();
        start.inputTestCase();
    }

    public TimeBasedKeyValStore() {
        this.timeMap = new TimeMap();
    }




    // ["TimeMap","set","get","get","set","get","get"]
    // [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    public void inputTestCase() {
        timeMap.set("foo","bar",1);
        System.out.println(timeMap.get("foo",1));
        System.out.println(timeMap.get("foo",3));
        timeMap.set("foo","bar2",4);
        System.out.println(timeMap.get("foo",4));
        System.out.println(timeMap.get("foo",5));

    }

    // Data structure to store records
    public static class Record {
        protected int timestamp;
        protected String val;

        public Record() {}

        public Record(int timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }
    }

    public static class TimeMap {

        Map<String, List<Record>> timeMap;

        public TimeMap() {
            this.timeMap = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {

            if (timeMap.containsKey(key)) {
                // key already exists
                List<Record> records = timeMap.get(key);
                Record record = new Record(timestamp, value);
                records.add(record);
            }
            else {
                // key doesn't exist
                Record record = new Record(timestamp, value);
                List<Record> records = new ArrayList<>();
                records.add(record);

                timeMap.put(key, records);
            }
        }

        public String get(String key, int timestamp) {

            if (timeMap.get(key) == null) {
                return "";
            }

            List<Record> records = timeMap.get(key);


            int len = records.size();
            if (len == 0) {
                return "";
            }

            // Do a binary search of records to check for timestamp.
            // If non found then grab the previous timestamp (left ptr)
            int left = 0;
            int right = len - 1;
            while (left < right) {
                int middle = (int) Math.ceil((right - left) / 2.0) + left;      // has to be ceiling!

                if (records.get(middle).timestamp < timestamp) {
                    left = middle;              // note how it's not middle + 1.
                }
                else if (records.get(middle).timestamp > timestamp) {
                    right = middle - 1;
                }
                else {
                    return records.get(middle).val;
                }
            }
            // no timestamp found, return prev_timestamp
            if (records.get(left).timestamp > timestamp) {
                return "";
            }
            else {
                return records.get(left).val;
            }

        }
    }
}



