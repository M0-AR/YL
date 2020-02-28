package Exercise_05._32;
/*
(Game: lottery) Revise Listing 3.8, Lottery.java, to generate a lottery of a twodigit
number. The two digits in the number are distinct. (Hint: Generate the first
digit. Use a loop to continuously generate the second digit until it is different
from the first digit.)
*/
public class LotteryGame {
    public static void main(String[] args) {
        // Generate to digit number
        int first = (int)(Math.random() * 10) + 1;
        int second = (int)(Math.random() * 10) + 1;

        // Change second if first == second
        while (first == second){
            second = (int)(Math.random() * 10) + 1;
        }

        // Prompt the user to enter a guess
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter your two digits lottery pick: ");

        int guess = input.nextInt();

        // Check the guess
        if (guess / 10 == first && guess % 10 == second) {
            System.out.println("Exact match: you win $10,000");
        }
        else if (guess % 10 == first && guess / 10 == second) {
            System.out.println("Match all digits: you win $3,000");
        }
        else if (guess % 10 == first || guess % 10 == second
                || guess / 10 == first || guess / 10 == second) {
            System.out.println("Match one digit: you win $1,000");
        }
        else {
            System.out.println("Sorry, no match");
        }

        // Display result
        System.out.println("First Digit; " + first + "\nSecond Digit: " + second);


    }
}
