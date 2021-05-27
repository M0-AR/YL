package Exercise_08._37;

import java.util.Scanner;

public class GuessTheCapitals {
    public static void main(String[] args) {
        String[][] capitalAndState = {{"Sy", "DM"}, {"Da", "Co"}};

        Scanner input = new Scanner( System.in );

        String userGuess;
        int correctUserAnswer = 0;

        for (int i = 0; i < capitalAndState.length; i++) {
            System.out.print("What is the capital of "
                                    + capitalAndState[i][0] + "? ");
            userGuess = input.nextLine();

            if (userGuess.equalsIgnoreCase(capitalAndState[i][1])) {
                System.out.println("Your answer is correct");
                correctUserAnswer++;
            } else {
                System.out.println("The correct answer should be "
                                            + capitalAndState[i][1]);
            }
        }

        System.out.println("The correct count is " + correctUserAnswer);
    }
}
