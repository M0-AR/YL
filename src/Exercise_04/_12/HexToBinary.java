package Exercise_04._12;

import java.util.Scanner;

public class HexToBinary {
    public static void main(String[] args) { // what >> mean in parameter and check y and g
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a hex digit
        System.out.print( "Enter a hex digit: " );
        char hexDigit = scanner.next().charAt( 0 );
        int numberOfHexDigit = 0;
        if (hexDigit > '9' && hexDigit < '0' && hexDigit < 'A' && hexDigit > 'F') {
            System.out.print( hexDigit + " is an invalid input" );
        } else {
            if (hexDigit >= 'A' && hexDigit <= 'F') {
                numberOfHexDigit = hexDigit - 'A' + 10;
            } else if (hexDigit >= '0' && hexDigit <= '9') {
                numberOfHexDigit = hexDigit - '0';
            }
            printBinaryForm( numberOfHexDigit );
        }

    }

    public static void printBinaryForm(int number) {
        int remainder;

        if (number <= 1) {
            System.out.print( number );
            return; // Kick out of the recursion
        }

        remainder = number % 2;
        printBinaryForm( number >> 1);
        System.out.print( remainder );
    }
}

