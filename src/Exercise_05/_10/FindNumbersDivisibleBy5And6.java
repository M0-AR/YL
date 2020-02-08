package Exercise_05._10;
/*
(Find numbers divisible by 5 and 6) Write a program that displays all the numbers
from 100 to 1,000, ten per line, that are divisible by 5 and 6. Numbers are
separated by exactly one space.
*/
public class FindNumbersDivisibleBy5And6 {
    public static void main(String[] args) { // g and y
        final int LAST_DESTINATION = 1000;

        int counter = 100;
        int counterForNewLine = 0;
        while (counter < LAST_DESTINATION){
            if (counter % 6 == 0 && counter % 5 == 0){
                System.out.print( counter + " " );
                counterForNewLine++;
                if (counterForNewLine%10 == 0)
                    System.out.println();
            }
            counter++;
        }


        // OR
        int count = 1;
        for (int i = 100; i <= 1000; i++)
            if (i % 5 == 0 && i % 6 == 0)
                System.out.print((count++ % 10 != 0) ? i + " ": i + "\n");

    }
}
