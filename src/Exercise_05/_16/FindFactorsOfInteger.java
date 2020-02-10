package Exercise_05._16;

import java.util.Scanner;
/*
(Find the factors of an integer) Write a program that reads an integer and displays
all its smallest factors in increasing order. For example, if the input integer is
120, the output should be as follows: 2, 2, 2, 3, 5.
*/
public class FindFactorsOfInteger {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        // Prompt the user to enter a positive integer
        System.out.print(
                "Enter a positive integer: ");
        int number = input.nextInt();

        // Find all the smallest factors of the integer
        int factor = 2;
        while (number != 1){
            if (number % factor ==  0) {
                number /= factor;
                System.out.print( factor + " ");
            } else 
                factor++;
        }

        System.out.println();
        
    }
}
