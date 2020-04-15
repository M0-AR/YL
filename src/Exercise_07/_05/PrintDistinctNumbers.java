package Exercise_07._05;

import java.util.Scanner;

public class PrintDistinctNumbers {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create an array
        int[] distinct = new int[10];

        // Prompt the user to enter 10 numbers
        System.out.print( "Enter 10 numbers: " );
        for (int i = 0; i < distinct.length; i++)
            distinct[i] = input.nextInt();

        distinctNumbers( distinct );
    }


    static void distinctNumbers(int[] distinct) {
        String s = "";

        for (int i = 0; i < distinct.length ; i++)
            if(!s.contains( distinct[i] + "" ))
                s += distinct[i] + " ";

        String[] ch = s.split( " " );

        // Display result
        System.out.println("The number of distinct numbers is " + ch.length);
        System.out.print("The distinct numbers are: " );
        for (String ch1 : ch) {
            System.out.print( ch1 + " " );
        }

    }
}
