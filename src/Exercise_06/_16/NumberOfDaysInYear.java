package Exercise_06._16;

public class NumberOfDaysInYear {
    public static void main(String[] args) {//github and y
        for (int i = 2000; i <= 2020; i++)
            System.out.println( "Number of days in " + i + " is " + numberOfDaysInAYear( i ) + " days" );

    }

    public static int numberOfDaysInAYear(int year) {
        return (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? 366 : 365);
    }
}
