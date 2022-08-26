package Exercise_11._16;

import java.util.HashSet;
import java.util.Scanner;

public class AdditionQuiz {
    public static void main(String[] args) {
        HashSet<Integer> alreadyEnteredList = new HashSet<>();
        String question = "What is 5 + 9? ";
        int sum = 0, num1 = 5, num2 = 9, userInput;

        sum = num1 + num2;

        do {
            userInput = promptingUserToEnterNumberUntilNumberMatch(question);
            System.out.print((alreadyEnteredList.contains(userInput)) ? "You already entered " + userInput + "\n": "");

            if (sum != userInput) {
                System.out.print("Wrong answer. Try again. ");
                alreadyEnteredList.add(userInput);
            }

        } while (sum != userInput);

        System.out.println("You got it!");
    }

    public static int promptingUserToEnterNumberUntilNumberMatch(String message) {
        Scanner input = new Scanner(System.in);

        System.out.print(message);
        return input.nextInt();
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }
}
