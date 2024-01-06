import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {

    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";

        boolean result = areAnagrams(str1, str2);
        System.out.println("Are \"" + str1 + "\" and \"" + str2 + "\" anagrams? " + result);
    }

    public static boolean areAnagrams(String str1, String str2) {
        // Remove any white space and convert strings to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Check if lengths are equal
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] charCounts = new int[26]; // Assuming English letters

        // Count characters from the first string
        for (int i = 0; i < str1.length(); i++) {
            charCounts[str1.charAt(i) - 'a']++;
        }

        // Subtract counts using the second string
        for (int i = 0; i < str2.length(); i++) {
            if (--charCounts[str2.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean areAnagrams1(String str1, String str2) {
        // Remove any white space and convert strings to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Check if lengths are equal
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] charCounts = new int[26]; // Assuming English letters

        // Count characters from the first string
        for (int i = 0; i < str1.length(); i++) {
            charCounts[str1.charAt(i) - 'a']++;
        }

        // Subtract counts using the second string
        for (int i = 0; i < str2.length(); i++) {
            if (--charCounts[str2.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
    public static boolean areAnagrams2(String str1, String str2) {
        // Remove any white space and convert strings to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Check if lengths are equal
        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> charCounts = new HashMap<>();

        // Count characters from the first string
        for (char c : str1.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        // Subtract counts using the second string
        for (char c : str2.toCharArray()) {
            int count = charCounts.getOrDefault(c, 0);
            if (count == 0) {
                return false; // More of this char in str2 or char not present in str1
            }
            charCounts.put(c, count - 1);
        }

        return true;
    }
}
