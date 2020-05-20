package Exercise_07._29;

public class pickFourCards { // github and yl
    /** Main method */
    public static void main(String[] args) {
        // The numbers of cards is 52
        final int NUMBER_OF_CARDS = 52;

        // The type of the card
        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        int sum = 0; // Sum of two ranks should be 24
        int countNumbersOfPicks = 0; // Count the numbers of picks to get 24

        int count; // To pick 4 cards from ranks
        while (sum != 24){
            count = 0;
            sum = 0;
            while (count < 4){
                int randomNumber = (int)(Math.random() * NUMBER_OF_CARDS);
                sum += ranks[randomNumber/4];
                count++;
            }
            countNumbersOfPicks++;
        }

        // Display result
        System.out.println(countNumbersOfPicks + " picks to get sum of " + sum);




    }
}
