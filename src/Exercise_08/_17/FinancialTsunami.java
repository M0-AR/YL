package Exercise_08._17;

import java.util.Scanner;


public class FinancialTsunami { // Wrong outPut // check how to print plural and singular and 3 first then 1
    public static void main(String[] args) {
        // Create a scanner
        Scanner input = new Scanner( System.in );

        // Prompt the user to enter numbers of banks and limit
        System.out.print( "Enter numbers of banks and limit: " );
        int numbersOfBanks;// = input.nextInt();
        int minimumTotalAssets;// = input.nextInt();

        numbersOfBanks = 5;
        minimumTotalAssets = 201;




        double[] bankBalances = {25, 125,175,75,181};
        // First number indicates the number of banks that
        // borrowed money from current bank.
        double[][] borrowers = {{2, 1, 100.5, 4, 320.5},
                                {2, 2, 40, 3, 85},
                                {2, 0, 125, 3, 75},
                                {1, 0, 125},
                                {1, 2, 125}};

        bankBalances = new double[numbersOfBanks];
        borrowers = new double[numbersOfBanks][numbersOfBanks*2 + 1];
        for (int i = 0; i < numbersOfBanks; i++) {
            bankBalances[0] = input.nextInt();
            int numberOfBorrowedBanks = input.nextInt();
            borrowers[i][0] = numberOfBorrowedBanks;
            for (int j = 1; j < numberOfBorrowedBanks*2; j+=2) {
                int indexOfBorrowedBank = input.nextInt();
                borrowers[i][j] = indexOfBorrowedBank;
                double amountOfBorrowedBank = input.nextDouble();
                borrowers[i][j+1] = amountOfBorrowedBank;
            }
        }

        int[] unsafeBank = decideUnsafeBank(bankBalances, borrowers, minimumTotalAssets);




        System.out.print( "Unsafe banks are " );
        for (int i = unsafeBank.length-1; i >= 0 ; i--) {
            if (unsafeBank[i] == 1)
                System.out.print( i + " " );
        }
    }

    public static int[] decideUnsafeBank(double[] bankBalances, double[][] borrowers, int minimumTotalAssets) {
        int[] unsafeBank = new int[borrowers.length];
        double totalAssetsOfBank;

        for (int i = 0; i < borrowers.length; i++) {

            totalAssetsOfBank = getTotalAssetsOfBank(bankBalances[i], borrowers, i );

            if ((minimumTotalAssets > totalAssetsOfBank) && unsafeBank[i] != 1) {
                unsafeBank[i] = 1;
                borrowers = setUnsafeBankToZero(borrowers, i);

                i = -1;
            }
        }

        return unsafeBank;
    }

    public static double getTotalAssetsOfBank(double bankBalance, double[][] borrowers, int indexOfBank) {
        final int INDEX_OF_BORROWERS_BANK_LOAN = 2;

        double totalAssetsOfBank = bankBalance;

        int j = INDEX_OF_BORROWERS_BANK_LOAN;
        while (j < borrowers[indexOfBank].length) {
            totalAssetsOfBank += borrowers[indexOfBank][j];
            j += INDEX_OF_BORROWERS_BANK_LOAN;
        }

        return totalAssetsOfBank;
    }

    public static double[][] setUnsafeBankToZero(double[][] borrowers, int unsafeBankNumber) {
        double[][] b = borrowers;
        for (int i = 0; i < b.length; i++) {
            for (int j = 1; j < b[i].length; j+=2) {
                if (b[i][j] == unsafeBankNumber)
                    b[i][j+1] = 0;
            }
        }
        return b;
    }



}
/*
25 2 1 100,5 4 320,5
125 2 2 40 3 85
175 2 0 125 3 75
75 1 0 125
181 1 2 125
 */