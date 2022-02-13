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
        for (int i = 0; i < n; i++) {
            int count1Bit = 0;
            for (int j = 0; j < n; j++) {
                if ('1' == bits.get(i).charAt(j)) {
                   count1Bit++;
                }
            }
            rowWith1Bit.add(count1Bit);
        }


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
}

/*
0011
0011
1101
1010
 */
