package Exercise_06._25;

public class ConvertToHoursMinutesSeconds { // github
    public static void main(String[] args) {
        System.out.println(convertMillis( 555550000 ));
    }

    public static String convertMillis(long millis){
        long totalSecond = millis / 1000;
        long currentSecond = totalSecond % 60;

        long totalMin =  totalSecond / 60;
        long currentMin = totalMin % 60;

        long totalHours = totalMin / 60;
//        long currentHours = totalHours % 60;


        String s = totalHours + ":"+ currentMin+":" + currentSecond;
        return s;

    }
}
