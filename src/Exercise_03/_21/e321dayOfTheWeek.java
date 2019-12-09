package Exercise_03._21;
/*
(Science: day of the week) Zeller’s congruence is an algorithm developed by
Christian Zeller to calculate the day of the week.
*/
import java.util.Scanner;

public class e321dayOfTheWeek { // done
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);

        //1
        int dayOfWeek, dayOfMonth, month, year;

        //2
//        Prompt the user to enter a year
        System.out.print("Enter year: (e.g., 2012): ");
        year = input.nextInt();
//        Prompt the user to enter a month
        System.out.print("Enter month: 1-12: ");
        month = input.nextInt();
//        Prompt the user to enter a day of the month
        System.out.print("Enter the day of the month: 1-31: ");
        dayOfMonth = input.nextInt();

        //3
//        Convert January and February to months 13 and 14 of the previoous year
        if (month == 1 || month == 0){
            month = (month==1)?13:14;
            year--;
        }

//        Compute
         dayOfWeek = (dayOfMonth + (26*(month+1)/10 + (year % 100) + (year % 100)/4 + (year/100)/4 + 5*(year/100))) %7;

        //4
//        Display result
        System.out.println("Day of the week is ");

        switch (dayOfWeek)
        {
            case 0: System.out.println("Saturday"); break;
            case 1: System.out.println("Sunday"); break;
            case 2: System.out.println("Monday"); break;
            case 3: System.out.println("Tuesday"); break;
            case 4: System.out.println("Wednesday"); break;
            case 5: System.out.println("Thursday"); break;
            case 6: System.out.println("Friday");

        }

    }
}
