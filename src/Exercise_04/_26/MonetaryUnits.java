package Exercise_04._26;

import java.util.Scanner;

public class MonetaryUnits {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an amount, for example 11.56: ");
        String amount = input.nextLine();

        String[] parts = amount.split("\\.");
        int numberOfOneDollars = Integer.parseInt(parts[0]);
        String centsText = parts.length > 1 ? (parts[1] + "00").substring(0, 2) : "00";
        int numberOfCents = Integer.parseInt(centsText);

        int numberOfQuarters = numberOfCents / 25;
        numberOfCents %= 25;
        int numberOfDimes = numberOfCents / 10;
        numberOfCents %= 10;
        int numberOfNickels = numberOfCents / 5;
        numberOfCents %= 5;
        int numberOfPennies = numberOfCents;

        System.out.println("Your amount " + amount + " consists of");
        System.out.println("    " + numberOfOneDollars + " dollars");
        System.out.println("    " + numberOfQuarters + " quarters");
        System.out.println("    " + numberOfDimes + " dimes");
        System.out.println("    " + numberOfNickels + " nickels");
        System.out.println("    " + numberOfPennies + " pennies");

        input.close();
    }
}
