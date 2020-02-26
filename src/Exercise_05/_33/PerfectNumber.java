package Exercise_05._33;

public class PerfectNumber {
    public static void main(String[] args) { // github
        int count = 6;
        int number = 6;
        int positiveInteger = 0;
        while (count < 10000) {
            number = count;
            positiveInteger = 0;
            while (number % 2 == 0) {
                number /= 2;
                if (number % 2 != 0) {
                    while (number != 0)
                        positiveInteger += number--;
                    if (positiveInteger == count){
                        System.out.println( positiveInteger );
                    }
                    number++;
                }
            }

            count++;
        }

    }
}
