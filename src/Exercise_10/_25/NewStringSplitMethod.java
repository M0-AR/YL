package Exercise_10._25;

import java.util.Arrays;
// TODO: not done yet.
public class NewStringSplitMethod {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(split("ab#12#453", "#")));   // ["ab", "#", "12", "#", "453"]
        System.out.println(Arrays.toString(split("a?b?gf#e", "[?#]"))); // ["a", "?", "b", "?", "gf", "#", "e"]
    }

    public static String[] split(String s, String regex) {
        String[] result = new String[s.length()];
        Arrays.fill(result, "");

        char[] ch = new char[regex.length()];
        for (int i = 0, j = 0; i < regex.length(); i++) {
            if (regex.charAt(i) == '[' || regex.charAt(i) == ']') continue;
            ch[j++] = regex.charAt(i);
        }

        if (regex.length() == 1) {
            ch[0] = regex.charAt(0);
        }

        int k = 0;
        boolean breakAllLoops = false;
        for (int i = 0; i < s.length(); i++) { // Iterate 'i' throw regex characters.
            for (int j = 0; j < s.length(); j++) {
                while (!breakAllLoops) {
                    if (s.charAt(k) == ch[i]) {
                        result[++j] = s.charAt(k++) + "";
                        j++;
                        break;
                    }
                    result[j] += s.charAt(k++);
                    if (k == s.length()) breakAllLoops = true;
                }
                if (breakAllLoops) break;
            }
            if (breakAllLoops) break;
        }

        return null;
    }
}
