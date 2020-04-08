package Exercise_06._30;
/*
(Game: craps) Craps is a popular dice game played in casinos. Write a program
to play a variation of the game, as follows:
Roll two dice. Each die has six faces representing values 1, 2, …, and 6, respectively.
Check the sum of the two dice. If the sum is 2, 3, or 12 (called craps), you
lose; if the sum is 7 or 11 (called natural), you win; if the sum is another value
(i.e., 4, 5, 6, 8, 9, or 10), a point is established. Continue to roll the dice until either
a 7 or the same point value is rolled. If 7 is rolled, you lose. Otherwise, you win.
Your program acts as a single player.
*/
public class Craps {
    public static void main(String[] args) {
        int previousDices, currentDices;

        currentDices = getDice();

        if (currentDices == 7 || currentDices == 11) {
            System.out.println("You win");
            System.exit(1);
        }
        else if (currentDices == 2 || currentDices == 3 || currentDices == 12) {
            System.out.println("You lose");
            System.exit(2);
        }

        previousDices = currentDices;
        System.out.println("point is " + previousDices);
        while (true) {
            currentDices = getDice();
            if (currentDices == 7){
                System.out.println("You lose");
                break;
            }else if (currentDices == previousDices){
                System.out.println("You win");
                break;
            }
        }
    }


    public static int getDice() {
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;

        System.out.println( "You rolled " + dice1 + " + " + dice2
                + " = " + (dice1 + dice2) );

        return dice1 + dice2;

    }
}
