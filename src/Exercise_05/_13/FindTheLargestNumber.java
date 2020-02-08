package Exercise_05._13;

public class FindTheLargestNumber { // g
    public static void main(String[] args) {
        int i = 1;

        while (i * i * i <= 12000)
            i++;
        System.out.println( "This number is " + i );
    }
}
