package Exercise_07._24;
/*********************************************************************************
 * (Simulation: coupon collector’s problem) Coupon collector is a classic         *
 * statistics problem with many practical applications. The problem is to pick    *
 * objects from a set of objects repeatedly and find out how many picks are       *
 * needed for all the objects to be picked at least once. A variation of the      *
 * problem is to pick cards from a shuffled deck of 52 cards repeatedly and find  *
 * out how many picks are needed before you see one of each suit. Assume a picked *
 * card is placed back in the deck before picking another. Write a program to     *
 * simulate the number of picks needed to get four cards from each suit and       *
 * display the four cards picked (it is possible acard may be picked twice).      *
 *********************************************************************************/
public class couponCollectorsProblem {
    /** Main method */
    public static void main(String[] args) {
        // The numbers of cards is 52
        final int NUMBER_OF_CARDS = 52;

        // The type of the card
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8",
                            "9", "10", "Jack", "Queen", "King"};

        // Help to pick 4 different type of cards
        boolean[] found = new boolean[4];

        // Shuffle the cards
        shuffleCards( ranks );

        // Count the number of picks
        int numberOfPicks = 0;

        // Count occurrence in each suit
        int count = 0;
        while (count < 4){
            numberOfPicks++;

            // Random number between 0-51
            int randomNumber = (int)(Math.random() * NUMBER_OF_CARDS);

            if (!found[randomNumber/13]){
                found[randomNumber/13] = true;
                count++;

                // Display random card that not being displayed before
                String suit = suits[randomNumber/13];
                String rank = ranks[randomNumber%13];
                System.out.println(rank + " of " + suit);
            }
        }

        System.out.println("Number of picks: " + numberOfPicks);
    }


    /** Shuffle a deck of cards */
    public static void shuffleCards(String[] deck){
        for (int i = 0; i < deck.length ; i++) {
            // Generate an index randomly
            int index = pickCards(deck);
            String temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
    }

    /** Pick card randomly for a deck */
    public static int pickCards(String[] deck) {
        return (int)(Math.random() * deck.length);
    }
}
