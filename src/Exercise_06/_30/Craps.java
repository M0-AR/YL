package Exercise_06._30;

public class Craps {
    public static void main(String[] args) { // con

        int dice1 = 0, dice2 = 0;
        int previousDices = 0, currentDices;
        int count = 1;
        while (true) {
            previousDices = dice1 + dice2;
            dice1 = (int) (Math.random() * 6) + 1;
            dice2 = (int) (Math.random() * 6) + 1;

            System.out.println( "You rolled " + dice1 + " + " + dice2
                    + " = " + (dice1 + dice2) );
            if (isWin( dice1, dice2 ) && count < 2) {
                System.out.println( "You win" );
                break;
            }

            if (isLose( dice1, dice2 ) && count < 2) {
                System.out.println( "You win" );
                break;
            }

//            if ()


            count++;

        }
    }


    static boolean isWin(int dice1, int dice2) {
        if (dice1 + dice2 == 7 || dice1 + dice2 == 11)
            return true;
        return false;
    }

    static boolean isLose(int dice1, int dice2) {
        if (dice1 + dice2 == 2 || dice1 + dice2 == 3 || dice1 + dice2 == 12)
            return true;
        return false;
    }
}
