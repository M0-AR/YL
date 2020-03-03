package Exercise_05._44;
/*
(Computer architecture: bit-level operations) A short value is stored in 16 bits.
Write a program that prompts the user to enter a short integer and displays the 16
bits for the integer. Here are sample runs:
*/
import java.util.Scanner;

public class Bit_level_operations {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a short digit
        System.out.print( "Enter a short digit: " );
        int value = input.nextShort();

        // Display result
        int mask = 1;
        for (int i = 15; i >= 0; i--) {
            int temp = value >> i;
            int bit = temp & mask;
            System.out.print(bit);
        }

    }
}
