package Exercise_12._03;

import java.util.Scanner;

public class arrayIndexOutOfBoundsException {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter an index
        System.out.println( "Enter an index: " );
        int userIndex = input.nextInt();

        // Create an array of 100 length
        int[] n = new int[100];
        // Full the array with a random number
        for (int i = 0; i < n.length; i++)
            n[i] = (int)(Math.random() * 100) + 1;

        try {
            System.out.println("The number of the index is " + n[userIndex]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println( e.getMessage() );
        }

    }
}
