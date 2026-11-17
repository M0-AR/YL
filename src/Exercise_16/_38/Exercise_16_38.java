package Exercise_16._38;

import java.util.ArrayList;
import java.util.List;

public class Exercise_16_38 {
  public static class Track {
    private final String title;
    private final int seconds;

    public Track(String title, int seconds) {
      this.title = title;
      this.seconds = seconds;
    }

    public String getTitle() {
      return title;
    }

    public int getSeconds() {
      return seconds;
    }
  }

  public static class MediaPlaylist {
    private final List<Track> tracks = new ArrayList<>();
    private int index;

    public void add(Track track) {
      tracks.add(track);
    }

    public boolean remove(String title) {
      for (int i = 0; i < tracks.size(); i++) {
        if (tracks.get(i).getTitle().equals(title)) {
          tracks.remove(i);
          if (index >= tracks.size()) {
            index = Math.max(0, tracks.size() - 1);
          }
          return true;
        }
      }
      return false;
    }

    public Track current() {
      return tracks.isEmpty() ? null : tracks.get(index);
    }

    public Track next() {
      if (!tracks.isEmpty()) {
        index = (index + 1) % tracks.size();
      }
      return current();
    }

    public Track previous() {
      if (!tracks.isEmpty()) {
        index = (index - 1 + tracks.size()) % tracks.size();
      }
      return current();
    }

    public int totalSeconds() {
      int total = 0;
      for (Track track : tracks) {
        total += track.getSeconds();
      }
      return total;
    }
  }

  public static void main(String[] args) {
    MediaPlaylist playlist = new MediaPlaylist();
    playlist.add(new Track("A", 60));
    playlist.add(new Track("B", 90));
    playlist.add(new Track("C", 30));
    check(playlist.current().getTitle().equals("A"));
    check(playlist.next().getTitle().equals("B"));
    check(playlist.next().getTitle().equals("C"));
    check(playlist.next().getTitle().equals("A"));
    check(playlist.previous().getTitle().equals("C"));
    check(playlist.remove("B"));
    check(playlist.totalSeconds() == 90);
    System.out.println("Exercise_16_38 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
