package Exercise_10._08;

public class TaxTest {
    public static void main(String[] args) {
        int[][] bracketsFor2001 = {
            {27050, 65550, 136750, 297350},  // Single filers
            {45200, 109250, 166500, 297350}, // Married filing jointly
            {22600, 54625, 83250, 1486275},  // Married filing separately
            {36250, 93650, 151650, 297350},  // Head of Household
        };

        double[] ratesFor2001 = {0.15, 0.275, 0.305, 0.355, 0.391};

        int[][] bracketsFor2002 = {
                {6000, 27950, 67700, 141250, 307050},    // Single filer
                {12000 , 46700, 112850, 171950, 307050}, // married filing jointly
                {6000, 23350, 56425, 85975, 153525},     // married filing separately
                {10000, 37450, 96700, 156600, 307050}    // head of household
        };

        double[] ratesFor2002 = {0.1, 0.15, 0.27, 0.30, 0.35, 0.386};

        Tax taxFor2001 = new Tax(0, bracketsFor2001, ratesFor2001, 50000);
        printOutPut(taxFor2001);

        System.out.println();

        Tax taxFor2002 = new Tax(0, bracketsFor2002, ratesFor2002, 50000);
        printOutPut(taxFor2002);
    }

    private static void printOutPut(Tax tax) {
        System.out.println("2002 Tax Table");
        System.out.println("taxable\tSingle\tMarried\tMarried\tHead of");
        System.out.println("Income\tSingle\tJoint\tSeparate\ta House");
        for (int taxableIncome = 50000; taxableIncome <= 60000; taxableIncome += 1000) {
            tax.setTaxableIncome(taxableIncome);

            tax.setFilingStatus(0);
            int taxForStatus0 = (int)tax.findTax();

            tax.setFilingStatus(1);
            int taxForStatus1 = (int)tax.findTax();

            tax.setFilingStatus(2);
            int taxForStatus2 = (int)tax.findTax();

            tax.setFilingStatus(3);
            int taxForStatus3 = (int)tax.findTax();

            System.out.println(taxableIncome + "\t" + taxForStatus0 + "\t" +
                    taxForStatus1 + "\t" + taxForStatus2 + "\t\t" +
                    taxForStatus3);
        }    }
}
