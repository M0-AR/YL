package Exercise_07._24;

public class couponCollectorsProblem { // github and yl
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

        int numberOfPicks = 0;

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
}
