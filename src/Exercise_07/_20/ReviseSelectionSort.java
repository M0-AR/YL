package Exercise_07._20;


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
        double currentMin;
        int currentMinIndex;

        for(int i = 0; i < list.length; i++){
            // Find the smallest in the list[i..list.length-1]
            currentMin = list[i];
            currentMinIndex = i;

            for(int j = i + 1; j < list.length; j++){
                if(currentMin > list[j]){
                    currentMin = list[j];
                    currentMinIndex =  j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if necessary;
            if (currentMinIndex != i){
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

}
