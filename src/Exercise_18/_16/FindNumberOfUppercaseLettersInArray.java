package Exercise_18._16;

import java.util.Scanner;

public class FindNumberOfUppercaseLettersInArray {

    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] items = s.toCharArray();
        System.out.println("The number of uppercase letters is " +
                count(items));
    }

    public static int count(char[] chars) {
        return count( chars, chars.length - 1 );
    }

    public static int count(char[] chars, int high) {
        // Base case
        if (high < 0) {
            return 0;
        }
        return count( chars, high - 1 ) + ((Character.isUpperCase( chars[high] )) ? 1 : 0);
    }
}
