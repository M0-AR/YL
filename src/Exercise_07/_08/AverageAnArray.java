package Exercise_07._08;

import java.util.Scanner;

public class AverageAnArray { // github and y
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        int[] n = new int[10];
        double[] d = new double[10];

        System.out.print("Enter 10 digits number: ");
//        for (int i = 0; i < 10 ; i++)



    }

    /** Return average of integer number in an array */
    public static int average(int[] array){
        int sum = 0;
        for (int i : array)
            sum += i;
        return sum / array.length;
    }

    /** Return average of double number in an array */
    public static double average(double[] array){
        double sum = 0;
        for (double i : array)
            sum += i;
        return sum / array.length;
    }
}
