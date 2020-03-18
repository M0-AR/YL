package Exercise_06._07;

public class ComputeFutureInvestmentValue {
    public static void main(String[] args) { // My answer is correct but how can I use years parameter github
        int years = 30;
        double amount = 1000;
        System.out.printf( "%s %20s\n", "Years", "Future Value" );
        for (int i = 1; i <= years  ; i++) {
            amount = futureInvestmentValue( amount, 9, 30 );
            System.out.printf( "%d %15s %.2f\n", i," ", amount);
        }


    }

    public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate, int years){
        for (int i = 1; i <= 12 ; i++) {
            investmentAmount *= (1 + monthlyInterestRate/1200);
        }
        return investmentAmount;
    }
}
