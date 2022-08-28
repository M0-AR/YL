package Exercise_11._16;

import java.util.HashSet;
import java.util.Scanner;

public class AdditionQuiz {
    public static void main(String[] args) {
        HashSet<Integer> alreadyEnteredList = new HashSet<>();

        int answer;
        int number1 = (int) (Math.random() * 10);
        int number2 = (int) (Math.random() * 10);

        String message = "What is " + number1 + " + " + number2;

        do {
            answer = promptingUserToEnterNumberUntilNumberMatch(message);
            System.out.print((alreadyEnteredList.contains(answer)) ? "You already entered " + answer + "\n": "");

            if ((number1 + number2) != answer) {
                System.out.print("Wrong answer. Try again. ");
                alreadyEnteredList.add(answer);
            }

        } while ((number1 + number2) != answer);

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
