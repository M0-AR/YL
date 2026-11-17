package Exercise_19._18;

import java.util.List;

public class Exercise_19_18 {
  public static double sum(List<? extends Number> list) {
    double total = 0;
    for (Number number : list) {
      total += number.doubleValue();
    }
    return total;
  }

  public static double average(List<? extends Number> list) {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("empty list");
    }
    return sum(list) / list.size();
  }

  public static void main(String[] args) {
    List<Integer> integers = List.of(1, 2, 3, 4);
    List<Double> doubles = List.of(1.5, 2.5);
    check(Math.abs(sum(integers) - 10.0) < 0.000001);
    check(Math.abs(average(doubles) - 2.0) < 0.000001);
    System.out.println("Exercise_19_18 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
