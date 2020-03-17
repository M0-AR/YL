package Exercise_18._13;

import java.util.Scanner;

public class FindLargestNumberInArray {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print("Enter list of eight integers: ");
        int[] n = new int[8];
        for (int i = 0; i < n.length ; i++)
            n[i] = input.nextInt();
        System.out.println( largestNumber(n) );
    }

    public static int largestNumber(int[] n){
        return largestNumberInArray( n, 0, n.length -1 );
    }

    public static int largestNumberInArray(int[]n , int i, int  j){
        // Base case when i reach the last element so return it
        if (i == j)
            return n[i];
        return Math.max( n[i], largestNumberInArray( n, i+1, j ) );
    }


}
