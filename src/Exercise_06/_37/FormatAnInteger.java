package Exercise_06._37;

import java.util.Scanner;

/*
(Format an integer) Write a method with the following header to format the integer
with the specified width.
public static String format(int number, int width)
The method returns a string for the number with one or more prefix 0s. The size
of the string is the width. For example, format(34, 4) returns 0034 and format(
34, 5) returns 00034. If the number is longer than the width, the method
returns the string representation for the number. For example, format(34, 1)
returns 34.
Write a test program that prompts the user to enter a number and its width and
displays a string returned by invoking format(number, width).
*/
public class FormatAnInteger {
    public static void main(String[] args) { // github
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter a number and its width
        System.out.print("Enter a number and its width: ");
        int number = input.nextInt();
        int width = input.nextInt();

        // Display a string of formatted number
        System.out.println(
                number + " formatted to width " + width + ": " + format(number, width));
    }

    /** Method format returns a string for the number with one or more prefix 0s*/
    public static String format(int number, int width){
        String s = number+"";

        // If the width is less or equal to
        // the length of number so we return the same number
        if (width <= s.length())
            return s;

        int n =  width - s.length();
        for (int i = 0; i < n; i++) {
            s = "0" + s;
        }

        return s;
    }
}
