package Exercise_13._06;

public class Max {
    public static ComparableCircle max
            (ComparableCircle o1, ComparableCircle o2) {
        return (o1.compareTo(o2) > 0) ? o1 : o2;
    }
}
