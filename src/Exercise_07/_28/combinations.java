package Exercise_07._28;

import java.util.Scanner;

public class combinations { // github and yl
    /** Main method */
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter 10 integers
        System.out.println( "Enter 10 integers numbers: " );
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length ; i++)
            numbers[i] = input.nextInt();

        // Display result
        displayCombinations(numbers);

    }

    /** To display all combinations for every number in the array */
    public static void displayCombinations(int[] list){
        for (int i = 0; i < list.length; i++)
            for (int j = i + 1; j < list.length; j++)
                System.out.println(list[i] + " " + list[j]);
    }
}
