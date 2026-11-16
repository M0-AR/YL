package Exercise_10._25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewStringSplitMethod {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(split("ab#12#453", "#")));
        System.out.println(Arrays.toString(split("a?b?gf#e", "[?#]")));
    }

    public static String[] split(String s, String regex) {
        Set<Character> delimiters = new HashSet<>();

        if (regex.length() >= 2
                && regex.charAt(0) == '['
                && regex.charAt(regex.length() - 1) == ']') {
            for (int i = 1; i < regex.length() - 1; i++) {
                delimiters.add(regex.charAt(i));
            }
        } else {
            for (int i = 0; i < regex.length(); i++) {
                delimiters.add(regex.charAt(i));
            }
        }

        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (delimiters.contains(ch)) {
                if (current.length() > 0) {
                    parts.add(current.toString());
                    current.setLength(0);
                }
                parts.add(String.valueOf(ch));
            } else {
                current.append(ch);
            }
        }

        if (current.length() > 0) {
            parts.add(current.toString());
        }

        return parts.toArray(new String[0]);
    }
}
