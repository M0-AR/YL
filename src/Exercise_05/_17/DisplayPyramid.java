package Exercise_05._17;
/*
(Display pyramid) Write a program that prompts the user to enter an integer from
1 to 15 and displays a pyramid, as shown in the following sample run:
*/
import java.util.Scanner;

public class DisplayPyramid { // g
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter an integer from 1 to 15
        System.out.print("Enter the number of lines: ");
        int spacesAndLines = input.nextInt();

        // Display pyramid
        for (int i = 1; i <= spacesAndLines ; i++) {
            // Take care of spaces before numbers
            for (int j = i; j <spacesAndLines ; j++) {
                System.out.print( "  " );
            }
            // take care of left side's numbers
            for (int j = i; j >0 ; j--) {
                System.out.print(j + " ");
            }

            // Take care of right side's numbers
            int temp = i;
            for (int j = 2; j <= spacesAndLines && temp > 1; j++) {
                System.out.print(j + " ");
                temp--;
            }
            System.out.println();
        }

    }
}
