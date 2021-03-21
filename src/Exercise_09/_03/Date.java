package Exercise_09._03;

public class Date {
    public static void main(String[] args) {
        final int NUMBER_OF_SET_ELAPSED_TIME = 8;

        long elapsedTime = 100000;
        for (int i = 0; i < NUMBER_OF_SET_ELAPSED_TIME; i++) {
            java.util.Date date = new java.util.Date(elapsedTime);
            System.out.println(date.toString());
            elapsedTime = addZeroToTheRightOfNumber(elapsedTime);
            System.out.println(elapsedTime);
        }
    }

    private static long addZeroToTheRightOfNumber(long elapsedTime) {
        String addZeroToTheRight = String.valueOf( elapsedTime );
        addZeroToTheRight += "0";
        return Long.parseLong( addZeroToTheRight );
    }
}
