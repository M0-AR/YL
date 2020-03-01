package Exercise_05._38;
/*
(Decimal to octal) Write a program that prompts the user to enter a decimal
integer and displays its corresponding octal value. Don’t use Java’s Integer
.toOctalString(int) in this program.
*/
import java.util.Scanner;

public class DecimalToOctal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter a decimal integer
        System.out.print("Enter an integer: ");
        int decimal = input.nextInt();

        // Convert decimal to octal
        String octal = "";
        for (int i = decimal; i > 0 ; i/=8)
            octal = i % 8 + octal;

        // Display results
        System.out.println("The octal of " + decimal + " is " + octal);

    }

    public static void myMethod(int decimal){
        String octalString = "";
        String[] split;

        while (decimal >= 1) {
            decimal /= 8;

            // Store the decimal in string and split it by comma in to array
            split = String.valueOf( decimal ).split( "\\." );

            // add 0. to th decimal number
            split[1] = "0." + split[1];
            // Store the output in octalString
            octalString = (int) (Double.parseDouble( split[1] ) * 8) + octalString;
        }

        // Display result
        System.out.println( octalString );
    }

    public static void YL(int decimal){
        String octalString = "";
        int value = decimal;
        while (value != 0) {
            int single = value % 8;
            octalString = single + octalString;
            value = value / 8;
        }

        System.out.println(decimal + "'s octal representation is " +
                octalString);

    }


}
