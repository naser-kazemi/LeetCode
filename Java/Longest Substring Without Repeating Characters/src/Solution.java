import java.util.HashMap;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> freqSet = new HashMap<>();
        int start = 0, end = 0, size = s.length();
        int length = 0;
        int maxLength = 0;
        char ch;
        int index;
        while (start < size && end < size) {
            ch = s.charAt(end);
            if (freqSet.containsKey(ch)) {
                index = freqSet.get(ch);
                if (index >= start) {
                    start = index + 1;
                    length = end - start;
                }
            }
            freqSet.put(ch, end);
            length++;
            end++;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

}
