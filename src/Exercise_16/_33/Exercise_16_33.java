package Exercise_16._33;

public class Exercise_16_33 {
  public static double monthlyPayment(double principal, double annualRate, int years) {
    int months = years * 12;
    double monthlyRate = annualRate / 1200.0;
    if (monthlyRate == 0) {
      return principal / months;
    }
    return principal * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));
  }

  public static double totalPayment(double principal, double annualRate, int years) {
    return monthlyPayment(principal, annualRate, years) * years * 12;
  }

  public static void main(String[] args) {
    double payment = monthlyPayment(10000, 5, 5);
    check(Math.abs(payment - 188.71) < 0.1);
    check(totalPayment(10000, 5, 5) > 11000);
    check(Math.abs(monthlyPayment(1200, 0, 1) - 100) < 0.000001);
    System.out.println("Exercise_16_33 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
