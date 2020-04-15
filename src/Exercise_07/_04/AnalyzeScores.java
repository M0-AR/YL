package Exercise_07._04;
/**
(Analyze scores) Write a program that reads an unspecified number of scores and
determines how many scores are above or equal to the average and how many
scores are below the average. Enter a negative number to signify the end of the
input. Assume that the maximum number of scores is 100.
*/
import java.util.Scanner;

public class AnalyzeScores {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );

        // Hold the scores in the array
        int[] scores = new int[100];

         int count = 0;
         double averageOfScores = 0;

        do {
            System.out.print("Enter a new score: ");

            scores[count] = input.nextInt();

            if (scores[count] > 0)
                averageOfScores += scores[count];

        }while (scores[count++] > 0);

        System.out.println("Count is " + count);
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
