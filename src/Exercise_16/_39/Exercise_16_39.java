package Exercise_16._39;

import java.util.ArrayList;
import java.util.List;

public class Exercise_16_39 {
  public static class SlideShow {
    private final List<String> slides = new ArrayList<>();
    private int index;

    public void add(String slide) {
      slides.add(slide);
    }

    public String current() {
      return slides.isEmpty() ? null : slides.get(index);
    }

    public String next() {
      if (!slides.isEmpty()) {
        index = (index + 1) % slides.size();
      }
      return current();
    }

    public String previous() {
      if (!slides.isEmpty()) {
        index = (index - 1 + slides.size()) % slides.size();
      }
      return current();
    }

    public int size() {
      return slides.size();
    }
  }

  public static void main(String[] args) {
    SlideShow show = new SlideShow();
    show.add("one");
    show.add("two");
    show.add("three");
    check(show.current().equals("one"));
    check(show.next().equals("two"));
    check(show.next().equals("three"));
    check(show.next().equals("one"));
    check(show.previous().equals("three"));
    check(show.size() == 3);
    System.out.println("Exercise_16_39 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
