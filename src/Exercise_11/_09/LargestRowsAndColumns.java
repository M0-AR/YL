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


        ArrayList<Integer> maxRowWith1Bit = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count1Bit = 0;
            for (int j = 0; j < n; j++) {
                if ('1' == bits.get(i).charAt(j)) {
                   count1Bit++;
                }
                // Todo store more than one row max count
            }
        }

        ArrayList<String> maxColumnWith1Bit= new ArrayList<>();
        for (String bit : bits) {
            System.out.print(bit + " ");
        }
    }
}
