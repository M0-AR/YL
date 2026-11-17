package Exercise_23._22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Exercise_23_22 {
  private static class Cursor {
    private final int value;
    private final int chunk;
    private final int index;

    private Cursor(int value, int chunk, int index) {
      this.value = value;
      this.chunk = chunk;
      this.index = index;
    }
  }

  public static int[] externalSort(int[] values, int chunkSize) throws IOException {
    Path directory = Files.createTempDirectory("external-sort");
    List<Path> chunks = new ArrayList<>();
    List<List<Integer>> data = new ArrayList<>();
    for (int start = 0; start < values.length; start += chunkSize) {
      int end = Math.min(start + chunkSize, values.length);
      int[] part = Arrays.copyOfRange(values, start, end);
      Arrays.sort(part);
      Path file = directory.resolve("chunk-" + chunks.size() + ".txt");
      StringBuilder text = new StringBuilder();
      List<Integer> numbers = new ArrayList<>();
      for (int value : part) {
        text.append(value).append('\n');
        numbers.add(value);
      }
      Files.writeString(file, text.toString());
      chunks.add(file);
      data.add(numbers);
    }

    PriorityQueue<Cursor> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
    for (int i = 0; i < data.size(); i++) {
      if (!data.get(i).isEmpty()) {
        heap.add(new Cursor(data.get(i).get(0), i, 0));
      }
    }
    int[] result = new int[values.length];
    int index = 0;
    while (!heap.isEmpty()) {
      Cursor cursor = heap.remove();
      result[index++] = cursor.value;
      int next = cursor.index + 1;
      if (next < data.get(cursor.chunk).size()) {
        heap.add(new Cursor(data.get(cursor.chunk).get(next), cursor.chunk, next));
      }
    }
    for (Path chunk : chunks) {
      Files.deleteIfExists(chunk);
    }
    Files.deleteIfExists(directory);
    return result;
  }

  public static void main(String[] args) throws Exception {
    Random random = new Random(13);
    int[] values = new int[50];
    for (int i = 0; i < values.length; i++) {
      values[i] = random.nextInt(500);
    }
    int[] expected = values.clone();
    Arrays.sort(expected);
    int[] actual = externalSort(values, 7);
    check(Arrays.equals(actual, expected));
    System.out.println("Exercise_23_22 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
