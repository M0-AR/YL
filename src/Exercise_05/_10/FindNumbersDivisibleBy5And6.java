package Exercise_05._10;

public class FindNumbersDivisibleBy5And6 {
    public static void main(String[] args) { // g and y
        final int LAST_DESTINATION = 1000;

        int counter = 100;
        int counterForNewLine = 0;
        while (counter < LAST_DESTINATION){
            if (counter % 6 == 0 && counter % 5 == 0){
                System.out.print( counter + " " );
                counterForNewLine++;
                if (counterForNewLine%10 == 0)
                    System.out.println();
            }
            counter++;
        }
    }
}
