package Exercise_16._34;

public class Exercise_16_34 {
  public static double futureValue(
      double principal, double annualRate, int years, int compoundsPerYear) {
    if (principal < 0 || annualRate < 0 || years < 0 || compoundsPerYear <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    return principal * Math.pow(
        1 + annualRate / 100.0 / compoundsPerYear,
        compoundsPerYear * years);
  }

  public static void main(String[] args) {
    double value = futureValue(1000, 5, 10, 12);
    check(Math.abs(value - 1647.01) < 0.1);
    check(Math.abs(futureValue(1000, 0, 10, 12) - 1000) < 0.000001);
    System.out.println("Exercise_16_34 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
