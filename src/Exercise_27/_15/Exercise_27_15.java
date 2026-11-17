package Exercise_27._15;

public class Exercise_27_15 {
  public static int hash(String text) {
    int hash = 0;
    for (int i = 0; i < text.length(); i++) {
      hash = 31 * hash + text.charAt(i);
    }
    return hash;
  }

  public static void main(String[] args) {
    check(hash("abc") == 96354);
    check(hash("") == 0);
    check(hash("abc") != hash("abd"));
    System.out.println("Exercise_27_15 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
