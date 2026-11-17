package Exercise_27._23;

import java.util.HashMap;
import java.util.Map;

public class Exercise_27_23 {
  public static void main(String[] args) {
    Map<String, Integer> scores = new HashMap<>();
    scores.put("Ada", 95);
    scores.put("Bob", 88);
    int total = 0;
    for (Map.Entry<String, Integer> entry : scores.entrySet()) {
      total += entry.getValue();
    }
    check(scores.entrySet().size() == 2);
    check(total == 183);
    check(scores.entrySet().contains(Map.entry("Ada", 95)));
    System.out.println("Exercise_27_23 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
