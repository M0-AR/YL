package Exercise_06._32;
/*
(Game: chance of winning at craps) Revise Exercise 6.30 to run it 10,000 times
and display the number of winning games.
*/
public class ChanceOfWinningAtCraps {
    public static void main(String[] args) {
        int currentDices, winCount = 0;

        for (int i = 0; i < 10000; i++) {
            currentDices = getDice();

            if (currentDices == 7 || currentDices == 11) {
                winCount++;
            } else if (currentDices == 2 || currentDices == 3 || currentDices == 12) {
//                System.out.println("You lose");
            } else {
                int previousDices = getDice();

                do {
                    currentDices = getDice();
                }while (currentDices != 7 && currentDices != previousDices);

                if (currentDices == 7){
//                    System.out.println("You lose");
                }
                else {
                    winCount++;
                }
            }


        }

        System.out.println(winCount);

    }


    public static int getDice() {
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;

//        System.out.println( "You rolled " + dice1 + " + " + dice2
//                + " = " + (dice1 + dice2) );

        return dice1 + dice2;

    }
}
