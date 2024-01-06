package algorithms;
import java.util.HashMap;
import java.util.Map;

public class LargestNonRepeatingSubstring {
    
    /**
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
    

        for (int start = 0, end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (map.containsKey(currentChar)) {
                start = Math.max(map.get(currentChar) + 1, start);
            }

            map.put(currentChar, end);
            maxLength = Math.max(maxLength, end - start + 1);
            System.out.println("Iteration" + end+ "\t" +"Start is"+start+ "\t" + "End is " +end +"\t" + "Max Length"+ maxLength);
        
            
        }

        

        return maxLength;
    }

    public static void main(String[] args) {
        String exampleString = "abcabcbb";
        System.out.println("Length of the longest substring: " + lengthOfLongestSubstring(exampleString));
    }
}
