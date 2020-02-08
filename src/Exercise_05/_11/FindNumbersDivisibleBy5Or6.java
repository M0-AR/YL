package Exercise_05._11;
/*
(Find numbers divisible by 5 or 6, but not both) Write a program that displays
all the numbers from 100 to 200, ten per line, that are divisible by 5 or 6, but not
both. Numbers are separated by exactly one space.
*/
public class FindNumbersDivisibleBy5Or6 {
    public static void main(String[] args) {
        final int LAST_DESTINATION = 200;

        for (int i = 100, countForNewLine = 0; i < LAST_DESTINATION ; i++) {
            if (i % 5== 0 || i % 6 == 0){
                System.out.print( ((++countForNewLine % 10 != 0) ? i + " " : i + "\n") );

            }
        }
    }
}
