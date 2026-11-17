package Exercise_19._21;

public class Exercise_19_21 {
  public static double[][] add(Number[][] first, Number[][] second) {
    requireSameShape(first, second);
    double[][] result = new double[first.length][first[0].length];
    for (int i = 0; i < first.length; i++) {
      for (int j = 0; j < first[i].length; j++) {
        result[i][j] = first[i][j].doubleValue() + second[i][j].doubleValue();
      }
    }
    return result;
  }

  public static double[][] multiply(Number[][] first, Number[][] second) {
    if (first[0].length != second.length) {
      throw new IllegalArgumentException("incompatible shapes");
    }
    double[][] result = new double[first.length][second[0].length];
    for (int i = 0; i < first.length; i++) {
      for (int j = 0; j < second[0].length; j++) {
        for (int k = 0; k < second.length; k++) {
          result[i][j] += first[i][k].doubleValue() * second[k][j].doubleValue();
        }
      }
    }
    return result;
  }

  private static void requireSameShape(Number[][] first, Number[][] second) {
    if (first.length != second.length || first[0].length != second[0].length) {
      throw new IllegalArgumentException("incompatible shapes");
    }
  }

  public static void main(String[] args) {
    Number[][] first = {{1, 2}, {3, 4}};
    Number[][] second = {{5, 6}, {7, 8}};
    double[][] sum = add(first, second);
    double[][] product = multiply(first, second);
    check(sum[1][1] == 12.0);
    check(product[0][0] == 19.0);
    check(product[1][1] == 50.0);
    System.out.println("Exercise_19_21 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
