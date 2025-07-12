package ArraysAndHashing.Medium;

//Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
//
//Please implement encode and decode
//
//Example 1:
//
//Input: ["neet","code","love","you"]
//
//Output:["neet","code","love","you"]
//Example 2:
//
//Input: ["we","say",":","yes"]
//
//Output: ["we","say",":","yes"]
//Constraints:
//
//        0 <= strs.length < 100
//        0 <= strs[i].length < 200
//strs[i] contains only UTF-8 characters.

import java.util.ArrayList;
import java.util.List;

//Watch this this vid for an explanation
//https://www.youtube.com/shorts/zwUjHW8Exyc
public class EncodeDecodeStr {

    public static void main(String[] args) {
        EncodeDecodeStr start = new EncodeDecodeStr();

        // Test data
        List<String> data = new ArrayList<>();
        data.add("testcase1");
        data.add("testcase2");
        data.add("#testcase1");
        data.add("tesadf3stcase1");
        data.add("tessd238912e1");
        data.add("12356261");
        data.add("1235236#2467234");

        List<String> data2 = new ArrayList<>();
        data2.add("we");
        data2.add("say");
        data2.add(":");
        data2.add("yes");
        data2.add("!@#$%^&*()");

        String tmp = start.encode(data2);
        start.decode(tmp);
    }

    // Encode string
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();


        for (String str : strs) {
            int len = str.length();
            sb.append(len + "#");
            sb.append(str);
        }
        return sb.toString();
    }

    // Decode string
    public List<String> decode(String str) {
        List<String> strList = new ArrayList<>();

        int i = 0;
        while (i < str.length()) {
            // check start of new string
            // NOTE: The length of a string could be more than 1 digit long.
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }

            int len = Integer.parseInt( str.substring(i, j));
            String tmp = str.substring(j + 1, j + 1 + len);
            strList.add(tmp);

            // check if this is last word
            if (j + len >= str.length()) {
                break;
            }

            i = j + 1 + len;
        }

        return strList;
    }
}
