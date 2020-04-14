package Exercise_07._04;

import java.util.Scanner;

public class AnalyzeScores {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Hold the scores in the array
        int[] scores = new int[100];

         int count = 0;
         double averageOfScores = 0;

        do {
            scores[count] = input.nextInt();
            if (scores[count] > 0)
                averageOfScores += scores[count];
        }while (scores[count++] > 0);

        // Calculate the average
        averageOfScores /= count-1;


        // Determine how many below and above the average
        int aboveOrEqual = 0;
        int below = 0;
        for (int score : scores) {
            if (score >= averageOfScores)
                aboveOrEqual++;
            else if (score > 0)
                below++;
        }

        // Display result
        System.out.println(
                "The count of above Or equal to the average " + aboveOrEqual +
                        "\nThe count of below to the average " + below);

    }
}
