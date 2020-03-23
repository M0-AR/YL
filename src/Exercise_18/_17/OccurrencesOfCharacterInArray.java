package Exercise_18._17;

import java.util.Scanner;

public class OccurrencesOfCharacterInArray { //github
    public static void main(String[] args) {
        System.out.print( "Enter a list of characters in one line: " );
        Scanner input = new Scanner( System.in );
        String s = input.nextLine();
        char[] items = s.toCharArray();
        System.out.print( "Enter a character: " );
        char ch = input.nextLine().charAt( 0 );
        System.out.println( "The number of occurrences of the character in the list: " +
                count( items, ch ) );
    }

    public static int count(char[] chars, char ch) {
        return count( chars, ch, chars.length - 1 );
    }

    public static int count(char[] chars, char ch, int high) {
        if (high < 0)
            return 0;
        return count( chars, ch, high - 1 ) +
                         ((chars[high] == ch) ? 1 : 0);
    }
}
