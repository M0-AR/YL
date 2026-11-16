package Exercise_04._19;

import java.util.Scanner;

public class CheckISBN_10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first 9 digits of an ISBN as a string: ");
        String isbn = input.next();

        if (isbn.length() != 9 || !isbn.matches("\\d{9}")) {
            System.out.println("The input must contain exactly 9 digits.");
            return;
        }

        int sum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            sum += (isbn.charAt(i) - '0') * (i + 1);
        }

        int checksum = sum % 11;
        String result = isbn + (checksum == 10 ? "X" : String.valueOf(checksum));
        System.out.println("The ISBN-10 number is " + result);

        input.close();
    }
}
