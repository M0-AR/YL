package Exercise_07._29;
/*********************************************************************************
 * (Game: pick four cards) Write a program that picks four cards from a deck of 52*
 * cards and computes their sum. An Ace, King, Queen, and Jack represent 1, 13,   *
 * 12, and 11, respectively. Your program should display the number of picks that *
 * yields the sum of 24.                                                          *
 *********************************************************************************/
public class pickFourCards {
    /** Solution 1: */
    /** Main method */
    /*public static void main(String[] args) {
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
    }*/


    /** Solution 2: */
    /** Main method */
    public static void main(String[] args) {
        int[] deck; // Create array

        do {
            // Initialize deck
            deck = new int[52];

            // Pick four cards
            pickFourCards(deck);

        }while (sum(deck) != 24);

        // Display the number of picks that yields the sum 24
        print(deck);
    }


    /** Randomly picks four cards */
    public static void pickFourCards(int[] deck){
        for (int i = 0; i < 4; i++)
            deck[(int)(Math.random() * 52)]++;
    }


    /** Computes the sum of cards picked */
    public static int sum(int[] deck){
        int sum = 0;

        for (int i = 0; i < deck.length; i++)
            sum += ((i % 13) + 1) * deck[i];

        return sum;
    }


    /** Displays the number of picks */
    public static void print(int[] deck){
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
                "10", "Jack", "Queen", "King"};

        System.out.print("The number of picks that yields thu sum of 24: ");
        for (int i = 0; i < deck.length; i++){
            while (deck[i] > 0){
                System.out.print(ranks[i % 13]  + " ");
                deck[i]--;
            }
        }
        System.out.println();
    }
}
