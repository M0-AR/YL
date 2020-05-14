package Exercise_07._20;
/*********************************************************************************
 * (Revise selection sort) In Section 7.11, you used selection sort to sort an    *
 * array. The selection-sort method repeatedly finds the smallest number in the   *
 * current array and swaps it with the first. Rewrite this program by finding the *
 * largest number and swapping it with the last. Write a test program that reads  *
 * in ten double numbers, invokes the method, and displays the sorted numbers.    *
 *********************************************************************************/
import java.util.Scanner;

public class ReviseSelectionSort {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Create a double array of length 10
        double[] array = new double[10];

        // Prompt the user to enter 10 numbers
        System.out.println( "Enter 10 numbers " +
                "separated by space(e.g, 1 2 3.4 ..): " );
        for(int i = 0; i < array.length; i++)
            array[i] = input.nextDouble();


        // Sort the array in decreasing order
        selectionSort(array);


        // Display the sorted array
        System.out.println( java.util.Arrays.toString(array));

    }


    /** Sorting numbers in decreasing order */
    public static void selectionSort(double[] list){
        double currentMax;
        int currentMaxIndex;

        for(int i = list.length - 1; i >= 0; i--){
            // Find the maximum in the list[i..list.length-1]
            currentMax = list[i];
            currentMaxIndex = i;

            for(int j = i - 1; j >= 0; j--){
                if(currentMax < list[j]){
                    currentMax = list[j];
                    currentMaxIndex =  j;
                }
            }

            // Swap list[i] with list[currentMaxIndex] if necessary;
            if (currentMaxIndex != i){
                list[currentMaxIndex] = list[i];
                list[i] = currentMax;
            }
        }
    }

}
