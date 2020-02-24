package Exercise_05._27;
/*
(Display leap years) Write a program that displays all the leap years, ten per line,
from 101 to 2100, separated by exactly one space. Also display the number of
leap years in this period.
*/
public class DisplayLeapYears {
    public static void main(String[] args) {
        int year = 101;
        int line = 0;
        while (year <= 2100){
            if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                line++;
                System.out.print( year + ((line % 10 == 0)? "\n": " ") );
            }
            year++;
        }

    }
}
