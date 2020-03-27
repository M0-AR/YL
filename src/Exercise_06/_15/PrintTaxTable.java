package Exercise_06._15;
/*
(Financial application: print a tax table) Listing 3.5 gives a program to compute
tax. Write a method for computing tax using the following header:
public static double computeTax(int status, double taxableIncome)
Use this method to write a program that prints a tax table for taxable income from
$50,000 to $60,000 with intervals of $50 for all the following statuses:
Taxable      Single      Married Joint       Married        Head of
Income                   or Qualifying       Separate       a House
                         Widow(er)
------------------------------------------------------------------------
50000        8688        6665                8688           7353
50050        8700        6673                8700           7365
...
59950        11175       8158                11175          9840
60000        11188       8165                11188          9853
Hint: round the tax into integers using Math.round (i.e., Math
.round(computeTax(status, taxableIncome)).
*/
public class PrintTaxTable {// s.91 y and github
    public static void main(String[] args) {
        System.out.println(Math.round( computeTax( 0, 50000 )) );
        System.out.println(computeTax( 1, 50000 ));
        System.out.println(computeTax( 2, 50000 ));
        System.out.println(computeTax( 3, 50000 ));
    }

    public static double computeTax(int status, double taxableIncome){
        double percent = 0;
        switch (status){
            case 0: percent = ((taxableIncome <= 8350)? 0.1: (taxableIncome <= 33950)?  0.15
                    :(taxableIncome <= 82250)? 0.25: (taxableIncome <= 171550)? 0.28
                    :(taxableIncome <= 372950)? 0.33: 0.35) ;break;

            case 1: percent = ((taxableIncome <= 16700)? 0.1: (taxableIncome <= 67900)?  0.15
                    :(taxableIncome <= 137050)? 0.25: (taxableIncome <= 208850)? 0.28
                    :(taxableIncome <= 372950)? 0.33: 0.35) ;break;

            case 2: percent = ((taxableIncome <= 8350)? 0.1: (taxableIncome <= 33950)?  0.15
                    :(taxableIncome <= 68525)? 0.25: (taxableIncome <= 104425)? 0.28
                    :(taxableIncome <= 186475)? 0.33: 0.35) ;break;

            case 3: percent = ((taxableIncome <= 11950)? 0.1: (taxableIncome <= 45500)?  0.15
                    :(taxableIncome <= 117450)? 0.25: (taxableIncome <= 190200)? 0.28
                    :(taxableIncome <= 372950)? 0.33: 0.35) ;break;
        }

        return percent * taxableIncome;
    }
}
