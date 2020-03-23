package Exercise_18._13;

/*********************************************************************************
 * (Find the largest number in an array) Write a recursive method that returns    *
 * the largest integer in an array. Write a test program that prompts the user to *
 * enter a list of eight integers and displays the largest element.               *
 *********************************************************************************/
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
        // Base case to reach last element and come back
        if (i == j)
            return n[i];
        return Math.max( n[i], largestNumberInArray( n, i+1, j ) );
    }


}
