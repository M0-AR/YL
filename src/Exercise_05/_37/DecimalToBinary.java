package Exercise_05._37;

import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) { // github
        Scanner input = new Scanner( System.in );

        // Prompt a user to enter a decimal integer
        System.out.print("Enter a decimal integer: ");
        int decimal = input.nextInt();

        // Output from decimal to binary
        String binary = "";

        // Convert decimal to binary
        int i = decimal;
        while (i != 0) {
            binary = (i % 2) + binary;
            i /= 2;
        }

        // Display result
        System.out.println("The binary value of the decimal \"" + decimal + "\" is: " + binary);
    }
}
