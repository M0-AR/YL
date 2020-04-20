package Exercise_07._07;

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
