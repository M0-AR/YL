package Exercise_05._27;

public class DisplayLeapYears {
    public static void main(String[] args) { // github
        int year = 101;
        int line = 0;
        while (year <= 2100){
            if((year % 4 == 0 && year % 100 == 0) || year % 400 == 0){
                System.out.print( year + " " );
                line++;
            }

            if (line % 10 ==0){
                System.out.println();
                line++;
            }


            year++;
        }

    }
}
