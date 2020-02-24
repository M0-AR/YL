package Exercise_05._28;

public class DisplayFirstDaysOfEachMonth { // Need test, Think done
    public static void main(String[] args) { // Github and Y
        int year = 2013;
        int daysNumber = 2;
        int monthConter = 1;
        int numberOfDaysInMonth = 0;
        String month = null;



        for (int i = 1; i <= 12; i++, monthConter++) {

            // Get the right number for year
            switch (monthConter) {
                case 1:month = "Jan";break;
                case 2:month = "Fab";break;
                case 3:month = "Mars";break;
                case 4:month = "April";break;
                case 5:month = "May";break;
                case 6:month = "Juni";break;
                case 7:month = "Juile";break;
                case 8:month = "Aug";break;
                case 9:month = "Sep";break;
                case 10:month = "Oct";break;
                case 11:month = "Nov";break;
                case 12:month = "Dec";break;
            }

            // Display Result
            switch (daysNumber) {
                case 0: System.out.println(month + " 1, " + year + " is Sunday");break;
                case 1: System.out.println(month + " 1, " + year + " is Monday");break;
                case 2: System.out.println(month + " 1, " + year + " is Tuesday");break;
                case 3: System.out.println(month + " 1, " + year + " is Onsday");break;
                case 4: System.out.println(month + " 1, " + year + " is Thirday");break;
                case 5: System.out.println(month + " 1, " + year + " is Friday");break;
                case 6: System.out.println(month + " 1, " + year + " is Sat");break;
            }
            // Find day's number in week
            if (monthConter == 2) {
                numberOfDaysInMonth = ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0)? 29: 28);
                daysNumber = (daysNumber + numberOfDaysInMonth) % 7;
            } else if (monthConter == 4 || monthConter == 6 ||monthConter == 9 ||monthConter == 11  ){
                numberOfDaysInMonth = 30;
                daysNumber = (daysNumber + numberOfDaysInMonth) % 7;
            }else {
                numberOfDaysInMonth = 31;
                daysNumber = (daysNumber + numberOfDaysInMonth) % 7;
            }


        }
    }
}
