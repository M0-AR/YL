package Exercise_19._17;

public class Exercise_19_17 {
  public static double sum(Number[] numbers) {
    double total = 0;
    for (Number number : numbers) {
      total += number.doubleValue();
    }
    return total;
  }

  public static void main(String[] args) {
    check(Math.abs(sum(new Number[]{1, 2.5, 3}) - 6.5) < 0.000001);
    check(Math.abs(sum(new Number[]{1.0f, 2L, 3.5d}) - 6.5) < 0.000001);
    System.out.println("Exercise_19_17 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
