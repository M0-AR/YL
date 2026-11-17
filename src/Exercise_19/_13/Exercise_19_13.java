package Exercise_19._13;

public class Exercise_19_13 {
  public static <E extends Comparable<E>> E max(E[] list) {
    if (list.length == 0) {
      throw new IllegalArgumentException("empty list");
    }
    E result = list[0];
    for (int i = 1; i < list.length; i++) {
      if (list[i].compareTo(result) > 0) {
        result = list[i];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    check(max(new Integer[]{3, 9, 2, 7}).equals(9));
    check(max(new String[]{"pear", "apple", "zoo"}).equals("zoo"));
    System.out.println("Exercise_19_13 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
