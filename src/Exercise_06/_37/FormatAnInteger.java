package Exercise_06._37;

public class FormatAnInteger {
    public static void main(String[] args) { // github
        System.out.println(format( 34, 1 ));
    }

    public static String format(int number, int width){
        String s = number+"";

        if (width <= s.length())
            return s;

        int n =  width - s.length();
        for (int i = 0; i < n; i++) {
            s = "0" + s;
        }

        return s;
    }
}
