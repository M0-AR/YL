package Exercise_07._15;

import java.util.Scanner;

public class EliminateDuplicates {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create an array of 10 length
        int[] n = new int[10];

        // Prompt the user to enter 10 numbers
        System.out.print( "Enter 10 numbers: " );
        for (int i = 0; i < n.length ; i++)
            n[i] = input.nextInt();

        // Display the eliminate duplicates array
        System.out.print( "The distinct numbers are: " );
        for (int i : eliminateDuplicates( n ))
            if (i != 0)
                System.out.print( i + " " );
    }

    /** Return an array with out duplicating */
    public static int[] eliminateDuplicates(int[] list){
        int[] temp = new int[list.length];

        for (int i = 0; i < list.length ; i++) {
            for (int j = 0; j < list.length ; j++) {
                // break when find any duplicating
                if ( list[i] == temp[j] )
                    break;

                // 0 mean an empty place and know that
                // we reach the end of temp array and
                // the add the element at the end of array
                if (temp[j] == 0){
                    temp[j] = list[i];
                    break;
                }

            }
        }

        return temp;
    }
}




