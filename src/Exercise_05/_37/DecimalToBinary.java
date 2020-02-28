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
        while (decimal != 0) {
            if (decimal % 2 == 0)
                binary = "0" + binary;
            else
                binary = "1" + binary;
            decimal /= 2;
        }

        // Display result
        System.out.println(binary);
    }
}
