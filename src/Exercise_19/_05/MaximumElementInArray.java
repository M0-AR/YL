package Exercise_19._05;

import java.util.Scanner;

public class MaximumElementInArray { // github
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        Integer[] numbers = new Integer[10];

        // Prompt the user to enter 10 digits
        System.out.print( "Enter 10 digits: " );
        for (int i = 0; i < numbers.length ; i++)
            numbers[i] = input.nextInt();

        // Display max of 10 digits
        System.out.println("The max number is: " + max( numbers ));

    }

    public static <E extends Comparable<E>> E max (E[] list){
        E max = list[0];
        for (int i = 1; i < list.length ; i++) {
            if (list[i].compareTo( max ) > 0){
                max = list[i];
            }
        }
        return max;
    }
}
