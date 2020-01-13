package Exercise_04._11;
/*
(Decimal to hex) Write a program that prompts the user to enter an integer between
0 and 15 and displays its corresponding hex number.
*/
import java.util.Scanner;

public class DecimalToHex {
    public static void main(String[] args) { // y and g
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter a value 0 to 15
        System.out.print("Enter a decimal value (0 to 15): ");
        int value = scanner.nextInt();

        //Display1 result
        if (value >= 0 && value<=9)
            System.out.println("The hex value is " + value);
        else if(value >= 10 && value<=15)
            System.out.println("The hex value is " + (char)(value + 'A' -10));
        else {
            System.out.print(value + " is an invalid input");
        }
        // Display2 result
        if (value > 15){
            System.out.print(value + " is an invalid input");
        }else {
            String convert = Integer.toHexString( value );
            System.out.print("The hex value is " + Character.toUpperCase( convert.charAt( 0 )));
        }

    }
}
