package Exercise_22._05;

import java.util.Scanner;

public class Exercise_22_05 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a series of numbers ending with 0:");
        int value = input.nextInt();
        int index = 0;
        int bestStart = 0;
        int bestValue = 0;
        int bestCount = 0;
        int currentStart = 0;
        int currentValue = 0;
        int currentCount = 0;

        while (value != 0) {
            if (currentCount > 0 && value == currentValue) {
                currentCount++;
            } else {
                if (currentCount > bestCount) {
                    bestCount = currentCount;
                    bestStart = currentStart;
                    bestValue = currentValue;
                }
                currentValue = value;
                currentStart = index;
                currentCount = 1;
            }
            index++;
            value = input.nextInt();
        }

        if (currentCount > bestCount) {
            bestCount = currentCount;
            bestStart = currentStart;
            bestValue = currentValue;
        }

        System.out.println("The longest same number sequence starts at index "
                + bestStart + " with " + bestCount + " values of " + bestValue);
    }
}
