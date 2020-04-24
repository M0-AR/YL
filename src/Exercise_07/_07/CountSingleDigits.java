package Exercise_07._07;
/***********************************************************************************
 * (Count single digits) Write a program that generates 100 random integers between *
 * 0 and 9 and displays the count for each number. (Hint: Use an array of ten       *
 * integers, say counts, to store the counts for the number of 0s, 1s, . . . , 9s.) *
 ************************************************************************************/
public class CountSingleDigits {
    public static void main(String[] args) {
        int[] n = new int[10];


        // To count how much every number being displayed throw Math.random
        for (int i = 0; i < 100 ; i++) {
            n[(int)(Math.random() * 10)]++;
        }

        // Display result
        for (int i = 0; i < n.length ; i++) {
            System.out.println("The count of "+  i + " is " + n[i] );
        }


    }
}
