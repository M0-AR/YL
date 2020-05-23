package Exercise_07._30;
/*********************************************************************************
 * (Pattern recognition: consecutive four equal numbers) Write the following      *
 * method that tests whether the array has four consecutive numbers with the same *
 * value.                                                                         *
 *                                                                                *
 * public static boolean isConsecutiveFour(int[] values)                          *
 *                                                                                *
 * Write a test program that prompts the user to enter a series of integers and   *
 * displays if the series contains four consecutive numbers with the same value.  *
 * Your program should first prompt the user to enter the input size—i.e., the    *
 * number of values in the series.                                                *
 *********************************************************************************/
import java.util.Scanner;

public class consecutiveFourEqualNumbers { // github and yl
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a series of integers
        System.out.print( "Enter tne number of values: ");
        int length = input.nextInt();

        int[] list = new int[length];

        System.out.print("Enter the values: ");
        for (int i = 0; i < list.length; i++)
            list[i] = input.nextInt();

        // Display whether the list has four consecutive numbers or not
        System.out.println("The list has" +
                ((isConsecutiveFour( list ))? " ":" no ") + "consecutive fours");
    }

    /** Check whether the array has four consecutive numbers or not */
    public static boolean isConsecutiveFour(int[] values){
        for (int i = 0; i < values.length - 3; i++)
            if (values[i] == values[i+1] && values[i+2] == values[i+3])
                return true;

        return false;
    }

    /** Second and smart solution */
    public static boolean isConsecutiveFourVersionII(int[] values) {
        outer: for (int i = 0; i < values.length - 3; i++) {
            for (int j = i; j < i + 3; j++)
                if (values[j] != values[j + 1])
                    continue outer; // See Supplement Part III.E statement labels
            return true;
        }

        return false;
    }
}
