package Exercise_10._08;
        // UML
/***************************
 *           Tax           *
 *-------------------------*
 * +SINGLE_FILER: int      *
 * +MARRIED_JOINTLY: int   *
 * +MARRIED_JOINTLY: int   *
 * +MARRIED_JOINTLY: int   *
 * -filingStatus: int      *
 * -brackets: int[][]      *
 * -rates: double[]        *
 * -taxableIncome: double  *
 * ------------------------*
 * +findTax(): double       *
 **************************/
public class Tax {
    public final static int SINGLE_FILER = 1;
    public final static int MARRIED_JOINTLY = 2;
    public final static int MARRIED_SEPARATELY = 3;
    public final static int HEAD_OF_HOUSEHOLD = 4;

    private int filingStatus = SINGLE_FILER;

    private int[][] brackets = {
        {27050, 65550, 136750, 297350},  // Single filers
        {45200, 109250, 166500, 297350}, // Married filing jointly
        {22600, 54625, 83250, 1486275},  // Married filing separately
        {36250, 93650, 151650, 297350},  // Head of Household
    };

    private double[] rates = {0.15, 0.275, 0.305, 0.355, 0.391};
    private double taxableIncome = 100000;

    public Tax() {
    }

    public Tax(int filingStatus, int[][] brackets, double[] rates,
        double taxableIncome) {
        this.filingStatus = filingStatus;
        this.brackets = brackets;
        this.rates = rates;
        this.taxableIncome = taxableIncome;
    }

    public int getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(int filingStatus) {
        this.filingStatus = filingStatus;
    }

    public int[][] getBrackets() {
        return brackets;
    }

    public void setBrackets(int[][] brackets) {
        this.brackets = brackets;
    }

    public double[] getRates() {
        return rates;
    }

    public void setRates(double[] rates) {
        this.rates = rates;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double findTax() {
        double tax = 0;

        // Compute tax in the first bracket
        if (taxableIncome <= brackets[filingStatus][0])
            return tax = taxableIncome * rates[0];
        else
            tax = brackets[filingStatus][0] * rates[0];

        int i;

        // Compute tax in the possible 2nd, 3nd, 4th, and 5th brackets
        for (i = 1; i < brackets[0].length; i++) {
            if (taxableIncome <= brackets[filingStatus][i])
                tax += (brackets[filingStatus][i] - brackets[filingStatus][i - 1]) *
                        rates[i];
            else {
                tax += (taxableIncome - brackets[filingStatus][i - 1]) * rates[i];
                break;
            }
        }

        // Compute tax in the possible last bracket
        if (i == brackets[0].length && taxableIncome > brackets[filingStatus][i - 1])
            tax += (taxableIncome - brackets[filingStatus][i - 1] * rates[i]);

        return tax;
    }


}
