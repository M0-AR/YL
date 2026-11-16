package Exercise_08._17;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinancialTsunami {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of banks and the safety limit: ");
        int numberOfBanks = input.nextInt();
        double limit = input.nextDouble();

        double[] balances = new double[numberOfBanks];
        List<List<Loan>> loans = new ArrayList<>();

        for (int i = 0; i < numberOfBanks; i++) {
            balances[i] = input.nextDouble();
            int numberOfLoans = input.nextInt();
            List<Loan> bankLoans = new ArrayList<>();
            for (int j = 0; j < numberOfLoans; j++) {
                int borrower = input.nextInt();
                double amount = input.nextDouble();
                bankLoans.add(new Loan(borrower, amount));
            }
            loans.add(bankLoans);
        }

        boolean[] unsafe = new boolean[numberOfBanks];
        List<Integer> unsafeBanks = new ArrayList<>();
        boolean changed = true;

        while (changed) {
            changed = false;
            for (int i = 0; i < numberOfBanks; i++) {
                if (unsafe[i]) {
                    continue;
                }

                double assets = balances[i];
                for (Loan loan : loans.get(i)) {
                    if (!unsafe[loan.borrower]) {
                        assets += loan.amount;
                    }
                }

                if (assets < limit) {
                    unsafe[i] = true;
                    unsafeBanks.add(i);
                    changed = true;
                }
            }
        }

        System.out.print("Unsafe banks are");
        for (int bank : unsafeBanks) {
            System.out.print(" " + bank);
        }
        System.out.println();

        input.close();
    }

    private static class Loan {
        private final int borrower;
        private final double amount;

        Loan(int borrower, double amount) {
            this.borrower = borrower;
            this.amount = amount;
        }
    }
}
