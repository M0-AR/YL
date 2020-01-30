package Exercise_05._02;

import java.util.Scanner;

public class AdditionsQuiz { // github and y
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        final int NUMBER_OF_QUESTIONS = 10;
        int countOfQuestions = 0; // Should be just 10 questions each time
        int countRightAnswers = 0;
        String output = "";//  output string is initially empty

        long startTime = System.currentTimeMillis();

        while (countOfQuestions < NUMBER_OF_QUESTIONS) {
            // Generate to random numbers
            int randomNumber1 = (int) (Math.random() * 15 + 1);
            int randomNumber2 = (int) (Math.random() * 15 + 1);
            // Prompt the user to answer the questions
            System.out.println( "What is " + randomNumber1 + " + " + randomNumber2 + " = ?" );
            int answer = input.nextInt();
            if (answer == (randomNumber1 + randomNumber2)) {
                System.out.println("You are correct!");
                countRightAnswers++;
            } else {
                System.out.println("Your answer is wrong.\n" + randomNumber1 + " + " + randomNumber2 + " = " + (randomNumber1+randomNumber2));
            }

            countOfQuestions++;
            output += "\n" + randomNumber1 + " + " + randomNumber2 + " = " + answer + ((randomNumber1 + randomNumber2 == answer)? " correct": " wrong");
        }


        long endTime = System.currentTimeMillis();
        long testTime = endTime - startTime;

        System.out.println("Correct count is " + countRightAnswers + "\nTest time is " + testTime/1000 + " seconds\n" + output);

    }
}
