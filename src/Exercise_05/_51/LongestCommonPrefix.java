package Exercise_05._51;
/*
(Longest common prefix) Write a program that prompts the user to enter two
strings and displays the largest common prefix of the two strings.
*/
import java.util.Scanner;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter two string
        System.out.println( "Enter the first string: " );
        String s1 = input.nextLine();


        System.out.println( "Enter the second string: " );
        String s2 = input.nextLine();


        // Method one
        String [] s11 = s1.split( " " );
        String [] s22 = s2.split( " " );

        String commonPrefix = "";
        int n = Math.min( s11.length, s22.length );
        for (int i = 0; i < n ; i++) {
            if (n == s11.length){
                if (s2.contains( s11[i] ))
                    commonPrefix +=  s11[i] + " ";

            }else
                if (s1.contains( s22[i] ))
                    commonPrefix += s22[i];
        }

        // Display result
        System.out.println(((commonPrefix.length() != 0)?"The common prefix is  " + commonPrefix: s1 + " and " + s2 + " have no common prefix"));



    }
}
