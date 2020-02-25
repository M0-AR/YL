package Exercise_05._28;

public class DisplayFirstDaysOfEachMonth {
    public static void main(String[] args) { // Github
        java.util.Scanner input = new java.util.Scanner(System.in);

        // Prompt the user to enter input
        System.out.print( "Enter a year: " );
        int year = input.nextInt();

        System.out.print( "Enter the first day of the year: " );
        int daysNumber = input.nextInt();

        int monthConter = 1;
        int numberOfDaysInMonth = 0;

        String[] month;

        // Display calendar for each month
        for (int i = 0; i < 12; i++, monthConter++) {

            // Get the right month
            month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            // Display Result
            switch (daysNumber) {
                case 0: System.out.println(month[i] + " 1, " + year + " is Sunday");break;
                case 1: System.out.println(month[i] + " 1, " + year + " is Monday");break;
                case 2: System.out.println(month[i] + " 1, " + year + " is Tuesday");break;
                case 3: System.out.println(month[i] + " 1, " + year + " is Onsday");break;
                case 4: System.out.println(month[i] + " 1, " + year + " is Thirday");break;
                case 5: System.out.println(month[i] + " 1, " + year + " is Friday");break;
                case 6: System.out.println(month[i] + " 1, " + year + " is Sat");break;
            }

            // Find day's month number in week
            if (monthConter == 2) {
                numberOfDaysInMonth = ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0)? 29: 28);
            } else if (monthConter == 4 || monthConter == 6 ||monthConter == 9 ||monthConter == 11  ){
                numberOfDaysInMonth = 30;
            }else {
                numberOfDaysInMonth = 31;
            }

            // Get the start day for the next month
            daysNumber = (daysNumber + numberOfDaysInMonth) % 7;

        }
    }
}
