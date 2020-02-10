package Exercise_05._20;

public class DisplayPrimeNumbers {
    public static void main(String[] args) { // g and y

        int primeNumbers = 2;
        System.out.print(primeNumbers + " " + (primeNumbers+1) + " ");
        while (primeNumbers < 1000) {
            if (primeNumbers % 2 != 0 && primeNumbers % 3 != 0)
                System.out.print(primeNumbers +  " " );
            primeNumbers++;
        }
    }
}
