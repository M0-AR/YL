package Exercise_07._27;
/*********************************************************************************
 * (Identical arrays) The arrays list1 and list2 are identical if they have the   *
 * same contents. Write a method that returns true if list1 and list2 are         *
 * identical, using the following header:                                         *
 *                                                                                *
 * public static boolean equals(int[] list1, int[] list2)                         *
 *                                                                                *
 * Write a test program that prompts the user to enter two lists of integers and  *
 * displays whether the two are identical. Here are the sample runs. Note that    *
 * the first number in the input indicates the number of the elements in the list.*
 * This number is not part of the list.                                           *
 *********************************************************************************/
import java.util.Scanner;

public class identicalArrays {
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


        // Display if arrays are strictly identical or not
        System.out.println("TWo lists are" + ((equals(list1, list2))?" ":
                " not ") + "strictly identical");
    }

    /** Check if two arrays are identical or not */
    public static boolean equals(int[] list1, int[] list2){
        if (list1.length != list2.length)
            return false;

        // Check if list2 has same elements in list1
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list1.length ; j++) {
                if (list1[i] == list2[j])
                    break;
                if (j == list1.length-1 && list1[i] != list2[j])
                    return false;
            }
        }

        // Check if list1 has same elements in list2
        for (int i = 0; i < list2.length; i++) {
            for (int j = 0; j < list2.length ; j++) {
                if (list2[i] == list1[j])
                    break;
                if (j == list2.length-1 && list2[i] != list1[j])
                    return false;
            }
        }

        return true;
    }
}
