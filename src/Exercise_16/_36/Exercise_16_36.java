package Exercise_16._36;

public class Exercise_16_36 {
  public static double celsiusToFahrenheit(double celsius) {
    return celsius * 9 / 5 + 32;
  }

  public static double fahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - 32) * 5 / 9;
  }

  public static void main(String[] args) {
    check(Math.abs(celsiusToFahrenheit(0) - 32) < 0.000001);
    check(Math.abs(celsiusToFahrenheit(100) - 212) < 0.000001);
    check(Math.abs(fahrenheitToCelsius(32)) < 0.000001);
    check(Math.abs(fahrenheitToCelsius(212) - 100) < 0.000001);
    System.out.println("Exercise_16_36 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
