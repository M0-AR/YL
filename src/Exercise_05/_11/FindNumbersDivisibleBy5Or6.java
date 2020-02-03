package Exercise_05._11;

public class FindNumbersDivisibleBy5Or6 {
    public static void main(String[] args) {
        final int LAST_DESTINATION = 1000;

        for (int i = 100, countForNewLine = 0; i < LAST_DESTINATION ; i++) {
            if (i % 5== 0 || i % 6 == 6){
                System.out.print(i + " ");
                countForNewLine++;
                if (countForNewLine % 10 == 0)
                    System.out.println();
            }
        }
    }
}
