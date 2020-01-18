package Exercise_04._22;
/*
(Check substring) Write a program that prompts the user to enter two strings and
reports whether the second string is a substring of the first string.
*/
import java.util.Scanner;

public class CheckSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter string1 and string2
        System.out.print("Enter string s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter string s2: ");
        String s2 = scanner.nextLine();

        // Display result1
        if (s1.indexOf( s2 ) != -1){
            System.out.println(s2 + " is a substring of " + s1);
        }else {
            System.out.println(s2 + " is not a substring of " + s1);
        }

        // Display result2
        System.out.println(s2 + ((s1.contains( s2 )) ? " is ": " is not ")+ " a substring of " + s1);



    }
}
