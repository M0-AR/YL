package Exercise_08._12;

public class ComputeTax {
    public static void main(String[] args) { // check answer yl and github
        // Create an array that has six rates
        double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};


        // Create an 2D array that has brackets for each rate
        // For all the filing statuses
        int[][]  brackets = {
                {8350, 33950, 82250, 171550, 372950},   // Single filer
                {16700, 67900, 137050, 20885, 372950},  // Married jointly
                                                        // -or qualifying widow(er)
                {8350, 33950, 68525, 104425, 186475},   // Married separately
                {11950, 45500, 117450, 190200, 372850}  // Head of household

        };

        // Invoke method
        double tax = computeTax(brackets, rates, 400000, 0);

        System.out.println(tax);

    }

    public static double computeTax(int[][] brackets, double[] rates, int income, int status) {
        int index = 0; // To store index of brackets according to income 
        for (int i = 0; i < brackets[status].length; i++) {
            if (income < brackets[status][i])
                break;

            index = i;
        }
        
        double tax = brackets[status][0] * rates[0]; // Compute tax
        for (int i = 1; i < ((index == 4) ? 6 : index); i++) {
            if (i == 5) {
                tax += (income - brackets[status][4]) * rates[5];
                break;
            }
            tax += (brackets[status][i] - brackets[0][0]) * rates[i];
        }

        return tax;
    }
}
