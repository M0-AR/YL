package Exercise_07._32;

import java.util.Scanner;

public class partitionOfList {
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter size of a list and a list
        System.out.print( "Enter list size: " );
        int size = input.nextInt();

        System.out.print( "Enter list content: " );
        int[] list = new int[size];

        for (int i = 0; i < list.length ; i++)
            list[i] = input.nextInt();

        // Make the partition part
        partition( list );

        // Display result
        System.out.print("After the partition, the list is ");
        for (int i : list) {
            System.out.print( i + " " );
        }
    }

    /** Partitions the list using hte first element, called a pivot*/
    public static int partition(int[] list){
        int pivot = list[0];
        int pivotIndex = 0;
        int i = 1; // i for left element
        int j = list.length - 1; // j for right element
        while (true) {
            // Swap left element with right element
            if (list[i] > pivot && list[j] < pivot){
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }

            if (list[i] < pivot && i != j)
                i++;

            if (list[j] > pivot && i != j)
                j--;

            // Swap middle element with pivot
            if (list[i] == list[j] && pivot > list[i]){
                list[0] = list[i];
                list[i] = pivot;
                pivotIndex = i;
                break;
            }

            // if the middle element greater or equal to pivot
            // swap the element before the middle with pivot
            if (list[i] == list[j] && pivot <= list[i]){
                list[0] = list[i-1];
                list[i-1] = pivot;
                pivotIndex = i-1;
                break;
            }
        }
        return pivotIndex;
    }
}
