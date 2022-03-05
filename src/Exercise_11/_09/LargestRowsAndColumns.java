package Exercise_11._09;

import java.util.ArrayList;
import java.util.Scanner;

public class LargestRowsAndColumns {
    public static void main(String[] args) {
        ArrayList<String> bits = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the array size n: ");
        int n = input.nextInt();

        System.out.println("\nThe random array is ");
        for (int i = 0; i < n; i++) {
            bits.add(input.next());
        }


        ArrayList<Integer> rowWith1Bit = new ArrayList<>();
        countOneBit(bits, rowWith1Bit, n, 0, 0);



        // Find max in rowWith1Bit
        ArrayList<Integer> maxRowWith1Bit = new ArrayList<>();
        for (int i = 0; i < rowWith1Bit.size(); i++) {
            if (i == 0) maxRowWith1Bit.add(rowWith1Bit.get(0));
            if (rowWith1Bit.get(i) > maxRowWith1Bit.get(0)) {
                maxRowWith1Bit = new ArrayList<>();
                maxRowWith1Bit.add(rowWith1Bit.get(i));
            } else if (rowWith1Bit.get(i) == maxRowWith1Bit.get(0)) {
                maxRowWith1Bit.add(rowWith1Bit.get(i));
            }
        }
        /* // bug with this input
        0111
        0011
        1101
        1010
         */

        for (Integer integer : maxRowWith1Bit) {
            System.out.print(integer + " ");
        }

        ArrayList<Integer> maxColumnWith1Bit= new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count1Bit = 0;
            for (int j = 0; j < n; j++) {
                if ('1' == bits.get(j).charAt(i)) {
                    count1Bit++;
                }
            }
            maxColumnWith1Bit.add(count1Bit);
        }

        // Find max in maxColumnWith1Bit
        // Todo

    }


    private static void countOneBit(ArrayList<String> bits, ArrayList<Integer> array, int n, int i, int j) {
        for (i = 0; i < n; i++) {
            int count1Bit = 0;
            for (j = 0; j < n; j++) {
                if ('1' == bits.get(i).charAt(j)) { // Todo: it works for row, flip i and j to make work for column
                    count1Bit++;
                }
            }
            array.add(count1Bit);
        }
    }

   /** Return the maximum integer in the array */
   public static int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
           if (a[i] > max)
               max = a[i];
        }
        return max;
   }

   /** Adds the indices of x in count to the list */
   public static void getIndex(ArrayList<Integer> list, int[] count, int x) {
       for (int i = 0; i < count.length; i++) {
           if (count[i] == x)
               list.add(i);
       }
   }


   /** Fill array randomly with 0s and 1s */
   public static void fill(int[][] array) {
       for (int i = 0; i < array.length; i++) {
           for (int j = 0; j < array.length; j++) {
               array[i][j] = (int)(Math.random() * 2);
           }
       }
   }

   /** Display matrix */
   public static void print(int[][] array) {
       System.out.println("The random array is");
       for (int i = 0; i < array.length; i++) {
           for (int j = 0; j < array.length; j++) {
               System.out.println(array[i][j]);
           }
           System.out.println();
       }
   }

}

/*
0011
0011
1101
1010
 */
