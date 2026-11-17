package Exercise_20._37;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Exercise_20_37 {
  private static class Cursor {
    private final int value;
    private final int listIndex;
    private final int elementIndex;

    private Cursor(int value, int listIndex, int elementIndex) {
      this.value = value;
      this.listIndex = listIndex;
      this.elementIndex = elementIndex;
    }
  }

  public static List<Integer> merge(int[][] lists) {
    PriorityQueue<Cursor> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
    for (int i = 0; i < lists.length; i++) {
      if (lists[i].length > 0) {
        heap.add(new Cursor(lists[i][0], i, 0));
      }
    }
    List<Integer> result = new ArrayList<>();
    while (!heap.isEmpty()) {
      Cursor cursor = heap.remove();
      result.add(cursor.value);
      int next = cursor.elementIndex + 1;
      if (next < lists[cursor.listIndex].length) {
        heap.add(new Cursor(lists[cursor.listIndex][next], cursor.listIndex, next));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> result = merge(new int[][]{{1, 4, 9}, {2, 3}, {5, 8, 10}});
    check(result.equals(List.of(1, 2, 3, 4, 5, 8, 9, 10)));
    System.out.println("Exercise_20_37 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
