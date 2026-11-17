package Exercise_27._24;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Exercise_27_24 {
  public static <K, V> Map<K, Set<V>> valuesToSet(
      Map<K, ? extends Collection<V>> map) {
    Map<K, Set<V>> result = new HashMap<>();
    for (Map.Entry<K, ? extends Collection<V>> entry : map.entrySet()) {
      result.put(entry.getKey(), new HashSet<>(entry.getValue()));
    }
    return result;
  }

  public static void main(String[] args) {
    Map<String, Collection<Integer>> source = new HashMap<>();
    source.put("a", java.util.List.of(1, 2, 2));
    source.put("b", java.util.List.of(3, 3));
    Map<String, Set<Integer>> result = valuesToSet(source);
    check(result.get("a").equals(Set.of(1, 2)));
    check(result.get("b").equals(Set.of(3)));
    System.out.println("Exercise_27_24 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
