package Exercise_05._41;

import java.util.Scanner;

/*
(Occurrence of max numbers) Write a program that reads integers, finds the largest
of them, and counts its occurrences. Assume that the input ends with number
0. Suppose that you entered 3 5 2 5 5 5 0; the program finds that the largest
is 5 and the occurrence count for 5 is 4.
(Hint: Maintain two variables, max and count. max stores the current max number,
and count stores its occurrences. Initially, assign the first number to max
and 1 to count. Compare each subsequent number with max. If the number is
greater than max, assign it to max and reset count to 1. If the number is equal to
max, increment count by 1.)
*/
public class OccurrenceOfMaxNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        /** Prompt the user to enter number  */
        System.out.print( "Enter numbers Exm(3 5 2 5 5 0): " );
        String numbers = input.nextLine();

        int max = 0;
        int count = 1;

        for (int i = 0; i < numbers.length() ; i+=2) {

            if (max < numbers.charAt( i )){
                max = numbers.charAt( i );
                count = 1;
            }else if (max == numbers.charAt( i ))
                count++;

        }

        /** Display result */
        System.out.println("The largest number is: " + (char)max);
        System.out.println("The occurrence count of the largest number is: " + count);
    }


}
