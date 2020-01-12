package Exercise_04._11;

import java.util.Scanner;

public class DecimalToHex {
    public static void main(String[] args) { // y and g
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a value 0 to 15
        System.out.print("Enter a decimal value (0 to 15): ");
        int value = scanner.nextInt();

        // Display result
        if (value > 15){
            System.out.print(value + " is an invalid input");
        }else {
            String convert = Integer.toHexString( value );
            System.out.print("The hex value is " + Character.toUpperCase( convert.charAt( 0 )));
        }

    }
}
