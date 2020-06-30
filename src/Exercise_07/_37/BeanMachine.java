package Exercise_07._37;

import java.util.Scanner;

public class BeanMachine {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user
        System.out.print("Enter the number of balls to drop: "); // Prompt the user to enter the number of balls
        int numberOfBalls = input.nextInt();

        System.out.print("Enter the number of slots in the bean machine: "); // Prompt the user to enter tne number of slots
        int numberOfSlots = input.nextInt();

        // Initialise two arrays
        String[] ballsPath = new String[numberOfBalls]; // Stores balls path
        int[] slots = new int[numberOfSlots]; // Stores the number of balls in a slot

        // Call methods
        generateRandomPath(ballsPath); // To generate path for every ball in ballsPath array
        countRLetter(ballsPath, slots); // Count R letter in ballsPath and increase the number of slot index by one according to ball's path
        printBalls(ballsPath, slots); // Print Ball's path and Balls in right slot
    }

    /** To generate path for every ball in ballsPath array */
    public static void generateRandomPath(String[] array) {
        for (int i = 0; i < array.length ; i++) {
            array[i] = "";
            for (int j = 0; j < 7; j++) { // 7 is the number of letters in every index in array
                int zeroOrOne = (int)(Math.random() * 2); // Zero = Left and One = Right
                if(zeroOrOne == 1)
                    array[i] += "R";
                else
                    array[i] += "L";
            }
        }
    }

    /** Count R letter in ballsPath and increase the number of slot index by one according to ball's path */
    public static void countRLetter(String[] ballsPath, int[] slots) {
        for (int i = 0; i < ballsPath.length; i++) {
            int countR = 0;
            for (int j = 0; j < ballsPath[i].length(); j++)
                if (ballsPath[i].charAt( j ) == 'R')
                    countR++;

            slots[countR % slots.length]++; // Increase one in the slots index
        }
    }

    /** Print Ball's path and Balls in right slot */
    public static void printBalls(String[] ballsPath, int[] slots) {
        System.out.println("\n");
        // Print balls's path
        for (int i = 0; i < ballsPath.length; i++)
            System.out.println(ballsPath[i]);

        // Call method
        int max = findMaximumNumber(slots); // To find maximum number of balls in a specific slot

        System.out.println("\n");
        // Print balls in slot
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < slots.length; j++) {
                if (max == slots[j]) {
                    // Print spaces
                    for (int k = 0; k < j; k++) {
                        System.out.print( " " );
                    }
                    System.out.print( "O" );
                    slots[j]--;
                }
            }
            System.out.println();
            max--;
        }
    }

    /** Find maximum number in array */
    public static int findMaximumNumber(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length -1; i++)
            if (max < array[i])
                max = array[i];

        return max;
    }


}
