package Exercise_05._44;

import java.util.Scanner;

public class Bit_level_operations {
    public static void main(String[] args) { // github and y
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a short digit
        System.out.print( "Enter a short digit: " );
        short number = input.nextShort();


        // Display result
        if (number >= 0) /** number is positive */
            System.out.println( String.format( "%016d", Integer.parseInt( Integer.toBinaryString( number ) ) ) );
        else { /** number is negative */
            number = (short) (number >> 1); // todo shrank number 1
            System.out.println( Integer.toBinaryString( number ) );
        }

    }
}
