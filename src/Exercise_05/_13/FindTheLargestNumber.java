package Exercise_05._13;
/*
(Find the largest n such that n^3 < 12,000) Use a while loop to find the largest
integer n such that n3 is less than 12,000.
*/
public class FindTheLargestNumber { // g
    public static void main(String[] args) {
        int i = 1;

        while (Math.pow( i, 3 ) <= 12000)
            i++;
        System.out.println( "This number is " + i );
    }
}
