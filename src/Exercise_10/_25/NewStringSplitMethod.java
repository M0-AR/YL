package Exercise_10._25;

import java.util.Arrays;

public class NewStringSplitMethod {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(split("ab#12#453", "#")));
        System.out.println(Arrays.toString(split("a?b?gf#e", "[?#]")));
    }

    public static String[] split(String s, String regex) {
        String[] result = new String[s.length()];

        char[] ch = new char[regex.length()];
        for (int i = 0, j = 0; i < regex.length(); i++) {
            if (regex.charAt(i) == '[' || regex.charAt(i) == ']') continue;
            ch[j++] = regex.charAt(i);
        }



        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; j < s.length(); j++) {
                result[i] += s.charAt(j);
            }
        }

        return null;
    }
}
