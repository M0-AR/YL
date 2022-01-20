package Exercise_17._06;

import java.io.*;

public class StoreLoanObjects { // github and yl // todo remember Serializable id L
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        // Default information
        double annualInterestRate = 1;
        int numberOfYears = 1;
        double loanAmount = 1001;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream( "Exercise17_06.dat" ) )
        ) {
            // Create five object and store them in Exercise17_06.dat
            for (int i = 0; i < 5; i++) {
                Loan loan = new Loan
                        ( annualInterestRate++, numberOfYears++, loanAmount++ );
                outputStream.writeObject( loan );
            }
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream( "Exercise17_06.dat" ) )
        ){
            while (true){
                Loan loan = (Loan) objectInputStream.readObject();
                System.out.println(loan);
                System.out.printf("Total loan amount: $%.2f\n",
                        loan.getTotalPayment());
                System.out.println();
            }
        } catch (EOFException e) {
            // Use EOFException to end the loop
        }


    }
}

