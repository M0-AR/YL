package Exercise_16._32;

public class Exercise_16_32 {
  public static String mix(int red, int green, int blue) {
    requireColor(red);
    requireColor(green);
    requireColor(blue);
    return String.format("#%02X%02X%02X", red, green, blue);
  }

  private static void requireColor(int value) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("color out of range");
    }
  }

  public static void main(String[] args) {
    check(mix(255, 0, 0).equals("#FF0000"));
    check(mix(0, 128, 255).equals("#0080FF"));
    check(mix(0, 0, 0).equals("#000000"));
    System.out.println("Exercise_16_32 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
