package Exercise_07._30;

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
        for (int i = 0; i < values.length; i++) {
            if (values.length-1 - i == 3)
                break;
            if (values[i] == values[i+1] && values[i+2] == values[i+3])
                return true;
        }
        return false;
    }
}
