package Exercise_06._16;
/*
(Number of days in a year) Write a method that returns the number of days in a
year using the following header:
public static int numberOfDaysInAYear(int year)
Write a test program that displays the number of days in year from 2000 to 2020.
*/
public class NumberOfDaysInYear {
    public static void main(String[] args) {
        for (int i = 2000; i <= 2020; i++)
            System.out.println( "Number of days in " + i + " is " + numberOfDaysInAYear( i ) + " days" );

    }

    public static int numberOfDaysInAYear(int year) {
        return isLeapYear( year ) ? 366 : 365;
    }

    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
