package Exercise_16._35;

public class Exercise_16_35 {
  public static double bmi(double weightKg, double heightM) {
    if (weightKg <= 0 || heightM <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    return weightKg / (heightM * heightM);
  }

  public static String category(double bmi) {
    if (bmi < 18.5) {
      return "Underweight";
    }
    if (bmi < 25) {
      return "Normal";
    }
    if (bmi < 30) {
      return "Overweight";
    }
    return "Obese";
  }

  public static void main(String[] args) {
    check(Math.abs(bmi(70, 1.75) - 22.86) < 0.01);
    check(category(17).equals("Underweight"));
    check(category(22).equals("Normal"));
    check(category(27).equals("Overweight"));
    check(category(31).equals("Obese"));
    System.out.println("Exercise_16_35 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
