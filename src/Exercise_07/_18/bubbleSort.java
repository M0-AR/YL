package Exercise_07._18;
/*********************************************************************************
 * (Bubble sort) Write a sort method that uses the bubble-sort algorithm. The     *
 * bubblesort algorithm makes several passes through the array. On each pass,     *
 * successive neighboring pairs are compared. If a pair is not in order, its      *
 * values are swapped; otherwise, the values remain unchanged. The technique is   *
 * called a bubble sort or sinking sort because the smaller values gradually      *
 * “bubble” their way to the top and the larger values “sink” to the bottom.      *
 * Write a test program that reads in ten double numbers, invokes the method,     *
 * and displays the sorted numbers.                                               *
 *********************************************************************************/
import java.util.Scanner;

public class bubbleSort {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        double[] numbers = new double[10];

        // Prompt the user to enter 10 double numbers
        System.out.print("Enter 10 double numbers: ");
        for(int i = 0; i < numbers.length ; i++)
            numbers[i] = input.nextDouble();


        // Use bubble sort method
        bubbleSort( numbers );

        // Display the numbers of array
        for (double number : numbers) {
            System.out.print( number + " " );
        }

    }


    /** Bubble Sort*/
    public static void bubbleSort(double[] array){
        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < array.length - 1; i++){
                // if a neighboring pair is not in order, swap balues
                if(array[i] > array[i + 1]){
                    double temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        }while (swapped);
    }
}
