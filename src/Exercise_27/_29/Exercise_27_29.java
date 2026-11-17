package Exercise_27._29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_27_29 {
  public static Map<String, List<String>> groupAnagrams(List<String> words) {
    Map<String, List<String>> groups = new HashMap<>();
    for (String word : words) {
      char[] letters = word.toCharArray();
      Arrays.sort(letters);
      String key = new String(letters);
      groups.computeIfAbsent(key, ignored -> new ArrayList<>()).add(word);
    }
    return groups;
  }

  public static void main(String[] args) {
    Map<String, List<String>> groups = groupAnagrams(
        List.of("eat", "tea", "tan", "ate", "nat", "bat"));
    check(groups.size() == 3);
    check(groups.get("aet").size() == 3);
    check(groups.get("ant").size() == 2);
    check(groups.get("abt").size() == 1);
    System.out.println("Exercise_27_29 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
