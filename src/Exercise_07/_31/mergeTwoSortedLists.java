package Exercise_07._31;


import java.util.Scanner;

public class mergeTwoSortedLists { // github
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter a list1
        System.out.print( "Enter list1 size and contents: " );
        int size1 = input.nextInt();
        int[] list1 = new int[size1];

        for (int i = 0; i < list1.length ; i++)
            list1[i] = input.nextInt();

        // Prompt the user to enter a list2
        System.out.print( "Enter list2 size and contents: " );
        int size2 = input.nextInt();
        int[] list2 = new int[size2];

        for (int i = 0; i < list2.length ; i++)
            list2[i] = input.nextInt();

        // Merge two lists in one sorted list
        int[] mergeArray = merge( list1, list2 );

        // Sort any array of integer
        sort(mergeArray);

        // Display result
        System.out.println("List1 is " +
                java.util.Arrays.toString( list1 ));

        System.out.println("List2 is " +
                java.util.Arrays.toString( list2 ));

        System.out.println("The merged list is " +
                java.util.Arrays.toString( mergeArray ) );
    }

    /** Sort a list in ascending order */
    public static void sort(int[] list){
        for (int i = 0; i < list.length; i++) {
            int min = list[i];
            int minIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < min){
                    min = list[j];
                    minIndex = j;
                }
            }

            if (minIndex != i){
                list[minIndex] = list[i];
                list[i] = min;
            }
        }
    }


    /** Merge two arrays in one array */
    public static int[] merge(int[] list1, int[] list2){
        int[] mergeArray = new int[list1.length + list2.length];

        for (int i = 0; i < list1.length; i++)
                mergeArray[i] = list1[i];

        for (int i = 0; i < list2.length; i++)
            mergeArray[list1.length + i] = list2[i];

        return mergeArray;
    }
}
